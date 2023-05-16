import java.util.*;

public class Entrega {

	public static void main(String Args[]) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Tupla> lista_expansiones = new ArrayList<Tupla>();
		Tupla tupla_inicial = new Tupla(sc.nextInt(),sc.nextInt(),-1,0);
		lista_expansiones.add(tupla_inicial);
		for(int i=1; i < n+1; i++) {
			Tupla tupla = new Tupla(sc.nextInt(),sc.nextInt(),-1,i);
			if(tupla.getW()>tupla_inicial.getW() && tupla.getL()>tupla_inicial.getL()) {
				lista_expansiones.add(tupla);
			}
		}
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		sc.close();
		
		if(lista_expansiones.size() == 1) {
			System.out.println(0);
		}else {
			lista_expansiones.sort(new TuplasComparator());
			getSecuencia(lista, lista_expansiones,1);
			List<Tupla> secuencia = generarSecuencia(lista,lista_expansiones);

            // Muestro solucion
			System.out.println(secuencia.size());
			for(Tupla tupla : secuencia) 
				System.out.print(tupla.getPosOriginal() + " ");
			
		}
		
}

	public static int getMayor(List<Tupla> lista_expansiones,List<Integer> result, int i) {
		int indice_max = -1;
		int max = 1;
			for(int j = 0; j<=i; j++) {
				if(lista_expansiones.get(j).getW() < lista_expansiones.get(i+1).getW() &&
                    result.get(j)>=max && lista_expansiones.get(j).getL() < lista_expansiones.get(i+1).getL()){
					max = result.get(j);
					indice_max = j;
				}
			}
		return indice_max;
	}
	
	public static void getSecuencia(List<Integer> lista,List<Tupla> lista_expansiones,int indice) {
		if(indice != lista_expansiones.size()) {
			int pos_max = getMayor(lista_expansiones,lista,indice-1);
			lista_expansiones.get(indice).setPosAnt(pos_max);
			int valor_indice = 1 + lista.get(pos_max);
			lista.add(valor_indice);
			getSecuencia(lista, lista_expansiones, indice+1);
		}
	}
	
	public static List<Tupla> generarSecuencia(List<Integer> lista,List<Tupla> lista_expansiones) {
		List<Tupla> toReturn = new ArrayList<Tupla>();
		int indice_max = 1;
		int max = 1;
		for(int i = lista.size()-1; i>=0; i--) {
			if(lista.get(i)>max) {
				indice_max = i;
				max = lista.get(i);
			}
		}
		Tupla tupla_ant = lista_expansiones.get(indice_max);
		while(tupla_ant.getPosAnt() != -1) {
			toReturn.add(0, tupla_ant);
			tupla_ant = lista_expansiones.get(tupla_ant.getPosAnt());
		}
		return toReturn;
	}
}

class Tupla implements Comparable<Tupla>{
	int pos_inicial;
	int w;
	int l;
	int pos_ant;

	public Tupla(int ancho, int largo, int pos_ant, int pos_inicial) {
		w = ancho;
		l = largo;
		this.pos_ant = pos_ant;
		this.pos_inicial = pos_inicial;
	}
	
	public int getPosOriginal() {
		return this.pos_inicial;
	}

	public int getW() {
		return w;
	}
	
	public int getL() {
		return l;
	}
	
	public void setPosAnt(int pos) {
		this.pos_ant = pos;
	}
	
	public int getPosAnt() {
		return pos_ant;
	}

    @Override
    public String toString() {
        return "(" + w + "," + l + "," + pos_ant + "," + pos_inicial + ")";
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
