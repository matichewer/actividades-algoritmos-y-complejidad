import java.util.*;

// O(n²)


public class LIS {

	public static int[] lis(List<Tupla> lista_expansiones, int n) {

        int lis[] = new int[n];
        int pos[] = new int[n];
        int i, j, max = 0, maxIndex = -1;

        for (i = 0; i < n; i++) {
            lis[i] = 1;
            pos[i] = -1;
        }

        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
				Tupla tupla_i = lista_expansiones.get(i);
				Tupla tupla_j = lista_expansiones.get(j);
                if (tupla_i.getL() > tupla_j.getL() 
						&& (tupla_i.getW() != tupla_j.getW())
						&& lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    pos[i] = j;
                }
            }
            if (max < lis[i]) {
                max = lis[i];
                maxIndex = i;
            }
        }

        int[] result = new int[max];
        for (i = max - 1; i >= 0; i--) {
            result[i] = lista_expansiones.get(maxIndex).getIndice();
            maxIndex = pos[maxIndex];
        }

        return result;
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

		int resultado [] = lis(lista_expansiones, lista_expansiones.size());
		int cant = resultado.length;
		if(cant==0){
			System.out.print(0);
		} else{
			System.out.println(cant -1);
			for (int i=1; i<cant; i++) 
				System.out.print(resultado[i] + " ");
		}
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