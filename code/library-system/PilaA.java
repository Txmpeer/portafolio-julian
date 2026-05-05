/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pilas;

/**
 *
 * @author carlitos73
 */
public class PilaA <T> implements PilaADT<T>{
    private int tope;
    private T [] datos;
    private final int MAX=20;
    
    public PilaA(){
        datos= (T[]) new Object [MAX];
        tope=-1;
    }
    
    public void expande(){
        T [] masGrande= (T[]) new Object [datos.length*2];
        for(int i=0;i<=tope;i++)
            masGrande[i]=datos[i];
        datos=masGrande;
    }

    @Override
    public void push(T dato) {
        if(tope==datos.length-1)
            expande();
        tope++;
        datos[tope]=dato;
    }

    @Override
    public T pop() {    
        if(isEmpty())
            throw new ExceptionColeccionVacia("La pila está vacía");
        
        T resultado=datos[tope];
        datos[tope]=null;
        tope--;
        
        return resultado;
    }

    @Override
    public boolean isEmpty() {
        return tope==-1;
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new ExceptionColeccionVacia("La pila está vacía");
        return datos[tope];
    }

    @Override
    public void multiPop(int n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String toString(){
        StringBuilder sB=new StringBuilder();
        PilaADT pilaAux=new PilaA();
        while(!isEmpty()){
            pilaAux.push(this.pop());
            sB.append(pilaAux.peek());
        }
        while(!pilaAux.isEmpty())
            this.push((T) pilaAux.pop());
        return sB.toString();
    }
}
