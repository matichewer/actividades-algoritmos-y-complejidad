import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class LIS2 {

    public static List<Integer> lis(List<Tupla> arr) {
        int n = arr.size();
        int[] tailIndices = new int[n];
        int[] prevIndices = new int[n];
    
        Arrays.fill(prevIndices, -1);
    
        int length = 1;
        tailIndices[0] = 0;
    
        for (int i = 1; i < n; i++) {
			Tupla tupla_i = arr.get(i);
			Tupla tupla_j = arr.get(tailIndices[0]);
			Tupla tupla_k = arr.get(tailIndices[length - 1]);
            if (tupla_i.getL() < tupla_j.getL()) {
                tailIndices[0] = i;
            } else if (tupla_i.getL() > tupla_k.getL()) {
                prevIndices[i] = tailIndices[length - 1];
                tailIndices[length++] = i;
            } else {
                int[] tempArray = new int[length];
                for (int j = 0; j < length; j++) {
                    Tupla tupla_l = arr.get(tailIndices[j]);
                    tempArray[j] = tupla_l.getL();
                }
                int index = Arrays.binarySearch(tempArray, tupla_i.getL());
                if (index < 0) {
                    index = -(index + 1);
                }
                prevIndices[i] = tailIndices[index - 1];
                tailIndices[index] = i;
            }
        }
    
        List<Integer> lis = new ArrayList<>(length);
        for (int i = tailIndices[length - 1]; i >= 0; i = prevIndices[i]) {
            lis.add(0,arr.get(i).getIndice());
        }
    
        return lis;
    }


    public static void main(String[] args){

        // LEO DATOS
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Tupla tupla_inicial = new Tupla(sc.nextInt(), sc.nextInt(), 0);
        List<Tupla> lista_expansiones = new ArrayList<Tupla>();
        lista_expansiones.add(tupla_inicial);
        for(int i = 1; i < n+1; i++) {
            Tupla tupla = new Tupla(sc.nextInt(), sc.nextInt(), i);
            // Solo los guardo si son mayores que el inicial
            if(tupla.getW()>tupla_inicial.getW() &&  tupla.getL()>tupla_inicial.getL()) {
                lista_expansiones.add(tupla);
            }
        }
        sc.close();
        //System.out.println(lista_expansiones);
        Collections.sort(lista_expansiones);
        //System.out.println(lista_expansiones);
        // FIN LECTURA DATOS

        List<Integer> resultado = lis(lista_expansiones);
        System.out.println(resultado);
        }
}



class Tupla implements Comparable<Tupla>{
int indice;
int w;
int l;

public Tupla(int ancho, int largo, int pos) {
    w = ancho;
    l = largo;
    this.indice = pos;
}

public int getIndice() {
    return indice;
}

public int getW() {
    return w;
}

public int getL() {
    return l;
}

@Override
public String toString() {
    return "(" + w + "," + l + "," + indice + ")";
}

@Override
public int compareTo(Tupla tupla) {
    if(this.getW() > tupla.getW())
        return 1;
    else 
        return -1;		
}	
}

class TuplasComparator implements Comparator<Tupla> {
    public int compare(Tupla t1, Tupla t2) {
        return t1.compareTo(t2);
    }
}



