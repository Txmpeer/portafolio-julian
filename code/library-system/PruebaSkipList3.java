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
public class PruebaSkipList3 {
    
    public static<T> void main(String[] args) {
        Random rand=new Random();
        int[] promedioElemMasivo=new int[100], sumaElemMasiva=new int[100], conteoElemMasiva=new int[100]; // inicializa el conteo aquí

        for (int i=0;i<1000;i++){ //Se crearán 1000 listas
            SkipList<Integer> sl=new SkipList(1);
            for (int j=0;j<500;j++){ //Se insertan 500 datos entre 1 y 1000
                int random=rand.nextInt(1, 1000);
                sl.ingresa(random);
            }

            //System.out.println("Lista: \n"+sl.toString());

            // Crear un arreglo con el número de elementos en cada nivel
            int[] arreElemNivel= sl.contElemNivel();
            int z=0;
            while(z<arreElemNivel.length){
                //System.out.println("Elementos en el nivel "+z+" son: "+arreElemNivel[z]);
                z++;
            }

            // cuando sumas los elementos, también incrementa el conteo
            for (int a = 0; a < arreElemNivel.length; a++) {
                sumaElemMasiva[a] += arreElemNivel[a];
                conteoElemMasiva[a]++;
            }

            // luego, cuando calculas el promedio, divide por el conteo
            int k=0;
            while(k<promedioElemMasivo.length && k<sl.contElemNivel().length){
                if (sumaElemMasiva[k] != 0) {
                    promedioElemMasivo[k] = sumaElemMasiva[k] / conteoElemMasiva[k];
                    System.out.println("Promedio de elementos en el nivel "+k+" es: "+promedioElemMasivo[k]);
                }
                k++;
            }

        }

        

    }
    
}
