def max_expansions(n, W, L, plans):
    # Filtrar y ordenar los planes de expansión
    valid_plans = [(w, l, i) for i, (w, l) in enumerate(plans) if w > W and l > L]
    valid_plans.sort()
    print(*valid_plans)
    # Inicializar la tabla de programación dinámica
    dp = [1] * len(valid_plans)
    prev = [-1] * len(valid_plans)

    # Calcular la secuencia más larga de expansiones posibles
    max_length = 0
    max_index = -1
    for i in range(len(valid_plans)):
        for j in range(i):
            #print(f"{i},{j}")
            # Chequeo que el siguiente plan se pueda aplicar
            # es decir, que el ancho y largo sean mas grandes que el plan anterior
            if valid_plans[j][0] < valid_plans[i][0] and valid_plans[j][1] < valid_plans[i][1]:
                if dp[j] + 1 > dp[i]:
                    dp[i] = dp[j] + 1
                    prev[i] = j
        if dp[i] > max_length:
            max_length = dp[i]
            max_index = i

    # Reconstruir la secuencia de expansiones
    expansion_indices = []
    while max_index != -1:
        expansion_indices.append(valid_plans[max_index][2] + 1)
        max_index = prev[max_index]
    expansion_indices.reverse()

    return max_length, expansion_indices

def read_input():
    n, W, L = map(int, input().split())
    plans = [tuple(map(int, input().split())) for _ in range(n)]
    return n, W, L, plans

def main():
    n, W, L, plans = read_input()
    max_length, expansion_indices = max_expansions(n, W, L, plans)
    print(max_length)
    print(" ".join(map(str, expansion_indices)))

if __name__ == "__main__":
    main()