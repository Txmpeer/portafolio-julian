/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkipList;

/**
 *
 * @author carlitos73
 */
public class SkipList <T extends Comparable <T>>{
    private int cont;
    private int numListas;
    private int contEliminaciones;
    private boolean esOptimo;
    private NodoSkip<T> cabeza=null, cola=null;
    
    public SkipList(T elem){
        numListas=1;
        cont=1;
        contEliminaciones=0;
        NodoSkip<T> nuevo=new NodoSkip<T>(elem);
        cabeza = new NodoSkip<T>();
        cola = new NodoSkip<T>();
        cabeza.setDer(nuevo);
        cabeza.setIzq(null);
        cabeza.setArriba(null);
        cabeza.setAbajo(null);
        cola.setIzq(nuevo);
        cola.setDer(null);
        cola.setArriba(null);
        cola.setAbajo(null);
        nuevo.setIzq(cabeza);
        nuevo.setDer(cola);
    }


    public int getCont() {
        return cont;
    }

    public int getNumListas() {
        return numListas;
    }

    public NodoSkip<T> getCabeza() {
        return cabeza;
    }

    public NodoSkip<T> getCola() {
        return cola;
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
            sb.append("     Piso [" + (numListas - i) + "] contiene: " + contP + " elementos \n");
        }
        System.out.println("Cantidad de movimientos realizados (toString): " + (cont - 1) + "\n");
        return sb.toString();
    }


    
    public NodoSkip<T> busca(T elem){
        NodoSkip<T> buscado=buscarR(elem);
        return buscado;
    }



    private NodoSkip<T> buscarR(T elem){ //Regresa uno antes de donde está o debería estar
        NodoSkip<T> actual=cabeza;
        for(int i=0; i<numListas;i++){
            while(actual.getDer().getElem() != null && elem.compareTo(actual.getDer().getElem()) >= 0)
                actual=actual.getDer();
            if(actual.getAbajo()!=null)
                actual=actual.getAbajo();
        }
        return actual;
    }
    
    private static int comparar(Comparable obj1, Comparable obj2, int[] contador) {
        contador[0]++; // incrementar el primer elemento del array
        return obj1.compareTo(obj2);
    }
    
    public int buscaComparaciones(T elem) {
        int[] contComp = new int[1]; // Inicializa el contador
        NodoSkip<T> actual = cabeza;
        for (int i = 0; i < numListas; i++) {
            while (actual.getDer().getElem() != null && comparar(elem, actual.getDer().getElem(), contComp) >= 0) {
                actual = actual.getDer();
            }
            if (actual.getAbajo() != null)
                actual = actual.getAbajo();
        }
        return contComp[0];
    }

    
    public NodoSkip<T> ingresa(T elem) {
        NodoSkip<T> actual = busca(elem);
        NodoSkip<T> nuevo = new NodoSkip<T>(elem);
        int volados = 1;
        ligarH(actual, nuevo);
        cont++;
        while (Math.random() > 0.5 && volados < Math.log(cont) / Math.log(2)) {
            volados++;
            if (volados > numListas)
                agregaNivel();
            NodoSkip<T> nuevo2=new NodoSkip<T>(elem);
            actual=nuevo;
            while (actual.getArriba()==null)
                actual=actual.getIzq();
            
            actual=actual.getArriba();
            ligarH(actual,nuevo2);
            ligarV(nuevo,nuevo2);
            nuevo=nuevo2;
        }
        return nuevo;
    }


    
    private void ligarH(NodoSkip<T> nodo, NodoSkip<T> nodoAligar){
            NodoSkip<T> temp = nodo.getDer();
            nodo.setDer(nodoAligar);
            nodoAligar.setIzq(nodo);
            nodoAligar.setDer(temp);
            temp.setIzq(nodoAligar);
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
        NodoSkip<T> buscado=busca(dato);
        if(buscado==null || buscado.getElem()==null)
            return;

        while(buscado!=null){
            borraH(buscado.getIzq(),buscado.getDer());
            buscado=buscado.getArriba();
            
        }
        cont--;
        if(Math.log(cont)>numListas && numListas>1){
            colapsar();
        }
    }
    
    public void borraEspecifico(int num){
        if(num <= cont){
           NodoSkip<T> actual = cabeza.getDer();
           int i = 1;
           while(i < num && actual != null && actual.getElem() != null){
               actual = actual.getDer();
               i++;
           }
           while(actual != null){
               NodoSkip<T> izq = actual.getIzq();
               NodoSkip<T> der = actual.getDer();
               if (izq != null && der != null) {
                   borraH(izq, der);
               }
               actual = actual.getArriba();
           }
           cont--;
           if(Math.log(cont) > numListas && numListas > 1){
               colapsar();
           }
        }
    }

    
    public void borraH(NodoSkip<T> n1, NodoSkip<T> n2){
        if (n1 != null && n2 != null) {
            n1.setDer(n2);
            n2.setIzq(n1);
        } else {
            // Manejar el caso cuando una de las referencias es nula
            System.out.println("Una de las referencias es nula.");
        }
    }

    
    public void colapsar(){
        NodoSkip<T> actual=cabeza;
        if(cabeza.getAbajo()==null)
            return;
        cabeza=cabeza.getAbajo();
        cola=cola.getAbajo();
        while(actual!=null){
            actual.setAbajo(null);
            actual=actual.getDer();
        }
        numListas--;
    }
    
    public boolean esAlturaOptima() {
        int alturaOptima = (int) Math.ceil(Math.log(cont) / Math.log(2));
        return numListas == alturaOptima;
    }
    
    //Método que devuelve si es óptima la altura, y también el número de inserta o elimina que tiene
    // para estar o no óptimo
    public Object[] esOptimo() {
        Object [] res=new Object[3];
        if(esAlturaOptima()){
            res[0]=true;
            res[1]=cont;
            res[2]=contEliminaciones;
            return res;
        }
        else{
            res[0]=false;
            res[1]=cont;
            res[2]=contEliminaciones;
            return res;
        }
    }
    
    
    public int[] contElemNivel(){
        int[] contPorNivel = new int[numListas];
        int cont;
        NodoSkip<T> actual = cabeza,pivote=cabeza;
        
        for(int i = 0; i < numListas; i++){
            cont=0;
            while(actual.getDer() != null && actual.getDer().getElem() != null){
                cont++;
                actual = actual.getDer();
            }
            pivote=pivote.getAbajo();
            actual=pivote;
            contPorNivel[i]=cont;
        }
        return contPorNivel;
    }
    
    
}
