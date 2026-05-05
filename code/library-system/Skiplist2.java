/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkipList;

/**
 *
 * @author carlitos73
 */
public class Skiplist2 <T extends Comparable<T>>{

    int cont;
    int numListas;
    NodoSkip<T> cabeza, cola;

    public Skiplist2() {
        cabeza = new NodoSkip<>();
        cola = new NodoSkip<>();
        cabeza.setDer(cola);
        cola.setIzq(cabeza);
        cont = 0;
        numListas = 1;
    }
    
    public int getNumListas(){
        return numListas;
    }
    
    public NodoSkip<T> busca(T dato){
        NodoSkip<T> nodo = buscaR(dato);
        return nodo;
    }
    
    private NodoSkip<T> buscaR(T dato){
        NodoSkip<T> actual = cabeza;
        for(int i = 0; i < numListas; i++){ //Itera de arriba a abajo por las listas enlazadas
            while(actual.getDer().getElem() != null && dato.compareTo(actual.getDer().getElem()) >= 0) //Mientras que actual derecha tenga dato y el dato buscado todavía sea mayor que el de la derecha actual
                actual = actual.getDer(); //Actual se mueve a la derecha
            //Cuando acaba, el actual ya no es 
            if(actual.getAbajo() != null) //Baja al nodo de la siguiente lista
                actual = actual.getAbajo();
        }
        return actual;
    }
    
    // Muestra las comparaciones realizadas por "busca"
    public int buscaComp(T dato){
        return buscaCompR(dato);
    }
    
    private int buscaCompR(T dato){
        NodoSkip<T> actual = cabeza;
        int contador = 0;
        for(int i = 0; i < numListas; i++){
            while(actual.getDer().getElem() != null){
                contador++;
                if(dato.compareTo(actual.getDer().getElem()) >= 0)
                    actual = actual.getDer();
                else
                    break;
            }
            if(actual.getAbajo() != null)
                actual = actual.getAbajo();
        }
        return contador;
    }
    
    
    public void inserta(T dato){
        NodoSkip<T> actual = busca(dato); //El actual es un nodo del dato donde debería estar el nuevo dato (uno antes)
        NodoSkip<T> nuevo = new NodoSkip<T>(dato); //Se crea el nodo a insertar
        ligarH(actual, nuevo); //Se liga al nuevo con el actual horizontalmente
        cont++;
        int volados = 1;
        while(Math.random() > 0.5 && volados < Math.log(cont)/Math.log(2)){ //Mientras que los volados realizados sean menores 
            volados++;
            if(volados > numListas)
                agregaNivel();
            NodoSkip<T> nuevo2 = new NodoSkip<T>(dato);
            actual = nuevo;
            while(actual.getArriba() == null)
                actual = actual.getIzq();
            actual = actual.getArriba();
            ligarH(actual, nuevo2);
            ligarV(nuevo, nuevo2);
            nuevo = nuevo2;
        }
    }
    
   
    private void ligarH(NodoSkip<T> n1, NodoSkip<T> n2){
        NodoSkip<T> temp = n1.getDer();
        n1.setDer(n2);
        n2.setIzq(n1);
        n2.setDer(temp);
        temp.setIzq(n2);
    }
    
    private void ligarV(NodoSkip<T> n1, NodoSkip<T> n2){
        n1.setArriba(n2);
        n2.setAbajo(n1);
    }
    
    private void agregaNivel(){
        NodoSkip<T> temp1 = new NodoSkip<T>();
        NodoSkip<T> temp2 = new NodoSkip<T>();
        temp1.setDer(temp2);
        temp2.setIzq(temp1);
        cabeza.setArriba(temp1);
        temp1.setAbajo(cabeza);
        cola.setArriba(temp2);
        temp2.setAbajo(cola);
        cabeza = temp1;
        cola = temp2;
        numListas++;
    }
    
    public void borra(T dato){
        NodoSkip<T> nodoBorra = this.busca(dato);
        if(nodoBorra == null || nodoBorra.getElem() == null || !nodoBorra.getElem().equals(dato))
            return;
        while(nodoBorra != null){
            borraH(nodoBorra.getIzq(), nodoBorra.getDer());
            nodoBorra = nodoBorra.getArriba();
            cont--;
        }
        if(numListas > Math.log(cont)/Math.log(2) && numListas > 1){
            colapsa();
        }
    }
    
    private void borraH(NodoSkip<T> n1, NodoSkip<T> n2){
        n1.setDer(n2);
        n2.setIzq(n1);
    }
 
    
    private void colapsa(){
        NodoSkip<T> actual = cabeza;
        if(cabeza.getAbajo() == null)
            return;
        cabeza = cabeza.getAbajo();
        cola = cola.getAbajo();
        while(actual != null){
            actual.setArriba(null);
            actual = actual.getDer();
        }
        numListas--;
    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int cont = 0;
        int contP;
        NodoSkip<T> pivote = cabeza;
        NodoSkip<T> actual = pivote;
        for(int i = 0; i < numListas; i++){
            contP = 0;
            while(actual.getDer().getElem() != null){
                actual = actual.getDer();
                sb.append("[" + actual.getElem() + "] ");
                cont++;
                contP++;
            }
            pivote = pivote.getAbajo();
            actual = pivote;
            cont++;
            sb.append("     Piso [" + (numListas - i) + "] conotiene: " + contP + " elementos \n");
        }
        System.out.println("Cantidad de movimientos realizados (toString): " + (cont - 1) + "\n");
        return sb.toString();
    }
    
    
    public int[] datosPorNivel(){
        int[] res = new int[numListas];
        int cont;
        NodoSkip<T> pivote = cabeza;
        NodoSkip<T> actual = cabeza;
        for(int i = 0; i < numListas; i++){
            cont = 0;
            while(actual.getDer().getElem() != null){
                actual = actual.getDer();
                cont++;
            }
            pivote = pivote.getAbajo();
            actual = pivote;
            res[i] = cont;
        }
        return res;
    }
    
}
