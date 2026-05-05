/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pilas;

/**
 *
 * @author carlitos73
 */
public class ExceptionColeccionVacia extends RuntimeException{
    public ExceptionColeccionVacia(){
        
    }
    
    public ExceptionColeccionVacia(String mensaje){
        super(mensaje);
    }
}
