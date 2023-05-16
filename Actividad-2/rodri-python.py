

class Par:
    def __init__(self, ancho, largo, pos_anterior, pos_inicial):
        self.ancho = ancho
        self.largo = largo
        self.pos_anterior = pos_anterior
        self.pos_inicial = pos_inicial

    def get_pos_inicial(self):
        return self.pos_inicial

    def get_ancho(self):
        return self.ancho

    def get_largo(self):
        return self.largo

    def set_pos_anterior(self, pos):
        self.pos_anterior = pos

    def get_pos_anterior(self):
        return self.pos_anterior

    def __lt__(self, other):
        return self.get_ancho() < other.get_ancho()
    
    def __str__(self):
        return "(" + str(self.ancho) + ", " + str(self.largo) + ", " + str(self.pos_anterior) + ", " + str(self.pos_inicial) + ")"

    


def get_camino(lista_resultados, lista_candidatos, indice):
    if indice != len(lista_candidatos):    
        pos_maximo = get_mayor(lista_candidatos, lista_resultados, indice - 1)
        lista_candidatos[indice].pos_anterior = pos_maximo
        valor_pos_indice = 1 + lista_resultados[pos_maximo]
        lista_resultados.append(valor_pos_indice)
        get_camino(lista_resultados, lista_candidatos, indice + 1)

def armar_camino(lista_resultados, lista_candidatos):
    toReturn = []
    maximo = 1
    max_index = 1
    for i in range(len(lista_resultados) - 1, -1, -1):
        if lista_resultados[i] > maximo:
            maximo = lista_resultados[i]
            max_index = i
    par_anterior = lista_candidatos[max_index]
    while par_anterior.pos_anterior != -1:
        toReturn.insert(0, par_anterior)
        par_anterior = lista_candidatos[par_anterior.pos_anterior]
    return toReturn

def get_mayor(lista_candidatos, resultados, i) :
    max_res = 1
    max_index = -1
    for j in range(i + 1):
        if (resultados[j] >= max_res and
            lista_candidatos[j].largo < lista_candidatos[i + 1].largo and
            lista_candidatos[j].ancho < lista_candidatos[i + 1].ancho):
            max_res = resultados[j]
            max_index = j
    return max_index

'''
def read_input():
    n, W, L = map(int, input().split())
    plans = [tuple(map(int, input().split())) for _ in range(n)]
    return n, W, L, plans

def main():
    n, W, L, plans = read_input()
    lista_candidatos = []
    for i, (ancho, largo) in enumerate(plans):
        if ancho > W and largo > L:
            nueva_tupla = Par(ancho, largo , -1, i+1)
            lista_candidatos.append(nueva_tupla)
    par_inicial = Par(W, L , -1, 0)
    lista_candidatos.append(par_inicial)
    lista_resultados = [1]

    if len(lista_candidatos) == 1:
        print(0)
    else:
        lista_candidatos.sort()        
        get_camino(lista_resultados, lista_candidatos, 1)
        camino = armar_camino(lista_resultados, lista_candidatos)
        print(len(camino))
        print(" ".join(str(par.pos_inicial) for par in camino))

'''
def main():
    n, W, L = map(int, input().split())
    lista_candidatos = []
    for i in range(1, n+1):
        w, l = map(int, input().split())
        if w > W and l > L:
            nueva_tupla = Par(w, l , -1, i)
            lista_candidatos.append(nueva_tupla)
    # print(*lista_candidatos)
    par_inicial = Par(W, L , -1, 0)
    lista_candidatos.append(par_inicial)
    lista_resultados = [1]

    if len(lista_candidatos) == 1:
        print(0)
    else:
        lista_candidatos.sort()     
        #print(*lista_candidatos)   
        get_camino(lista_resultados, lista_candidatos, 1)
        camino = armar_camino(lista_resultados, lista_candidatos)
        print(len(camino))
        print(" ".join(str(par.pos_inicial) for par in camino))


    

if __name__ == "__main__":
    main()
