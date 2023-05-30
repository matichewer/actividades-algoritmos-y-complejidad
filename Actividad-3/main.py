def calcular_intersecciones(a):
    intersecciones = 0
    n = len(a)
    for i in range(n):
        for j in range(i + 1, n):
            if a[i] > a[j]:
                intersecciones += 1
    return intersecciones

# Ejemplo de uso
a = [4, 1, 4, 6, 7, 7, 5]
print(calcular_intersecciones(a)) 