/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkipList;

import ArbolAVL.NodoAVL;

/**
 *
 * @author carlitos73
 */
public class NodoSkip <T extends Comparable <T>>{
    private T elem;
    private NodoSkip<T> izq, der, arriba, abajo;
    
    public NodoSkip(){
        
    }
    
    public NodoSkip(T elem){
        this.elem=elem;
        this.izq=null;
        this.der=null;
        this.arriba=null;
        this.abajo=null;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoSkip<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoSkip<T> izq) {
        this.izq = izq;
    }

    public NodoSkip<T> getDer() {
        return der;
    }

    public void setDer(NodoSkip<T> der) {
        this.der = der;
    }

    public NodoSkip<T> getArriba() {
        return arriba;
    }

    public void setArriba(NodoSkip<T> arriba) {
        this.arriba = arriba;
    }

    public NodoSkip<T> getAbajo() {
        return abajo;
    }

    public void setAbajo(NodoSkip<T> abajo) {
        this.abajo = abajo;
    }
    
    public int compareTo(NodoSkip<T> o) {
        return this.elem.compareTo(o.elem);
    }
    
    public String toString(){
        return "\nelemento: "+elem+" nodo izquierda: "+izq.getElem()+" nodo derecha: "+der.getElem()+" nodo abajo: "+abajo.getElem()+" nodo arriba: "+arriba.getElem();
    }
    
}
