/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkipList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author carlitos73
 */
public class PruebaSkipList {
    
    public static<T> void main(String[] args) {
        Random rand = new Random();

        // SOLO INGRESA
        SkipList<Integer> sL=new SkipList(5);
        
        while (sL.getCont() < 100) {
            sL.ingresa(rand.nextInt(100));  // Inserta un número entero aleatorio entre 0 y 99
            Object[] arre = sL.esOptimo();
            boolean esOptimo = (boolean) arre[0];
            int contador = (int) arre[1], contadorElim = (int) arre[2];
            if (!esOptimo) {
                System.out.println("numInserta: " + contador + " numEliminaciones: " + contadorElim);
                break;
            }
        }
        
        //SOLO ELIMINA
        
        //INGRESA Y ELIMINA DE MANERA ALEATORIA
        SkipList<Integer> sL3=new SkipList(5);
        int[] elementos3 = new int[100];

        // Genera 100 números aleatorios y los guarda en el array 'elementos'
        for (int i = 0; i < 100; i++) {
            elementos3[i] = rand.nextInt(100);
        }
        // Realiza operaciones de inserción y eliminación aleatorias
        for (int i = 0; i < 100; i++) {
            if (rand.nextBoolean()) {
                // Inserta un elemento
                sL3.ingresa(elementos3[i]);
                System.out.println("Insertado: " + elementos3[i]);
            } else {
                // Borra un elemento
                if (sL3.getCont() > 0) {
                    sL3.borra(elementos3[i % sL3.getCont()]);
                    System.out.println("Borrado: " + elementos3[i % sL3.getCont()]);
                }
            }

            // Comprueba si la altura de la SkipList es óptima
            Object[] arre3 = sL3.esOptimo();
            boolean esOptimo3 = (boolean) arre3[0];
            int contador3 = (int) arre3[1], contadorElim3 = (int) arre3[2];
            if (!esOptimo3) {
                System.out.println("numInserta: " + contador3 + " numEliminaciones: " + contadorElim3);
                break;
            }
        }
        
        //INGRESA DE FORMA ASCENDENTE
        SkipList<Integer> sL4=new SkipList(5);
        int[] elementos4 = new int[100];
        for (int i = 0; i < 100; i++) {
            elementos4[i] = rand.nextInt(100);
        }
        Arrays.sort(elementos4);

        // Inserta los elementos en la SkipList
        for (int elem : elementos4) {
            sL4.ingresa(elem);
            Object[] arre4 = sL4.esOptimo();
            boolean esOptimo4 = (boolean) arre4[0];
            int contador4 = (int) arre4[1], contadorElim4 = (int) arre4[2];
            if (!esOptimo4) {
                System.out.println("numInserta: " + contador4 + " numEliminaciones: " + contadorElim4);
                break;
            }
        }
        
        //INGRESA DE FORMA DESCENDENTE
        SkipList<Integer> sL5=new SkipList(5);
        int[] elementos5 = new int[100];
        for (int i = 0; i < 100; i++) {
            elementos5[i] = rand.nextInt(100);
        }
        Arrays.sort(elementos5);
        int j=0,k=elementos5.length-1,temp=0;
        while(j<=k){
            temp=elementos5[j];
            elementos5[j]=elementos5[k];
            elementos5[k]=temp;
            j++;
            k--;
        }

        // Inserta los elementos en la SkipList
        for (int elem : elementos5) {
            sL5.ingresa(elem);
            Object[] arre5 = sL5.esOptimo();
            boolean esOptimo5 = (boolean) arre5[0];
            int contador5 = (int) arre5[1], contadorElim5 = (int) arre5[2];
            if (!esOptimo5) {
                System.out.println("numInserta: " + contador5 + " numEliminaciones: " + contadorElim5);
                break;
            }
        }
        
    }
    
}
