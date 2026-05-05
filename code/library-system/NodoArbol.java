/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda;

import Pilas.PilaA;
import java.util.ArrayList;

/**
 *
 * @author carlitos73
 */
public class NodoArbol <T extends Comparable <T>>{

    private NodoArbol<T> izq,der,papa;
    private T elem;
    
    public NodoArbol(T elem){
        this.elem=elem;
        izq=null;
        der=null;
        papa=null;
    }

    public NodoArbol<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoArbol<T> izq) {
        this.izq = izq;
    }

    public NodoArbol<T> getDer() {
        return der;
    }

    public void setDer(NodoArbol<T> der) {
        this.der = der;
    }

    public NodoArbol<T> getPapa() {
        return papa;
    }

    public void setPapa(NodoArbol<T> papa) {
        this.papa = papa;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }
    
    
    public int numDescendientes(){
        return numDescendientes(this,0);
    }
    
    private int numDescendientes(NodoArbol <T> nodo, int cont){
        if(der!=null){
            cont= numDesDer(nodo.getDer());
        }
        if(izq!=null){
            cont+= numDesIzq(nodo.getIzq());
        }
        return cont;
    }
    
    private int numDesIzq (NodoArbol <T> nodo){
        if(nodo==null){
            return 0;
        }
        else
            return numDesIzq(nodo.izq) + 1;
    }
    
    private int numDesDer (NodoArbol <T> nodo){
        if(nodo==null){
            return 0;
        }
        else
            return numDesDer(nodo.der) + 1;
    }
    
    public NodoArbol<T> encuentra(T elem){
        return encuentra(elem, this);
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
    
    private ArrayList<T> preOrden(NodoArbol<T> actual, ArrayList<T> lista){
        if(actual==null)
            return null;
        lista.add(actual.getElem());
        preOrden(actual.izq,lista);
        preOrden(actual.der,lista);
        return lista;
    }
    /*
    private void preOrden2(){
        NodoArbol<T> act=raiz;
        PilaA p=new PilaA();
        ArrayList<T> arre=new ArrayList();
        p.add(raiz);
        
        while(!p.isEmpty()){
            act=p.pop();
            arre.add(temp.elem());
            if(act.getIzq().getElem()!=null)
                p.push(act.getIzq());
            if(act.getDer().getElem()!=null)
                p.push(act.getDer());
        }
        
    }*/
    
    private ArrayList<T> postOrden(NodoArbol<T> actual, ArrayList<T> lista){
        if(actual==null)
            return null;
        
        postOrden(actual.izq,lista);
        postOrden(actual.der,lista);
        lista.add(actual.getElem());
        
        return lista;
    }
    
    private ArrayList<T> inOrden(NodoArbol<T> actual, ArrayList<T> lista){
        if(actual==null)
            return null;
        
        postOrden(actual.izq,lista);
        lista.add(actual.getElem());
        postOrden(actual.der,lista);
        
        return lista;
    }
    

    public int compareTo(NodoArbol<T> o) {
        return this.getElem().compareTo(o.getElem());
    }
    
}
