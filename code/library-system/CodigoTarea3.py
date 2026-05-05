import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from scipy.special import digamma, polygamma, gammaln
from scipy.optimize import root_scalar, minimize_scalar
from scipy.stats import norm

claves = ["208732", "215349", "212781"]

df1 = pd.read_table("tarea21.dat", sep=r"\s+")
df2 = pd.read_table("tarea22.dat", sep=r"\s+")

### Problema 1 (problema de las claves)

res = []

for c in claves:
    x = df1[c].to_numpy()
    res.append([
        c,
        x.mean() - 1,
        x.max() - 2,
        x.min()
    ])

tabla1 = pd.DataFrame(res, columns=["Clave", "theta_emm", "theta_emv_izq", "theta_emv_der"])
print(tabla1)

### Problema 4 (a/b, momentos y max ver)

res = []

for c in claves:
    x = df2[c].to_numpy()

    x_barra = x.mean()
    s2 = ((x - x_barra)**2).mean()

    alpha_mm = x_barra**2 / s2
    beta_mm = x_barra / s2

    alpha = alpha_mm
    for k in range(200):
        g = np.log(alpha) - digamma(alpha) - (np.log(x_barra) - np.log(x).mean())
        gp = 1/alpha - polygamma(1, alpha)
        alpha_nuevo = alpha - g/gp
        if alpha_nuevo <= 0 or not np.isfinite(alpha_nuevo):
            alpha_nuevo = alpha / 2
        if abs(alpha_nuevo - alpha) < 1e-12:
            alpha = alpha_nuevo
            break
        alpha = alpha_nuevo

    beta_mv = alpha / x_barra

    res.append([
        c,
        alpha_mm, beta_mm,
        alpha, beta_mv
    ])

tabla4 = pd.DataFrame(res, columns=["Clave", "alpha_mm", "beta_mm", "alpha_mv", "beta_mv"])
print(tabla4)

### Problema 4c simulación

# ===== 4(c) =====

def mv_gamma(x, tol=1e-10, max_iter=100):
    import numpy as np
    from scipy.special import digamma, polygamma

    x = np.asarray(x)
    x_barra = x.mean()
    log_x_barra = np.log(x).mean()

    # Inicialización con método de momentos
    s2 = ((x - x_barra)**2).mean()
    alpha = x_barra**2 / s2

    for _ in range(max_iter):
        g = np.log(alpha) - digamma(alpha) - (np.log(x_barra) - log_x_barra)
        gp = 1/alpha - polygamma(1, alpha)

        alpha_new = alpha - g/gp

        if alpha_new <= 0 or not np.isfinite(alpha_new):
            alpha_new = alpha / 2

        if abs(alpha_new - alpha) < tol:
            alpha = alpha_new
            break

        alpha = alpha_new

    beta = alpha / x_barra

    return alpha, beta, None

for c in claves:
    x = df2[c].to_numpy()

    alpha_mv, beta_mv, _ = mv_gamma(x)

    N = 3000
    n = len(x)

    alpha_sim = []
    theta_sim = []

    for i in range(N):
        xs = np.random.gamma(shape=alpha_mv, scale=1/beta_mv, size=n)
        a, b, _ = mv_gamma(xs)
        alpha_sim.append(a)
        theta_sim.append(1 / b)

    plt.figure(figsize=(10,4))

    plt.subplot(1,2,1)
    plt.hist(alpha_sim, bins=20, color="lightblue", edgecolor="black")
    plt.axvline(alpha_mv, color="red")
    plt.title(f"{c} - alpha_hat")

    plt.subplot(1,2,2)
    plt.hist(theta_sim, bins=20, color="lightgreen", edgecolor="black")
    plt.axvline(1/beta_mv, color="red")
    plt.title(f"{c} - theta_hat = 1/beta_hat")

    plt.tight_layout()
    plt.show()

### Problema 5 (b) TCL

n = 25
alpha = 0

varianza = (3 - alpha**2) / n
desv = np.sqrt(varianza)

prob = 2 * (1 - norm.cdf(0.5 / desv))
print(prob)

### Problema 5e (eficiencia relativa)

alpha = np.array([0.00, 0.10, 0.20, 0.30, 0.40, 0.50, 0.60, 0.70, 0.80, 0.90, 0.93, 0.95, 0.96])
ER = []

for a in alpha:
    if a == 0:
        I = 1/3
    else:
        I = (np.log((1 + a) / (1 - a)) - 2*a) / (2 * a**3)

    er = 1 / ((3 - a**2) * I)
    ER.append(er)

tabla = pd.DataFrame({
    "alpha": alpha,
    "ER": np.round(ER, 3)
})

print(tabla)

#grafica

plt.plot(alpha, ER, "o-", color="red")
plt.axhline(1, color="gray", linestyle="--")
plt.xlabel("Alpha")
plt.ylabel("Eficiencia Relativa")
plt.title("Eficiencia Relativa vs Alpha")
plt.ylim(0, 1.05)
plt.show()

### problema 5f (histogramas)

def rangular(n, a):
    u = np.random.rand(n)
    return (-1 + np.sqrt((1 - a)**2 + 4*a*u)) / a

def score(a, x):
    return np.sum(x / (1 + a*x))

def alpha_mv(x):
    try:
        return root_scalar(lambda a: score(a, x), bracket=[-0.999999, 0.999999], method="bisect").root
    except:
        return minimize_scalar(lambda a: -np.sum(np.log(1 + a*x)),
                               bounds=(-0.999999, 0.999999),
                               method="bounded").x

N = 5000
n = 35
a_real = 0.75

mm = []
mv = []

for i in range(N):
    x = rangular(n, a_real)
    mm.append(3 * x.mean())
    mv.append(alpha_mv(x))

#histogramas
plt.figure(figsize=(12,5))

plt.subplot(1,2,1)
plt.hist(mm, bins=20, color="lightblue", edgecolor="black")
plt.axvline(a_real, color="red")
plt.title("Metodo de Momentos")
plt.xlabel("Alpha estimado")

plt.subplot(1,2,2)
plt.hist(mv, bins=20, color="lightgreen", edgecolor="black")
plt.axvline(a_real, color="red")
plt.title("Maxima Verosimilitud")
plt.xlabel("Alpha estimado")

plt.tight_layout()
plt.show()