/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolAVL;

/**
 *
 * @author carlitos73
 */
public class ArbolAVL <T extends Comparable <T>>{
    NodoAVL<T> raiz;
    
    public ArbolAVL(T elem){
        NodoAVL<T> inicio=new NodoAVL<T>(elem);
        raiz=inicio;
    }

    public NodoAVL<T> getRaiz() {
        return raiz;
    }
    
    
    public void inserta(T elem){
        NodoAVL<T> nuevo= new NodoAVL<T>(elem),actual=raiz,papa=actual;
        
        int cont=0;
        
        boolean inserta=true;
        while(actual!=null && inserta){
            papa=actual;
            if(elem.compareTo(actual.getElem())<0)
                actual=actual.getIzq();
            else
                if(elem.compareTo(actual.getElem())>0)
                    actual=actual.getDer();
                else
                    inserta=false;
            
        }
        if(!inserta)
            return;
        papa.cuelga();
        cont++;
        
        actual=nuevo;
        boolean termine=false;
        
        while(actual.getPapa()!=null && !termine){
            if(actual.getDer().compareTo(actual.getPapa().getDer())<0)
                actual.getPapa().getFe()-=1;
            else
                actual.papa.fe+=1;
            if(actual.papa.fe=2){
                actual=rota(actual.papa);
                termine=true;
            }
            if(actual.papa.fe!=0)
                termine=true;
            actual=actual.papa;
        }
        
    }
    
    public void borraAVL(T elem){
        NodoAVL<T> n=busca(elem);
        NodoAVL<T> su=n.der();
        if(n.izq!=null && n.der!=null){
            while(su!=null)
                su=su.getIzq();
            n=su;
        }
        borra(elem) tradicional;
        //Ahora balancear a partir de N
        
        actual=nuevo;
        boolean termine=false;
        
        while(actual.getPapa()!=null && !termine){
            if(actual.getDer().compareTo(actual.getPapa().getDer())<0)
                actual.papa.fe+=1;
            else
                actual.papa.fe-=1;
            if(actual.papa.fe=2){
                actual=rota(actual.papa);
                termine=true;
            }
            if(actual.papa.fe==0)
                termine=true;
            actual=actual.papa;
        }
        
    }
    
    private NodoAVL<T> rota(NodoAVL<T> actual){
        if(actual.getFe()=-2 && actual.getIzq()<1){ //izq-izq
            papa=actual.papa;
            alfa=actual;
            beta=alfa.izq;
            D=alfa.der;
            gamma=beta.izq;
            A=gamma.izq;
            B=gamma.der;
            C=beta.der;
            papa.cuelga(beta);
            alfa.cuelga(C);
            alfa.cleuga(D);
            return beta;
        }
    }
    
    
    
}
