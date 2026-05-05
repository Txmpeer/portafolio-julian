/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda;

import static java.lang.Integer.max;

/**
 *
 * @author carlitos73
 */
public class BinaryTree <T extends Comparable <T>>implements BinaryTreeADT<T>{
    private NodoArbol raiz;
    
    public BinaryTree(NodoArbol raiz){
        this.raiz=raiz;
    }
    public BinaryTree(T dato){
        NodoArbol nuevaRaiz=new NodoArbol(dato);
        this.raiz=nuevaRaiz;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }
    
    public void insertaR(T dato){
        NodoArbol nuevo=new NodoArbol(dato);
        insertaR(nuevo,raiz);        
        
    }
    
    private void insertaR(NodoArbol nuevo, NodoArbol aux){
        if(nuevo.getElem().compareTo(aux.getElem())<0){
            if(aux.getIzq()!=null)
                insertaR(nuevo, aux.getIzq());
            else{
                nuevo.setPapa(aux);
                aux.setIzq(nuevo);
            }
        }
        else{
            if(aux.getDer()!=null)
                insertaR(nuevo, aux.getDer());
            else{
                nuevo.setPapa(aux);
                aux.setDer(nuevo);
            }
        }
    }
    
    public int compareTo(NodoArbol o) {
        return this.raiz.getElem().compareTo(o.getElem());
    }

    @Override
    public boolean isEmpty() {
        return raiz==null;
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
        NodoArbol<T> actual=find(elem);
        
        if(actual==null)
            return;
        if(actual.getDer()==null && actual.getIzq()==null){ //No tiene hijos
            if(actual.getElem().compareTo(actual.getPapa().getElem())<0)
                actual.getPapa().setIzq(null);
            else
                actual.getPapa().setDer(null);
        }
        if(actual.getDer()!=null || actual.getIzq()!=null){ //Un hijo
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
        
        if(actual.getDer()!=null && actual.getIzq()!=null){ // Dos hijos
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
            
        }else{
            NodoArbol<T> temp=actual.getDer();
            while(temp.getIzq()!=null)
                temp=temp.getIzq();
            T datoAux=actual.getElem();
            borra(temp.getElem());
            actual.setElem(datoAux);
        }
        
        
    }
    
    public boolean esBin(){
        NodoArbol<T> actual=raiz;
        return esBin(actual);
    }
    
    private boolean esBin(NodoArbol<T> actual){
        if(actual.getIzq()!=null){
            if(actual.getIzq().getElem().compareTo(actual.getElem())<0)
                return esBin(actual.getIzq());
            else
                return false;
            
        } else if(actual.getIzq()!=null){
            if(actual.getDer().getElem().compareTo(actual.getElem())>0)
                return esBin(actual.getDer());
            else
                return false;
        } else
            return true;
    }

    @Override
    public T encuentraMin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T encuentraMax() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean encuentra(T elem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
