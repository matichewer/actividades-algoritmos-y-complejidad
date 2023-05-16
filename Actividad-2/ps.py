def max_expansions(n, W, L, plans):
    valid_plans = [(w, l, i) for i, (w, l) in enumerate(plans, 1) if w > W and l > L]
    valid_plans.sort()

    dp = [0] * (len(valid_plans) + 1)
    prev = [None] * len(valid_plans)

    for w, l, i in valid_plans:
        pos = 0
        while pos < len(dp) - 1 and dp[pos] < l:
            pos += 1
        if dp[pos - 1] < l:
            dp[pos] = l
            prev[pos - 1] = i

    max_expansions = dp.index(0) - 1
    indices = []

    if max_expansions >= 0:
        for i in range(max_expansions - 1, -1, -1):
            indices.append(prev[i])

    return max_expansions, indices[::-1]

# Leer input
n, W, L = map(int, input().split())
plans = [tuple(map(int, input().split())) for _ in range(n)]

# Calcular y mostrar resultado
expansions, indices = max_expansions(n, W, L, plans)
print(expansions)
if indices:
    print(*indices)