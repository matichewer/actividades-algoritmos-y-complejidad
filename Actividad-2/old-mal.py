n, W, L = map(int, input().split())
expansions = [(i+1,) + tuple(map(int, input().split())) for i in range(n)]
expansions.sort(key=lambda x: x[1], reverse=False)  # Ordenar por largo

applied_expansions = []
expansiones_aplicadas = []
for i in range(n):
    w, l = expansions[i][1:]
    if w > W and l > L:
        applied_expansions.append(expansions[i][0])
        W, L = w, l

for j in range(n):
    w, l = expansions[1:][j]
    if w > W and l > L:
        applied_expansions.append(expansions[j][0])
        W, L = w, l


#print(len(applied_expansions))
print(*applied_expansions)
print(*expansiones_aplicadas)
