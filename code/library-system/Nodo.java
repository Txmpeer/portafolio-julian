/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;

/**
 *
 * @author carlitos73
 */
public class Nodo <T>{
    private T dato;
    private Nodo <T> sig; //siguiente, direccion, next
    
    public Nodo (T dato){
        this.dato=dato;
        this.sig=null;
    }
    
    public Nodo (T dato,Nodo <T> sig){
        this.dato=dato;
        this.sig=sig;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return sig;
    }

    public void setSiguiente(Nodo<T> direccion) {
        this.sig = direccion;
    }
    public String toString(){
        return dato+" ";
    }
    
    
}
