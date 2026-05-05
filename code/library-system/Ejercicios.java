/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Colas;

//import Colas.ExceptionColeccionVacia;
import Pilas.ExceptionColeccionVacia;
import java.util.ArrayList;

/**
 *
 * @author carlitos73
 */
public class Ejercicios <T> {
    
    // Método recursivo
    public static <T> void invierteCola(ColaADT<T> cola){
	if(cola.estaVacia())
            throw new ExceptionColeccionVacia("La cola está vacía");
        invierteColaAux(cola);
    }
    private static <T> void invierteColaAux(ColaADT<T> cola) {
	if (!cola.estaVacia()) {
	    T elemento = cola.quita();
	    invierteColaAux(cola);
	    cola.agrega(elemento); 
	}
    }
    
    //Método iterativo
    public static <T> void invierteColaIt(ColaADT<T> cola){
        if(cola.estaVacia())
            throw new ExceptionColeccionVacia("La cola está vacía");
        ArrayList<T> lista=new ArrayList();
        while(!cola.estaVacia())
            lista.add(cola.quita());
        for(int i=lista.size()-1;i>=0;i--)
            cola.agrega(lista.get(i));
    }
    //Repetidos
    public static <T> void eliminaDatRep(ColaADT<T> cola){
        if(cola.estaVacia())
            throw new ExceptionColeccionVacia("La cola está vacía");
        T dato;
        ColaA<T> colaAux=new ColaA();
        while(!cola.estaVacia()){
            colaAux.agrega(cola.quita());
            if(!cola.estaVacia())
                if(colaAux.consultaPrimero().equals(cola.consultaPrimero()))
                    cola.quita();
        }
        while(!colaAux.estaVacia())
            cola.agrega(colaAux.quita());
        
    }
    //Iguales del mismo tipo
    public static <T> void eliminaTipoIgual(ColaADT<T> cola,String clase){
        ColaA<T> colaAux=new ColaA<T>();
        while(!cola.estaVacia()){
            if(cola.consultaPrimero().getClass().getSimpleName().equals(clase))
                cola.quita();
            else
                colaAux.agrega(cola.quita());
        }
        while(!colaAux.estaVacia())
            cola.agrega(colaAux.quita());
    }
    

    public static<T> void main(String[] args) {
        // Prueba recursiva
        ColaA<Integer> cola1=new ColaA<>();
        cola1.agrega(2);
        cola1.agrega(3);
	cola1.agrega(4);
	cola1.agrega(5);
	//while(!cola1.estaVacia())
	//	System.out.print(cola1.quita()+" ");
	try {
            invierteCola(cola1);
            System.out.print("\n");
            while(!cola1.estaVacia())
		System.out.print(cola1.quita()+" ");
	} catch (Exception e) {
            System.out.print(e.getMessage());
	} 
        
        //Prueba iterativa
        ColaA<Integer> cola2=new ColaA<>();
        cola2.agrega(10);
        cola2.agrega(20);
	cola2.agrega(30);
	cola2.agrega(40);
        
        System.out.print("\n");
	while(!cola2.estaVacia())
		System.out.print(cola2.quita()+" ");
	try {
            invierteColaIt(cola2);
            System.out.print("\n");
            //while(!cola2.estaVacia())
		//System.out.print(cola2.quita()+" ");
	} catch (Exception e) {
            System.out.print(e.getMessage());
	}
        
        //Repetidos
        ColaA<Integer> cola3=new ColaA<>();
        cola3.agrega(10);
        cola3.agrega(10);
	cola3.agrega(30);
	cola3.agrega(32);
        
        try {
            eliminaDatRep(cola3);
            System.out.print("\n");
            while(!cola3.estaVacia())
		System.out.print(cola3.quita()+" ");
	} catch (Exception e) {
            System.out.print(e.getMessage());
	}
        
        //Elementos del mismo tipo
        ColaA cola4=new ColaA<>();
        cola4.agrega("Hola");
        cola4.agrega(10);
	cola4.agrega(30.9349);
	cola4.agrega('c');
        
        eliminaTipoIgual(cola4,"String");
        System.out.print("\n");
        while(!cola4.estaVacia())
            System.out.print(cola4.quita()+" ");
        
        ColaA<Integer> cola5=new ColaA<>();
        cola5.agrega(10);
        cola5.agrega(9);
	cola5.agrega(8);
	cola5.agrega(7);
        
        ArrayList<Integer>lista=new ArrayList();
        int n=2;
        lista= cola5.multiQuita(n);
        
        System.out.print("\n");
        for(int i=0;i<n;i++)
            System.out.print(lista.get(i)+" ");
        System.out.print("\n");
        System.out.print(lista);

    }
}
