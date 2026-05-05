/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolAVL;

import eda.NodoArbol;
import java.util.ArrayList;

/**
 *
 * @author carlitos73
 */
public class NodoAVL <T extends Comparable<T>>{
    
    private NodoAVL<T> izq,der,papa;
    private T elem;
    private int fe;
    
    NodoAVL(T elem){
        this.elem=elem;
        izq=null;
        der=null;
        papa=null;
        fe=0;
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL<T> izq) {
        this.izq = izq;
    }

    public NodoAVL<T> getDer() {
        return der;
    }

    public void setDer(NodoAVL<T> der) {
        this.der = der;
    }

    public NodoAVL<T> getPapa() {
        return papa;
    }

    public void setPapa(NodoAVL<T> papa) {
        this.papa = papa;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public int getFe() {
        return fe;
    }
    
    
    public int numDescendientes(){
        return numDescendientes(this,0);
    }
    
    private int numDescendientes(NodoAVL <T> nodo, int cont){
        if(der!=null){
            cont= numDesDer(nodo.der);
        }
        if(izq!=null){
            cont+= numDesIzq(nodo.izq);
        }
        return cont;
    }
    
    private int numDesIzq (NodoAVL <T> nodo){
        if(nodo==null){
            return 0;
        }
        else
            return numDesIzq(nodo.izq) + 1;
    }
    
    private int numDesDer (NodoAVL <T> nodo){
        if(nodo==null){
            return 0;
        }
        else
            return numDesIzq(nodo.der) + 1;
    }
    
    private NodoAVL<T> encuentra(T elem, NodoAVL<T> actual){
        if(actual==null)
            return null;
        if(actual.getElem().equals(elem))
            return actual;
        
        NodoAVL <T> temp= encuentra(elem,actual.getIzq());
        if(temp==null)
            temp=encuentra(elem,actual.getDer());
        return temp;
    }
    
    private ArrayList<T> preOrden(NodoAVL<T> actual, ArrayList<T> lista){
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
        Pila p=new Pila();
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
    
    private ArrayList<T> postOrden(NodoAVL<T> actual, ArrayList<T> lista){
        if(actual==null)
            return null;
        
        postOrden(actual.izq,lista);
        postOrden(actual.der,lista);
        lista.add(actual.getElem());
        
        return lista;
    }
    
    private ArrayList<T> inOrden(NodoAVL<T> actual, ArrayList<T> lista){
        if(actual==null)
            return null;
        
        postOrden(actual.izq,lista);
        lista.add(actual.getElem());
        postOrden(actual.der,lista);
        
        return lista;
    }
    /*
    private void levelOrden(){
        NodoArbol<T> act=raiz;
        Cola p=new Cola();
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
    public int compareTo(NodoAVL<T> o) {
        return this.elem.compareTo(o.elem);
    }
    
    public void cuelga(NodoAVL<T> n){
        if(n==null)
            return;
        if(n.getElem().compareTo(elem)<=0)
            izq=n;
        else
            der=n;
        n.papa=this;
    }

}
