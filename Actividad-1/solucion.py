from math import ceil

def resolverCaso(n, W, paquetes):
    # Inicializar el límite inferior y superior
    limite_inf, limite_sup = ceil(W / 2), W
    C = 0
    seleccionados = []
    indice = 1
    for peso in paquetes:
        if peso >= limite_inf and peso <= limite_sup:
            return 1, [indice]
        if C + peso <= limite_sup:
            C += peso
            seleccionados.append(indice)
        if C == limite_sup: 
            break
        indice += 1

    # Verificar si la suma de los paquetes seleccionados está en el rango y retorna el resultado
    if C >= limite_inf:
        return len(seleccionados), seleccionados
    else:
        return -1, []

cant_casos = int(input())
for _ in range(cant_casos):
    # Leer los valores de n, W y los pesos de los paquetes para cada caso de prueba
    n, W = map(int, input().split())
    paquetes = list(map(int, input().split()))

    m, solucion = resolverCaso(n, W, paquetes)

    # Imprimir la solución
    if m == -1:
        print(-1)
    else:
        print(m)
        print(*solucion)
