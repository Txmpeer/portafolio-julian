/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pilas;

import java.util.ArrayList;

/**
 *
 * @author carlitos73
 */
public class ManejadorPilasGenerico {
    //Inciso 14
    public static int cuentaElem(PilaADT pila){
        if(pila.isEmpty())
            throw new ExceptionColeccionVacia("La pila está vacía");
        PilaA temp=new PilaA();
        int cont=0;
        while(!pila.isEmpty()){
            temp.push(pila.pop());
            cont++;
        }
        while(!temp.isEmpty())
            pila.push(temp.pop());
        return cont;
    }
    
    //Inciso 15
    public static boolean siContiene(PilaADT pila1,PilaADT pila2){
        if(pila1.isEmpty() || pila2.isEmpty())
            throw new ExceptionColeccionVacia("Alguna de las dos pilas está vacía");
        boolean res=false;
        ArrayList lista1=new ArrayList();
        ArrayList lista2=new ArrayList();
        
        while(!pila1.isEmpty())
            lista1.add(pila1.pop());
        while(!pila2.isEmpty())
            lista2.add(pila2.pop());
        
        if(lista1.size()>=lista2.size()){
            boolean band=true;
            int i=0;
            while(i<lista2.size()&&band){
                if(!lista1.contains(lista2.get(i)))
                    band=false;
                else
                    i++;
            }
            if(i==lista2.size())
                res=true;
            
            for(int j=0;j<lista1.size();j++)
                pila1.push(lista1.get(j));
            for(int k=0;k<lista2.size();k++)
                pila2.push(lista2.get(k));
        }
        
        return res;
    }
    
    //Inciso 16
    
    //Inciso 20
    
    //Inciso 21 a)
    public static void quitaNElem(PilaADT pila,int n){
        if(pila.isEmpty())
            throw new ExceptionColeccionVacia("La pila está vacía");
        PilaA pilaAux=new PilaA();
        int cont=0;
        while(!pila.isEmpty() && cont<n){
            pilaAux.push(pila.pop());
            cont++;
        }
        if(cont<n){
            while(!pilaAux.isEmpty())
                pila.push(pilaAux.pop());
        }
    }
    
    //Ejercicio equals
    
    
    public static void main(String[] args){
        // 15)
        PilaA<Integer> pila1=new PilaA();
        pila1.push(2);
        pila1.push(1);
        pila1.push(39);
        pila1.push(520);
        try{
            int cont= ManejadorPilasGenerico.cuentaElem(pila1);
            System.out.println("\nNúmero de elementos: "+ cont);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        // 16)
        PilaA<Integer> pila2=new PilaA();
        pila2.push(2);
        pila2.push(1);
        pila2.push(39);
        pila2.push(520);
        
        PilaA<Integer> pila3=new PilaA();
        pila3.push(2);
        pila3.push(1);
        pila3.push(39);
        pila3.push(520);
        pila3.push(12);
        
        try{
            boolean contiene=ManejadorPilasGenerico.siContiene(pila2, pila3);
            System.out.println("\nPila 2 contiene a pila 3: "+ contiene);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        // 21 a)
        PilaA<Integer> pila4=new PilaA();
        pila4.push(2);
        pila4.push(3);
        pila4.push(4);
        pila4.push(5);
        try{
            ManejadorPilasGenerico.quitaNElem(pila4, 3);
            System.out.println("\nPila 4 nueva: "+pila4.toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
