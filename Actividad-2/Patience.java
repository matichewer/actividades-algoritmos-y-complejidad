import java.util.*;

public class Patience {
/*
	// Function to merge piles in a sorted order
	public static List<Integer>
	mergePiles(List<List<Integer> > v)
	{
		// Store minimum element from
		// the top of stack
		List<Integer> ans = new ArrayList<Integer>();

		// In every iteration find the smallest element
		// of top of pile and remove it from the piles
		// and store into the final array
		while (true) {

			// Stores the smallest element
			// of the top of the piles
			int minu = Integer.MAX_VALUE;

			// Stores index of the smallest element
			// of the top of the piles
			int index = -1;

			// Calculate the smallest element
			// of the top of the every stack
			for (int i = 0; i < v.size(); i++) {

				// If minu is greater than
				// the top of the current stack
				if (!v.get(i).isEmpty()
					&& minu > v.get(i).get(v.get(i).size()
										- 1)) {
					minu
						= v.get(i).get(v.get(i).size() - 1);
					index = i;
				}
			}

			if (index == -1) {
				break;
			}

			ans.add(minu);
			v.get(index).remove(v.get(index).size() - 1);

			// If current pile is empty

			if (v.get(index).isEmpty()) {
				// Remove current pile
				// from all piles
				v.remove(index);
			}
		}

		return ans;
	}

	// Function to sort the given array
	// using the patience sorting
	public static List<Integer>	patienceSorting(List<Par> lista_expansiones){
		
		// Store all the created piles
		List<List<Par>> pilas = new ArrayList<List<Par> >();
		
		// recorro las expansiones
		for (int i=1; i < lista_expansiones.size(); i++) {
			
			// If no piles are created
			if (pilas.isEmpty()) {

				// Initialize a new pile
				List<Par> nueva_pila = new ArrayList<Par>();
				nueva_pila.add(lista_expansiones.get(i));
				pilas.add(nueva_pila);
			}
			else {
				// Check if top element of each pile
				// is less than or equal to
				// current element or not
				int flag = 1;

				// recorro las pilas
				for (int j = 0; j < pilas.size(); j++) {

					// Check if the element to be
					// inserted is less than
					// current pile's top
					
					Par p1 = lista_expansiones.get(i);
					if (p1 < pilas.get(j).get(pilas.get(j).size() - 1)) {
						pilas.get(j).add(arr.get(i));
						flag = 0;
						break;
					}
					
				}

				if (flag == 1) {
					List<Integer> temp
						= new ArrayList<Integer>();
					// Insert current element
					// into temp
					temp.add(arr.get(i));

					// Insert current pile
					// into all the piles
					pilas.add(temp);
				}
			}
		}

		// Store the sorted sequence
		// of the given array
		List<Integer> ans = mergePiles(pilas);

		for (int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i) + " ");
		}

		return ans;
	}
*/

	public static int[] lis(List<Par> lista_expansiones, int n) {

        int lis[] = new int[n];
        int pos[] = new int[n];
        int i, j, max = 0, maxIndex = -1;

        for (i = 0; i < n; i++) {
            lis[i] = 1;
            pos[i] = -1;
        }

        for (i = 1; i < n; i++) {
            for (j = 0; j < i; j++) {
				Par par_i = lista_expansiones.get(i);
				Par par_j = lista_expansiones.get(j);
                if (par_i.getLargo() > par_j.getLargo() 
						&& (par_i.getAncho() != par_j.getAncho())
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
            result[i] = maxIndex;
            maxIndex = pos[maxIndex];
        }

        return result;
    }

	
	public static void main(String[] args){

		// LEO DATOS
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Par par_inicial = new Par(sc.nextInt(), sc.nextInt(), 0);
		List<Par> lista_expansiones = new ArrayList<Par>();
		lista_expansiones.add(par_inicial);
		for(int i = 1; i < n+1; i++) {
			Par par = new Par(sc.nextInt(), sc.nextInt(), i);
			// Solo los guardo si son mayores que el inicial
			if(par.getAncho()>par_inicial.getAncho() &&  par.getLargo()>par_inicial.getLargo()) {
				lista_expansiones.add(par);
			}
		}
		sc.close();
		//System.out.println(lista_expansiones);
		Collections.sort(lista_expansiones);
		System.out.println(lista_expansiones);
		// FIN LECTURA DATOS

		int resultado [] = lis(lista_expansiones, lista_expansiones.size());
		System.out.println(resultado.length);
		for (int numero : resultado) {
            System.out.print(numero + " ");
        }



	}
}



class PairComparator implements Comparator<Par> {
	@Override
	public int compare(Par par1, Par par2) {
		return par1.compareTo(par2);
	}
}

class Par implements Comparable<Par>{
	int ancho;
	int largo;
	int indice;

	public Par(int w, int l, int indice) {
		this.ancho = w;
		this.largo = l;
		this.indice = indice;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getLargo() {
		return largo;
	}
	
	public int getIndice() {
		return indice;
	}
	
	// p1.compareTo(p2) = 1 = p1.ancho > p2.ancho
	@Override
	public int compareTo(Par par) {
		if(this.getAncho() != par.getAncho()){
			if(this.getAncho()>par.getAncho())
				return 1;
			else 
				return -1;
		} else {
			if(this.getLargo() < par.getLargo())
				return 1;
			else
				return -1;
		}
	}	

    @Override
    public String toString() {
        return "(" + ancho + "," + largo + "," + indice + ")";
    }
}