/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Colas;

import Pilas.ExceptionColeccionVacia;
import java.util.ArrayList;

/**
 *
 * @author carlitos73
 */
public class ColaA <T> implements ColaADT<T>{
    private T [] cola;
    private int primero;
    private int ultimo;
    private final int MAX=20;
    
    public ColaA(){
        cola=(T[]) new Object[MAX];
        primero=-1;
        ultimo=-1;
    }
    public ColaA(int max){
        cola=(T[]) new Object[max];
        primero=-1;
        ultimo=-1;
    }

    @Override
    public void agrega(T dato) {
        if(estaVacia())
            primero=0;
        else
            if((ultimo+1)%cola.length==primero) //La cola está llena
                expande();
        ultimo=(ultimo+1)%cola.length;
        cola[ultimo]=dato;
    }
    
    private void expande(){
        T [] masGrande= (T[]) new Object[cola.length*2];
        int tam=cola.length;
        for(int i=0;i<tam;i++)
            masGrande[i]=cola[(primero+1)%tam];
        cola=masGrande;
        primero=0;
        ultimo=tam-1;
    }

    @Override
    public T quita() {
        if(estaVacia())
            throw new ExceptionColeccionVacia("La cola está vacía");
        T eliminado=cola[primero];
        if(primero==ultimo){ //Hay un solo elemento ---> debe quedar vacía
            primero=-1;
            ultimo=-1;
        }
        else
            primero=(primero+1)%cola.length;
        return eliminado;
    }

    @Override
    public boolean estaVacia() {
        return primero==-1 && ultimo==-1;
        
    }

    @Override
    public T consultaPrimero() {
        if(estaVacia())
            throw new ExceptionColeccionVacia("La cola está vacía");
        return cola[primero];
    }

    @Override
    public int cuentaElementos() {
        if(estaVacia())
            throw new ExceptionColeccionVacia("La cola está vacía");
        if(ultimo>=primero)
            return (ultimo-primero)+1;
        return ultimo+(cola.length)+1;
    }

    @Override
    public T consultaUltimo() {
        if(estaVacia())
            throw new ExceptionColeccionVacia("La cola está vacía");
        return cola[ultimo];
    }

    @Override
    public ArrayList multiQuita(int n) {
        ArrayList<T> lista=new ArrayList();
        if(n>cuentaElementos())
            throw new ExceptionColeccionVacia("La n excede el número de elementos");
        for(int i=0;i<n;i++){
            lista.add(quita());
        }
        return lista;
    }
    
    
}
