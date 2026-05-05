/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eda;

/**
 *
 * @author carlitos73
 */
public interface BinaryTreeADT <T extends Comparable <T>>{
    public boolean isEmpty();
    public int size();
    public NodoArbol find(T elem);
    public String toString();
    public void insertaR(T dato);
    public void borra(T elem);
    public T encuentraMin();
    public T encuentraMax();
    public boolean encuentra(T elem);
    
}
