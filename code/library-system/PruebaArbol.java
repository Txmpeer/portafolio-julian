/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda;

import eda.NodoArbol;

/**
 *
 * @author carlitos73
 */
public class PruebaArbol {
    
    public static <T> void main(String[]args){
        /*
        NodoArbol <Integer> n1=new NodoArbol<Integer>(2);
        NodoArbol <Integer> n2=new NodoArbol<Integer>(5);
        NodoArbol <Integer> n3=new NodoArbol<Integer>(4);
        NodoArbol <Integer> n4=new NodoArbol<Integer>(18);
        
        n1.setDer(n3);
        n1.setIzq(n2);
        n2.setIzq(n4);
        
        int desc= n1.numDescendientes();
        System.out.print(desc);
        
        NodoArbol<Integer> nodo=n1.encuentra(18);*/
        
        BinaryTree arbol=new BinaryTree(2);
        arbol.insertaR(1);
        arbol.insertaR(3);
        arbol.insertaR(11);
        arbol.insertaR(8);
        arbol.insertaR(14);
        
        //arbol.size();
        if(arbol.esBin())
            System.out.println("El árbol es de búsqueda binaria");
        else
            System.out.println("El árbol no es de búsqueda binaria");
    }
    
}
