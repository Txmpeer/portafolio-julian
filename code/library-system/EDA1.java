/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eda;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlitos73
 */
public class EDA1  <T extends Comparable <T>>{

    public static int comparar(Comparable obj1, Comparable obj2, int[] contador) {
        contador[0]++; // incrementar el primer elemento del array
        return obj1.compareTo(obj2);
    }


    public <T extends Comparable <T>> void selectionSort(T[] arre){
        int minPos;
        int n=arre.length;
        T aux;
        
        for(int i=0;i<n;i++){
            minPos=i;
            for(int j=i+1;j<n;j++){
                if(arre[j].compareTo(arre[minPos])<0)
                    minPos=j;
            }
            aux=arre[i];
            arre[i]=arre[minPos];
            arre[minPos]=aux;
            
        }
    }
    
    public <T extends Comparable <T>> int selectionSortComparaciones(T[] arre){
        int minPos;
        int n=arre.length;
        T aux;
        int[] contComp = new int[1]; // contador como array de un solo elemento

        for(int i=0;i<n;i++){
            minPos=i;
            for(int j=i+1;j<n;j++){
                if(comparar(arre[j],arre[minPos],contComp)<0){
                    contComp[0]++;
                    minPos=j;
                }
            }
            aux=arre[i];
            arre[i]=arre[minPos];
            arre[minPos]=aux;

        }
        return contComp[0]; // devolver el primer elemento del array
    } 
    
    
    public <T extends Comparable <T>> void insertionSort(T[] arre) {
        int n = arre.length;
        for (int i=1; i<n; i++) {
            T aux=arre[i];
            int j=i-1;
            while (j>=0 && arre[j].compareTo(aux)>0) {
                arre[j+1]=arre[j];
                j=j-1;
            }
            arre[j+1]=aux;
        }
    }
    
    public <T extends Comparable <T>> int insertionSortComparaciones(T[] arre) {
        int n = arre.length;
        int[] contComp = new int[1];
        for (int i = 1; i < n; i++) {
            T aux = arre[i];
            int j = i - 1;
            while (j >= 0 && comparar(arre[j],aux,contComp)>0) {
                contComp[0]++;
                arre[j + 1] = arre[j];
                j = j - 1;
            }
            arre[j + 1] = aux;
        }
        return contComp[0];
    }

    
    public <T extends Comparable<T>> void bubbleSort(T[] arre) {
        int n=arre.length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n-i-1; j++) {
                if (arre[j].compareTo(arre[j+1])>0) {
                    T aux = arre[j];
                    arre[j] = arre[j+1];
                    arre[j+1] = aux;
                }
            }
        }
    }
    public <T extends Comparable<T>> int bubbleSortComparaciones(T[] arre) {
        int n = arre.length;
        int[] contComp = new int[1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparar(arre[j],arre[j+1],contComp) > 0) {
                    contComp[0]++;
                    T aux = arre[j];
                    arre[j] = arre[j + 1];
                    arre[j + 1] = aux;
                }
            }
        }
        return contComp[0];
    }
    
    
    public <T extends Comparable <T>>void quicksort(T [] arre){
        quicksort(arre,0,arre.length-1);
    }

    private <T extends Comparable <T>> void quicksort(T [] arre, int min, int max){
    
        if(min<max){
            int izq=min, der=max;
            //int pivoteIndex = min + (max - min) / 2;
            int pivoteIndex=(int) ((Math.random()*(max-min))+min);
            T pivote = arre[pivoteIndex];
            while(izq<=der){
                while(arre[izq].compareTo(pivote)<0)
                    izq++;
                while(arre[der].compareTo(pivote)>0)
                    der--;
                if(izq<=der){
                    swap(arre,izq,der);
                    izq++;
                    der--;
                }
            }
            if(min<der){
                quicksort(arre,min,der);
            }
            if (izq < max) {
                quicksort(arre, izq, max);
            }
        }
    
    }

    private <T> void swap(T[] arre, int pos1, int pos2){
        T aux=arre[pos2];
        arre[pos2]=arre[pos1];
        arre[pos1]=aux;
    
    }
    
    public <T extends Comparable <T>>int quicksortComparaciones(T [] arre){
        return quicksortComparaciones(arre,0,arre.length-1);
    }

    private <T extends Comparable <T>> int quicksortComparaciones(T [] arre, int min, int max){
        int[] contComp = new int[1];
        if(min<max){
            int izq=min, der=max;
            //int pivoteIndex = min + (max - min) / 2;
            int pivoteIndex=(int) ((Math.random()*(max-min))+min);
            T pivote = arre[pivoteIndex];
            while(izq<=der){
                while(comparar(arre[izq],pivote,contComp)<0){
                    contComp[0]++;
                    izq++;
                }
                while(comparar(arre[der],pivote,contComp)>0){
                    contComp[0]++;
                    der--;
                }
                if(izq<=der){
                    swapComparaciones(arre,izq,der);
                    izq++;
                    der--;
                }
            }
            if(min<der){
                quicksort(arre,min,der);
            }
            if (izq < max) {
                quicksort(arre, izq, max);
            }
        }
        return contComp[0];
    
    }

    private <T> void swapComparaciones(T[] arre, int pos1, int pos2){
        T aux=arre[pos2];
        arre[pos2]=arre[pos1];
        arre[pos1]=aux;
    
    }

    
    
    private <T extends Comparable <T>>void merge(T arr[], int izq, int centro, int der) {
        int n1 = centro - izq + 1;
        int n2 = der - centro;
        
        List<T> arreIzq = new ArrayList<>();
        List<T> arreDer = new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            arreIzq.add(arr[izq + i]);
        for (int j = 0; j < n2; ++j)
            arreDer.add(arr[centro + 1 + j]);

        int i = 0, j = 0;
        int k = izq;
        while (i < n1 && j < n2) {
            if (arreIzq.get(i).compareTo(arreDer.get(j))<=0) {
                arr[k] = arreIzq.get(i);
                i++;
            } else {
                arr[k] = arreDer.get(j);
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = arreIzq.get(i);
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = arreDer.get(j);
            j++;
            k++;
        }
        /*for(int i=0; i<max-min+1; i++){
            datos[min]=temporal[i];
            min++;
        }*/
    }

    private <T extends Comparable <T>> void mergeSort(T arr[], int izq, int der) {
        if (izq < der) {
            int centro = (izq + der) / 2;

            mergeSort(arr, izq, centro);
            mergeSort(arr, centro + 1, der);

            merge(arr, izq, centro, der);
        }
    }
    public <T extends Comparable <T>> void mergeSort(T arr[]){
        mergeSort(arr,0,arr.length-1);
    }
    
    
        private <T extends Comparable <T>> void mergeComparaciones(T arr[], int izq, int centro, int der, int [] contComp) {
        int n1 = centro - izq + 1;
        int n2 = der - centro;
        
        List<T> arreIzq = new ArrayList<>();
        List<T> arreDer = new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            arreIzq.add(arr[izq + i]);
        for (int j = 0; j < n2; ++j)
            arreDer.add(arr[centro + 1 + j]);

        int i = 0, j = 0;
        int k = izq;
        while (i < n1 && j < n2) {
            if (comparar(arreIzq.get(i),arreDer.get(j),contComp)<=0 ) {
                contComp[0]++;
                arr[k] = arreIzq.get(i);
                i++;
            } else {
                contComp[0]++;
                arr[k] = arreDer.get(j);
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = arreIzq.get(i);
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = arreDer.get(j);
            j++;
            k++;
        }
    }

    private <T extends Comparable <T>> void mergeSortComparaciones(T arr[], int izq, int der,int[] contComp) {
        if (izq < der) {
            int centro = (izq + der) / 2;

            mergeSortComparaciones(arr, izq, centro,contComp);
            mergeSortComparaciones(arr, centro + 1, der,contComp);
            
            mergeComparaciones(arr, izq, centro, der,contComp);
        }
    }
    public <T extends Comparable <T>> int mergeSortComparaciones(T arr[]){
        int[] contComp = new int[1]; // Inicializar el contador
        mergeSortComparaciones(arr, 0, arr.length - 1, contComp);
        return contComp[0]; // Devolver el contador
    }

    
    public static void main(String[] args) {
        /*int [] a={1,5,88,2,6,33,77,12};

        for(int i=0;i<a.length-1;i++)
            System.out.println("\n "+a[i]);
        selectionSort(a);
        System.out.println("\nArreglo ordenado");
        for(int i=0;i<a.length-1;i++)
            System.out.println("\n "+a[i]);
        */
        
        //Prubea de método quicksort
        EDA1 eda=new EDA1();
        Integer [] arre={5,22,7,1,17};
        for(int i=0;i<arre.length;i++)
            System.out.println("\n "+arre[i]);
        eda.quicksort(arre);
        for(int i=0;i<arre.length;i++)
            System.out.println("\n "+arre[i]);
        
    }
    
}
