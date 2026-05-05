/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TablasDeHash;

/**
 *
 * @author carlitos73
 */
public class NodoHash <T extends Comparable <T>>{
    private T elem;
    NodoHash<T> sig;
    
    public NodoHash(T elem){
        this.elem=elem;
        sig.elem=null;
    }
    
}
