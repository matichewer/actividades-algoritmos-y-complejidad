import heapq

def main():
    n, W, L = map(int, input().split())
    expansion_plans = [tuple(map(int, input().split())) + (i + 1,) for i in range(n)]

    # Filtrar planes de expansión válidos
    valid_plans = [(w, l, i) for w, l, i in expansion_plans if w > W and l > L]

    # Ordenar planes de expansión por ancho y luego por largo
    valid_plans.sort(key=lambda x: (x[0], x[1]))

    # Aplicar Patience Sort con cola de prioridad
    piles = []
    for plan in valid_plans:
        idx = find_pile(piles, plan)
        if idx == -1:
            heapq.heappush(piles, [plan[1], [plan[2]]])
        else:
            heapq.heappush(piles[idx][1], plan[2])
            piles[idx][0] = plan[1]

    # Encontrar la pila con la mayor cantidad de planes de expansión
    if piles:
        max_pile = max(piles, key=lambda x: len(x[1]))
        print(len(max_pile[1]))
        print(*max_pile[1])
    else:
        print(0)

def find_pile(piles, plan):
    idx = -1
    min_diff = float('inf')
    for i, pile in enumerate(piles):
        if pile[0] < plan[1] and plan[1] - pile[0] < min_diff:
            idx = i
            min_diff = plan[1] - pile[0]
    return idx

if __name__ == "__main__":
    main()
