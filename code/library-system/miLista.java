/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listas;

/**
 *
 * @author carlitos73
 */
public class miLista <T>{
    Nodo<T> cabeza;
    int cont;
    
    public miLista(){
        cabeza=new Nodo(null);
        cont=0;
    }
    
    public void agregaNodoAlInicio(T dato){
        Nodo<T> nuevo=new Nodo(dato);
        nuevo.setSiguiente(cabeza.getSiguiente());
        cabeza.setSiguiente(nuevo);
        cont++;
    }
    
    public String toString(){
        Nodo<T> temp=cabeza.getSiguiente();
        StringBuilder sb=new StringBuilder();
        if(temp!=null){
            while(temp!=null){
                sb.append(temp.getDato());
                temp=temp.getSiguiente();
            }
        }
        return sb.toString();
    }
    
    private int cuentaNodos(Nodo<T> actual,int cont){
        if(actual!=null){
            cont++;
            return cuentaNodos(actual.getSiguiente(),cont);
        }
        else
            return cont;
    }
    
    public int cuentaNodosR(){
        return cuentaNodosR(cabeza.getSiguiente());
    }
    
    private int cuentaNodosR(Nodo<T> actual){
        if(actual==null)
            return 0;
        return cuentaNodosR(actual.getSiguiente())+1;
    }
    
    //Invertir una lista
    // 1) Cambiando los apuntadores
    
    //En una lista, sin tocar los apuntadores invertir la lista
    
    public void invierteR(){
        Nodo <T> temp=invierteR(cabeza.getSiguiente());
        temp.setSiguiente(null);
    }
    
    private Nodo<T> invierteR(Nodo<T> actual){
        if(actual.getSiguiente()==null){
            cabeza.setSiguiente(actual);
            return actual;
        }
        Nodo<T> temp= invierteR(actual.getSiguiente());
        temp.setSiguiente(actual);
        return actual;
    }
    
    public void invierteValoresR(){
        invierteValoresR(cabeza.getSiguiente());
    }
    
    private Nodo<T> invierteValoresR(Nodo<T> actual){
        if(actual==null)
            return cabeza.getSiguiente();
        else{
            T dato=actual.getDato();
            Nodo<T> temp=invierteValoresR(actual.getSiguiente());
            temp.setDato(dato);
            return temp.getSiguiente();
            
        }
    }
    /*
    public String permutacion(String cadena){
        StringBuilder sB=new StringBuilder();
        
        for(int i=0;i<cadena.length();i++){
            char arre=cadena.charAt(i);
            String sub= cadena.substring(i+1, cadena.length()-1);
            
            
        }
    } */
    
    public boolean esPalindromo(){
        Nodo<T> nodo= esPalindromo(cabeza.getSiguiente());
        return nodo==null;
    }
    
    public Nodo<T> esPalindromo(Nodo<T> actual){
        if(actual==null)
            return cabeza.getSiguiente();
        else{
            T dato=actual.getDato();
            Nodo<T> temp= esPalindromo(actual.getSiguiente());
            if(dato!=temp.getDato())
                return new Nodo<T>(null);
            return temp.getSiguiente();
        }
    }

    public static <T> int distancia(String s1,String s2,int cont) {
    	if(s1.length()==0 || s2.length()==0)
            return cont+=s1.length()+s2.length(); //Contador de pasos finales
    	else {
            if(s1.charAt(0)==s2.charAt(0))
		return distancia(s1.substring(1), s2.substring(1), cont);
            else {
		int inserta =distancia(s1.substring(1), s2, cont+1); // Inserta
		int borra= distancia(s1, s2.substring(1), cont+1); // Borra
		int cambia= distancia(s1.substring(1), s2.substring(1), cont+1); //Cambia
		int m= Math.min(borra, cambia);
		int min=Math.min(m, inserta);
		return min;
            }
	}
    	
    }
    
        
    public static String editDistancePasos(String s1, String s2, String arreglo){
        if(s1.length() == 0 && s2.length() == 0)
            return arreglo;
        String num = getNum(arreglo);
        String aumentaContador = Integer.toString(Integer.parseInt(num) + 1);
        if(s1.length() == 0){
            for(int i = 0; i < s2.length(); i++)
                arreglo = arreglo + "[Borra] ";
            return Integer.toString(Integer.parseInt(num) + s2.length()) + arreglo.substring(num.length()); 
        }
        if(s2.length() == 0){
            for(int i = 0; i < s1.length(); i++)
                arreglo = arreglo + "[Inserta] ";
            return Integer.toString(Integer.parseInt(num) + s1.length()) + arreglo.substring(num.length()); 
        }
        if(s1.charAt(0) == s2.charAt(0)){
            return editDistancePasos(s1.substring(1), s2.substring(1), aumentaContador + arreglo.substring(num.length()) + "[Nada] ");
        }
        String inserta = editDistancePasos(s1.substring(1), s2, aumentaContador + arreglo.substring(num.length()) + "[Inserta] ");
        String borra = editDistancePasos(s1, s2.substring(1), aumentaContador + arreglo.substring(num.length()) + "[Borra] ");
        String cambia = editDistancePasos(s1.substring(1), s2.substring(1), aumentaContador + arreglo.substring(num.length()) + "[Cambia] ");
        int insertaNum = Integer.parseInt(getNum(inserta)), borraNum = Integer.parseInt(getNum(borra)), cambiaNum = Integer.parseInt(getNum(cambia));
        if(insertaNum <= borraNum && insertaNum <= cambiaNum)
            return inserta;
        if(borraNum <= insertaNum && borraNum <= cambiaNum)
            return borra;
        return cambia;
    }

    public static String getNum(String s){
        int i = 0;
        String resp = "";
        while(i < s.length() && Character.isDigit(s.charAt(i))){
            resp += s.charAt(i);
            i++;
        }
        return resp;
    }
        
    public static void main(String[]args){
        /*
        miLista lista=new miLista();
        
        //Nodos
        //Nodo n1=new Nodo(5);
        //Nodo n2=new Nodo(4);
        Nodo n3=new Nodo(3);
        Nodo n4=new Nodo(2);
        Nodo n5=new Nodo(1);
        
        //lista.agregaNodoAlInicio(n1);
        //lista.agregaNodoAlInicio(n2);
        lista.agregaNodoAlInicio(n3);
        lista.agregaNodoAlInicio(n4);
        lista.agregaNodoAlInicio(n5);
        
        System.out.println("Nodos: \n"+lista.toString());
        lista.invierteValoresR();
        System.out.println("Nodos: \n"+lista.toString()); */
        
        String s1="Ho";
        String s2="jo";
        int distancia= distancia(s1, s2, 0);
        System.out.print("La distancia entre s1 y s2 es: "+distancia);
        /*
        long t1 = System.currentTimeMillis();
        String respuesta = editDistancePasos(s1, s2, "0");
        long t2 = System.currentTimeMillis();
        
        
        System.out.println(respuesta.substring(getNum(respuesta).length()));
        System.out.println("Segundos: " + (t2 - t1)/1000);*/
    
    }

}
