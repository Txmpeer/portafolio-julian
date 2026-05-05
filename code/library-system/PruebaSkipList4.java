/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkipList;

/**
 *
 * @author carlitos73
 */
public class PruebaSkipList4 {
    
    public static void main(String[] args) {
        Skiplist2<Integer> lista;
        double promComp = 0;
        double promNumListas = 0;
        double promCompDespues = 0;
        double promNumListasDespues=0;
        int numDatos = 10000;
        int numPruebas = 1000;
        int numBuscarFalso = 460;
        int porcentajeBorrar = 2;
        int[] nivelesProm = new int[(int)(Math.log(numDatos) / Math.log(2))+1];
        int[] nivelesPromDespues = new int[(int)(Math.log(numDatos) / Math.log(2))+1];
        
     
        for(int i = 0; i < numPruebas; i++){
            lista = new Skiplist2<>();
            for(int j = 1; j <= numDatos; j++)
                lista.inserta(j);
            System.out.println(lista.toString());
            promComp += lista.buscaComp(numBuscarFalso);
            promNumListas += lista.getNumListas();
            

            int[] niveles = lista.datosPorNivel();
            for(int k = 0; k < niveles.length; k++)
                nivelesProm[k] += niveles[niveles.length - k - 1];

            for(int l = 0; l <= numDatos; l = l + porcentajeBorrar)
                lista.borra(l);
            
            promCompDespues += lista.buscaComp(numBuscarFalso);                    
            
            niveles = lista.datosPorNivel();
            for(int k = 0; k < niveles.length; k++)
                nivelesPromDespues[k] += niveles[niveles.length - k - 1];
            promNumListasDespues+=lista.getNumListas();
        }
        
        System.out.println("Comparaciones y niveles esperados antes de borrar: " + (int)((Math.log(numDatos) / Math.log(2)) + 1) );
        System.out.println("Comparaciones Promedio de Busqueda antes de borrar: " + promComp / numPruebas);
        System.out.println("Niveles promedio de listas antes de borrar: " + promNumListas / numPruebas);
        System.out.println("\nCantidad de elementos promedio por nivel antes de borrar:");
        for(int i = 0; i < nivelesProm.length; i++)
            System.out.println("Piso [" + (i + 1) + "]: " + (double)nivelesProm[i] / numPruebas);
        
        System.out.println("\n\nComparaciones y niveles esperados despues de borrar: " + (int)((Math.log(numDatos/porcentajeBorrar) / Math.log(2)) + 1) );
        System.out.println("Comparaciones Promedio de Busqueda despues de borrar: " + promCompDespues / numPruebas);
        //System.out.println("Niveles promedio de listas después de borrar: " + promNumListasDespues / numPruebas);
        System.out.println("\nCantidad de elementos promedio por nivel despues de borrar:");
        for(int i = 0; i < nivelesPromDespues.length; i++)
            System.out.println("Piso [" + (i + 1) + "]: " + (double)nivelesPromDespues[i] / numPruebas);
    }
    
}
