/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TablasDeHash;

/**
 *
 * @author carlitos73
 */
public class TablaHash <T extends Comparable <T>>{
    private T [] tabla;
    int contador;
    double fc=0.7;
    
    public TablaHash(){
        tabla=new (T) Object[];
        
    }
    
    public int fnHash(){
        
    }
    
    private NodoHash<T> busca(T elem){
        long pos=elem.fnHash();
        NodoHash<T> actual=tabla[pos%tabla.length];
        actual=actual.sig;
        while(actual!=null && actual.elem!=elem)
            actual=actual.sig;
        return actual;
    }
    
    private void borra(T elem){
        long pos=elem.fnHash();
        
    }
    
}
