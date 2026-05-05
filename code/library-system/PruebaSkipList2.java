/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkipList;

import java.util.Random;

/**
 *
 * @author carlitos73
 */
public class PruebaSkipList2 {
    
    public static<T> void main(String[] args) {
        Random rand=new Random();    
        /**
         * PRUEBA 1: ANALIZAR EL NÚMERO DE COMPARACIONES EN LA BÚSQUEDA EN UNA SKIPLIST
         * ANTES Y DESPUÉS DE BORRAR NODOS IMPARES Y VOLVER A INSERTAR
         */

        SkipList<Integer> sl1=new SkipList(5);

        //Ingresar 100 valores (de 200 a 300) ordenados ascendentemente
        int i=100;
        while(sl1.getCont()<25){
            sl1.ingresa(i);
            i++;
        }
        
        //Hacer 50 búsquedas para valores específicos, medir las comparaciones, promediarlas y guardarlas en un arreglo

        int[] arrePromedios = new int[25];
        for (int k = 0; k < 25; k++) {
            int j = 0, suma1 = 0;
            while (j < 25) {
                int random=rand.nextInt(100, 125); // Usa la misma instancia de Random
                //System.out.println(random);
                int comparaciones = sl1.buscaComparaciones(random);
                //System.out.println("Número de comparaciones para la comparación número " + j + "es: " + comparaciones);
                suma1 += comparaciones;
                j++;
            }
            int promedio = suma1 / 25;
            arrePromedios[k] = promedio;
            System.out.println("El promedio de comparaciones del nivel " + k + " es: " + promedio);
        }
        int sumaTotal=0;
        for (int a=0;a<arrePromedios.length;a++)
            sumaTotal+=arrePromedios[a];
        int promedioTotal=sumaTotal/arrePromedios.length;
        System.out.println("El promedio total de comparaciones es: "+promedioTotal);
        
        //Ahora quitar los impares
        int b=1;
        while(b<(sl1.getCont()/2)){
            sl1.borraEspecifico(b);
            b=b+2;
        }
        
        //Inserta un número entre 100 y 125
        sl1.ingresa(rand.nextInt(100, 125));
        
        //Vuelve a considerar las comparaciones
        int[] arrePromedios2 = new int[25];
        for (int k = 0; k < 25; k++) {
            int j = 0, suma1 = 0;
            while (j < 25) {
                int random=rand.nextInt(100, 125); // Usa la misma instancia de Random
                //System.out.println(random);
                int comparaciones = sl1.buscaComparaciones(random);
                //System.out.println("Número de comparaciones para la comparación número " + j + "es: " + comparaciones);
                suma1 += comparaciones;
                j++;
            }
            int promedio = suma1 / 25;
            arrePromedios[k] = promedio;
            System.out.println("El promedio " + k + " es: " + promedio);
        }
        int sumaTotal2=0;
        for (int a=0;a<arrePromedios2.length;a++)
            sumaTotal+=arrePromedios2[a];
        int promedioTotal2=sumaTotal2/arrePromedios2.length;
        System.out.println("El promedio total es: "+promedioTotal);


    }
}
