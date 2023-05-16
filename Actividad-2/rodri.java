import java.util.*;

public class rodri {

	public static void main(String Args[]) {

		Scanner sc = new Scanner(System.in);
		long cantCandidatos = sc.nextInt();
		List<Par> listaCandidatos = new ArrayList<Par>();
		Par parInicial = new Par(sc.nextLong(),sc.nextLong(),-1,0);
		listaCandidatos.add(parInicial);
		for(int i = 1; i < cantCandidatos+1;i++) {
			Par par = new Par(sc.nextLong(),sc.nextLong(),-1,i);
			//Filtro los pares que son menors al inicial
			if(par.getAncho()>parInicial.getAncho() &&  par.getLargo()>parInicial.getLargo()) {
				listaCandidatos.add(par);
			}
		}
		sc.close();
		List<Integer> listaResultados = new ArrayList<Integer>();
		listaResultados.add(1);
		
		
        System.out.println(listaCandidatos.toString());
		
		if(listaCandidatos.size() == 1) {
			System.out.println(0);
		}else {
			listaCandidatos.sort(new ComparadorPares());

            System.out.println(listaCandidatos.toString());
			getCamino(listaResultados,listaCandidatos,1);
			List<Par> camino = armarCamino(listaResultados,listaCandidatos);
			System.out.println(camino.size());
			for(Par par : camino) {
				System.out.print(par.getPosOriginal()+" ");
			}
		}
		
	}
	
	static void getCamino(List<Integer> listaResultados,List<Par> listaCandidatos,int indice) {
		if(indice == listaCandidatos.size()) {
			
		}else {
			int posMaximo = obtenerMayorEnIntervalo(listaCandidatos,listaResultados,indice-1);
			listaCandidatos.get(indice).setPosAnterior(posMaximo);
			int valorPosIndice = 1 + listaResultados.get(posMaximo);
			listaResultados.add(valorPosIndice);
			getCamino(listaResultados,listaCandidatos,indice+1);
		}
	}
	
	static List<Par> armarCamino(List<Integer> listaResultados,List<Par> listaCandidatos) {
		List<Par> toReturn = new ArrayList<Par>();
		int maximo = 1;
		int indexMaximo = 1;
		for(int i = listaResultados.size()-1; i>=0; i--) {
			if(listaResultados.get(i)>maximo) {
				maximo = listaResultados.get(i);
				indexMaximo = i;
			}
		}
		Par parAnterior = listaCandidatos.get(indexMaximo);
		while(parAnterior.getPosAnterior() != -1) {
			toReturn.add(0, parAnterior);
			parAnterior = listaCandidatos.get(parAnterior.getPosAnterior());
		}
		return toReturn;
	}
	
	static int obtenerMayorEnIntervalo(List<Par> listaCandidatos,List<Integer> resultados, int i) {
		int maxRes = 1;
		int maxIndex = -1;
			for(int j = 0; j<=i; j++) {
				if(resultados.get(j)>=maxRes && listaCandidatos.get(j).getLargo() < listaCandidatos.get(i+1).getLargo() &&
						listaCandidatos.get(j).getAncho() < listaCandidatos.get(i+1).getAncho()) {
					maxRes = resultados.get(j);
					maxIndex = j;
				}
			}
		return maxIndex;
	}
}


class ComparadorPares implements Comparator<Par> {
	@Override
	public int compare(Par par1, Par par2) {
		return par1.compareTo(par2);
	}
}

class Par implements Comparable<Par>{
	long ancho;
	long largo;
	int posAnterior;
	int posOriginal;

	public Par(long x, long y,int posAnterior,int posOriginal) {
		this.ancho = x;
		this.largo = y;
		this.posAnterior = posAnterior;
		this.posOriginal = posOriginal;
	}
	
	public int getPosOriginal() {
		return this.posOriginal;
	}

	public long getAncho() {
		return ancho;
	}
	
	public long getLargo() {
		return largo;
	}
	
	public void setPosAnterior(int pos) {
		this.posAnterior = pos;
	}
	
	public int getPosAnterior() {
		return posAnterior;
	}

	@Override
	public int compareTo(Par par) {
		if(this.getAncho()>par.getAncho()){
			return 1;
		}else {
			return -1;
		}
	}	

    @Override
    public String toString() {
        return "(" + ancho + "," + largo + "," + posAnterior + "," + posOriginal + ")";
    }
}