/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Colas;

import java.util.ArrayList;

/**
 *
 * @author carlitos73
 */
public interface ColaADT <T>{
    public void agrega(T dato);
    public T quita();
    public boolean estaVacia();
    public T consultaPrimero();
    public int cuentaElementos();
    public T consultaUltimo();
    public ArrayList multiQuita(int n);
    
    
}
