/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolLogN;

import Colas.ColaA;
import eda.BinaryTreeADT;
import eda.NodoArbol;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlitos73
 */
public class arbolLogN <T extends Comparable <T>>{
    
    private NodoArbol <T>raiz;
    private int elementos;
    
    public arbolLogN(NodoArbol raiz){
        this.raiz=raiz;
        elementos=1;
    }
    
    public arbolLogN(T dato){
        NodoArbol nuevaRaiz=new NodoArbol(dato);
        this.raiz=nuevaRaiz;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }
    
    
public void insertaR(T dato) {
    raiz = insertaR(dato, raiz);
    elementos++;
}

private NodoArbol<T> insertaR(T dato, NodoArbol<T> nodoActual) {
    if (nodoActual == null) {
        // Si el nodo actual es nulo, crea un nuevo nodo con el dato y devuélvelo
        return new NodoArbol<>(dato);
    }

    // Compara el dato con el valor del nodo actual
    int comparacion = dato.compareTo(nodoActual.getElem());

    if (comparacion < 0) {
        // Si el dato es menor, inserta en el subárbol izquierdo
        nodoActual.setIzq(insertaR(dato, nodoActual.getIzq()));
    } else if (comparacion > 0) {
        // Si el dato es mayor, inserta en el subárbol derecho
        nodoActual.setDer(insertaR(dato, nodoActual.getDer()));
    }
    // Si el dato es igual, no se permite duplicados (puedes ajustar esta lógica según tus necesidades)

    return nodoActual;
}

    
    public NodoArbol<T> find(T elem){
        return find(elem, raiz);
    }
    private <T extends Comparable <T>> NodoArbol<T> find(T elem, NodoArbol<T> actual) {
        if(actual==null)
            return null;
        if(actual.getElem().equals(elem))
            return actual;
        
        if(elem.compareTo(actual.getElem())<0)
            return find(elem,actual.getIzq());
        else
            return find(elem,actual.getDer());
    }

    public  void borra(T elem) {
        NodoArbol<T> actual=encuentra(elem);
        
        if(actual==null)
            return;
        if(actual.getDer()==null && actual.getIzq()==null){
            if(actual.getElem().compareTo(actual.getPapa().getElem())<0)
                actual.getPapa().setIzq(null);
            else
                actual.getPapa().setDer(null);
        }
        if(actual.getDer()==null || actual.getIzq()==null){
            if(actual.getElem().compareTo(actual.getPapa().getElem())<0){
                if(actual.getIzq()!=null)
                    actual.getPapa().setIzq(actual.getIzq());
                else
                    actual.getPapa().setIzq(actual.getDer());
            }else{
                if(actual.getIzq()!=null)
                    actual.getPapa().setDer(actual.getIzq());
                else
                    actual.getPapa().setDer(actual.getDer());
            }
        }
        else{
            NodoArbol<T> temp=actual.getDer();
            while(temp.getIzq()!=null)
                temp=temp.getIzq();
            T datoAux=actual.getElem();
            borra(temp.getElem());
            actual.setElem(datoAux);
        }
        
        
    }
    
    public NodoArbol<T> encuentra(T elem){
        return encuentra(elem,raiz);
    }
    
    
    private NodoArbol<T> encuentra(T elem, NodoArbol<T> actual){
        if(actual==null)
            return null;
        if(actual.getElem().equals(elem))
            return actual;
        
        NodoArbol <T> temp= encuentra(elem,actual.getIzq());
        if(temp==null)
            temp=encuentra(elem,actual.getDer());
        return temp;
    }
    
    public ArrayList<NodoArbol> inOrden(){
        ArrayList lista=new ArrayList();
        
        return inOrden(raiz,lista);
    }
    
    private ArrayList<NodoArbol> inOrden(NodoArbol<T> actual, ArrayList<NodoArbol> lista){
        if(actual==null)
            return null;
        
        inOrden(actual.getIzq(),lista);
        lista.add((NodoArbol) actual.getElem());
        inOrden(actual.getDer(),lista);
        
        return lista;
    }
    
    public void balancea(){
        balanceaR(raiz);
    }
    
    private void balanceaR(NodoArbol nodo){
        if(nodo != null || (nodo.getDer() != null && nodo.getIzq() != null)){
            ArrayList<NodoArbol> array = this.inOrden();
            T elem = (T) array.get(array.size() / 2);
            array.remove(elem);
            encuentra(elem).setElem((T)nodo.getElem());
            nodo.setElem(elem);
            for(int i = 0; i < array.size(); i++){
                
                T aux = (T) array.get(i);
                System.out.println(aux);
                this.borra(aux);
                this.insertaR(aux);
            }
            if(nodo.getDer() != null)
                balanceaR(nodo.getDer());
            else
                balanceaR(nodo.getIzq());
        }
    }
    
    public void balanceaR(){
        ArrayList<NodoArbol> arreNodos=this.inOrden();
        arbolLogN<T> arbolNuevo=new arbolLogN(arreNodos.get(elementos/2));
        List<NodoArbol> arreIzq= arreNodos.subList(0, elementos/2);
        List<NodoArbol> arreDer= arreNodos.subList((elementos/2)+1, elementos-1);
        balancea(arreIzq,arreIzq.size()-1,arbolNuevo);
        balancea(arreDer,arreDer.size()-1,arbolNuevo);
        
    }
    
    private void balancea(List<NodoArbol> arreNodos, int elem,arbolLogN arbol){
        if(elem<=1){
            for(int i=0;i<elem;i++)
                arbol.insertaR(i);
        }
        else{
            List<NodoArbol> arreIzq= arreNodos.subList(0, (elementos/2)-1);
            List<NodoArbol> arreDer= arreNodos.subList((elementos/2)+1, elementos-1);
            balancea(arreIzq, arreIzq.size()-1, arbol);
            balancea(arreDer, arreDer.size()-1, arbol);
        }
    
    }
    
    public void levelOrden(){ 
        ColaA<NodoArbol> cola = new ColaA<>();
        cola.agrega(raiz);
        
        while(!cola.estaVacia()){
            NodoArbol<T> nodoAux = cola.quita();
            System.out.println("[" + nodoAux.getElem() + "] ");
            if(nodoAux.getIzq() != null)
                cola.agrega(nodoAux.getIzq());
            if(nodoAux.getDer() != null)
                cola.agrega(nodoAux.getDer());
        }
    }
    
    public int size(){
        return size(raiz);
    }
    
    public int size(NodoArbol<T> actual) {
        if(actual==null)
            return 0;
        int der=size(actual.getDer());
        int izq=size(actual.getIzq());
        return max(der,izq)+1;
    }
    
    public boolean necesitaBalanceo(){
        int alturaIdeal= (int) (Math.log(elementos)/Math.log(2));
        int altura=this.size();
        if((alturaIdeal-altura)/elementos>2)
            return true;
        return altura != alturaIdeal;
    }
    
    
    
    
    public static void main(String [] args){
        arbolLogN<Integer> arbol=new arbolLogN<>(1);
        arbol.insertaR(2);
        arbol.insertaR(3);
        arbol.insertaR(4);
        
        System.out.println("El árbol antes es: \n");
        arbol.levelOrden();
        
        System.out.println("\nEl árbol después es: \n");
        arbol.borra(4);
        
        arbol.levelOrden();
        
        
    }
}
