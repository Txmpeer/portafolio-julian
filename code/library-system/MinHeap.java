/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Heaps;

/**
 *
 * @author carlitos73
 */
public class MinHeap <T extends Comparable <T>>{
    int cont;
    Comparable <T>[] datos=new Comparable[3];

    public MinHeap(T dato) {
        cont=1;
        datos[1]=dato;
    }
    
    private void expande(){
        Comparable<T> [] nuevo=new Comparable[datos.length*2];
        for(int i=1;i<=cont;i++)
            nuevo[i]=datos[i];
        datos=nuevo;
    }
    
    
    public T findMin() throws Exception{
        if(cont>0)
            return (T) datos[1];
        else
            throw new Exception("No encontró el mínimo");
    }
    
    public void inserta(T nuevo){
        if(cont==datos.length-1)
            expande();
        datos[cont+1]=nuevo;
        int actual=cont+1, papa=actual/2;
        while((papa!=0 && datos[actual].compareTo((T) datos[papa])<=0)){
            swap(actual,papa);
            actual=papa;
            papa=actual/2;
        }
        cont++;
    }
    
    private void swap(int pos1,int pos2){
        T aux=(T) datos[pos1];
        datos[pos1]=datos[pos2];
        datos[pos2]=aux;
    }
    
    public String toString(){
        int i=1;
        StringBuilder sb=new StringBuilder();
        while(i<=cont){
            sb.append(datos[i]+", ");
            i++;
        }
        return sb.toString();
    }
    /*
    public void borraMinChafa(){
        //T aux=(T) datos[cont];
        datos[1]=datos[cont];
        datos[cont]=null;
        cont--;
        int pos=1;
        while((pos*2)+1<datos.length){
            if((datos[pos*2]!=null && datos[pos*2+1]==null && datos[pos*2].compareTo((T) datos[pos])<0))
                swap(pos,pos*2);
            else if(datos[pos*2]==null && datos[pos*2+1]!=null && datos[pos*2+1].compareTo((T)datos[pos])<0)
                swap(pos,pos*2+1);
            else{
                if(datos[pos*2]!=null && datos[pos*2+1]==null && datos[pos*2].compareTo((T)datos[pos])<0)
                    swap();
                if()
                    swap();
            }
        }
    }*/
    
    public T borraMin(){
        T temp;
        if(cont==0)
            return null;
        temp=(T)datos[1];
        datos[1]=datos[cont];
        cont--;
        int actual=1;
        while(actual*2+1<cont || actual*2+1<cont){ //Mientras existe un hijo derecho (no se sale)
            if(actual*2<cont && actual*2+1<cont){ //Dos hijos
                if(datos[actual*2+1].compareTo((T) datos[actual*2])<0){ //Si el derecho es menor al izquierdo
                    swap(actual,actual*2+1);
                    actual=actual*2+1;
                }
                else if(datos[actual*2+1].compareTo((T) datos[actual*2])>0){ //Si el izquierdo es menor al derecho
                        swap(actual,actual*2);
                        actual=actual*2;
                    }
            } else if(actual*2<cont){//Un hijo (En este caso siempre es el izquierdo)
                    swap(actual,actual*2);
                    actual=actual*2;
                }
        }
        return temp;
    }
    
    public void sort(int arr[]) {
        int n = arr.length;

        // Construir heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extraer elementos uno por uno del heap
        for (int i = n - 1; i >= 0; i--) {
            // Mover el nodo raíz actual al final
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Llamar a heapify en el heap reducido
            heapify(arr, i, 0);
        }
    }

    public void heapify(int arr[], int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] < arr[smallest])
            smallest = left;

        if (right < n && arr[right] < arr[smallest])
            smallest = right;

        if (smallest != i) {
            int swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            heapify(arr, n, smallest);
        }
    }
    
    
    public static void main(String[] args){
        
        MinHeap mh=new MinHeap(40);
        mh.inserta(100);
        mh.inserta(50);
        mh.inserta(150);
        mh.inserta(300);
        mh.inserta(75);
        mh.inserta(1000);
        mh.inserta(400);
        
        
        System.out.println(mh.toString());
    }
    
}
