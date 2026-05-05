/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eda;

import java.util.Random;

/**
 *
 * @author carlitos73
 */
public class EDA  <T extends Comparable <T>>{

    public static void selectionSort(int[] arre){
        int minPos;
        int n=arre.length;
        int aux;
        
        for(int i=0;i<n;i++){
            minPos=i;
            for(int j=i++;j<n;j++){
                if(arre[j]<arre[minPos])
                    minPos=j;
            }
            aux=arre[i];
            arre[i]=arre[minPos];
            arre[minPos]=aux;
            
        }
    }
    
    public static void insertionSnt(int[]arre){
        //Alta de variables básicas
        int n=arre.length;
        int aux;
        for(int i=0;i<n;i++){
            int j=i+1;
            while(arre[i]<arre[j-1] && j>0){
                aux=arre[i];
                arre[j-1]=arre[i];
                arre[i]=arre[j-1];
                //swap(arre,j,j-1)
                j--;
            }
        }
    }
    
    public static void insertionSort2(int[] arre) {
    int n = arre.length;
    for (int i = 1; i < n; i++) {
        int aux = arre[i];
        int j = i - 1;
        while (j>=0 && arre[j] > aux) {
            arre[j + 1] = arre[j];
            j = j - 1;
        }
        arre[j + 1] = aux;
    }
}

    
    public static <T extends Comparable<T>> void bubbleSort(T[] arre) {
        int n = arre.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arre[j].compareTo(arre[j + 1]) > 0) {
                    T aux = arre[j];
                    arre[j] = arre[j + 1];
                    arre[j + 1] = aux;
                }
            }
        }
    }

    
    public static <T extends Comparable <T>>void quicksort(T [] arre){
        quicksort(arre,0,arre.length-1);
    }
    
    private static <T extends Comparable <T>> void quicksort(T [] arre, int min, int max){
        
        if(min<max){
            int izq=min, der=max, pivote=pivote(min,max);
            while(izq<der){
                if(arre[izq].compareTo(arre[pivote])>0 && arre[der].compareTo(arre[pivote])<0){
                    swap(arre,izq,der);
                    izq++;
                    der--;
                }
                else{
                    if(arre[izq].compareTo(arre[pivote])<=0)
                        izq++;
                    if(arre[der].compareTo(arre[pivote])>=0)
                        der--;
                }
            }
            if(arre[der].compareTo(arre[pivote])>0)
                der--;
            swap(arre,der,pivote);
            quicksort(arre,min,der-1);
            quicksort(arre,der+1,max);
        }
        
    }
    
    private static <T> void swap(T[] arre, int pos1, int pos2){
        T aux=arre[pos2];
        arre[pos2]=arre[pos1];
        arre[pos1]=aux;
        
    }
    private static int pivote(int min, int max){
        return (int) ((Math.random()*(max-min))+min);
    }
    
    
    public static void merge(int arr[], int izq, int centro, int der) {
        int n1 = centro - izq + 1;
        int n2 = der - centro;

        int arreIzq[] = new int[n1];
        int arreDer[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            arreIzq[i] = arr[izq + i];
        for (int j = 0; j < n2; ++j)
            arreDer[j] = arr[centro + 1 + j];

        int i = 0, j = 0;
        int k = centro;
        while (i < n1 && j < n2) {
            if (arreIzq[i] <= arreDer[j]) {
                arr[k] = arreIzq[i];
                i++;
            } else {
                arr[k] = arreDer[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = arreIzq[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = arreDer[j];
            j++;
            k++;
        }
        /*for(int i=0; i<max-min+1; i++){
            datos[min]=temporal[i];
            min++;
        }*/
    }

    public static void mergeSort(int arr[], int izq, int der) {
        if (izq < der) {
            int centro = (izq + der) / 2;

            mergeSort(arr, izq, centro);
            mergeSort(arr, centro + 1, der);

            merge(arr, izq, centro, der);
        }
    }

    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        // Elegir un índice aleatorio como pivote
        int randomIndex = new Random().nextInt(high - low + 1) + low;
        swap(arr, randomIndex, high);

        int pivot = arr[high];
        int i = low-1;

        for (int j=low; j<high; j++) {
            if (arr[j]<pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, high);
        return i+1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        /*int [] a={1,5,88,2,6,33,77,12};

        for(int i=0;i<a.length-1;i++)
            System.out.println("\n "+a[i]);
        selectionSort(a);
        System.out.println("\nArreglo ordenado");
        for(int i=0;i<a.length-1;i++)
            System.out.println("\n "+a[i]);
        
        
        //Prubea de método quicksort
        int [] arre={5,22,7,1,17};
        for(int i=0;i<arre.length;i++)
            System.out.println("\n "+arre[i]);
        mergeSort(arre,0,arre.length-1);
        for(int i=0;i<arre.length;i++)
            System.out.println("\n "+arre[i]);*/
        
        
        int[] arr = {10, 7, 9};
        int N = arr.length;

        mergeSort(arr, 0, N - 1);

        System.out.println("Arreglo ordenado:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        
    }
    
}
