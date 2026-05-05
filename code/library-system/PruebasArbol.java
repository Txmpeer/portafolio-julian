/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import eda.BinaryTree;

/**
 *
 * @author carlitos73
 */
public class PruebasArbol{
    // Método Auxiliar para crear un número aleatrorio en un rango
    public static int numAleatorio(int min, int max){
        return (int)((Math.random() * (max - min)) + min);
    }  
    
    public  static void main(String[] args) {
        int n = 25000, alturaProm = 0;
        int [] arreN={5,100,1000,2500,5000,10000};
        for(int numero:arreN){
            for(int i = 0; i < 1000; i++){
                BinaryTree<Integer> arbol = new BinaryTree<>(numAleatorio(0, 10*n));
                for(int j = 0; j < n; j++){
                    int num = numAleatorio(0, 10*n);
                    arbol.insertaR(num);
                }
                alturaProm += arbol.size();
            }

            // Tamaños 10, 100, 1000, 5000, 10000, 25000, 50000, 100000
            System.out.println("Altura promedio para (" + numero + ") elementos: " + alturaProm / 1000);
        }
    }
}