def read_input():
    n, W, L = map(int, input().split())
    plans = [tuple(map(int, input().split())) for _ in range(n)]
    return n, W, L, plans

def filter_valid_plans(W, L, plans):
    return [(i, w, l) for i, (w, l) in enumerate(plans, 1) if w > W and l > L]

def sort_plans(plans):
    return sorted(plans, key=lambda x: (x[1], x[2]))


def find_longest_expansion_sequence(plans):
    if not plans:
        return 0, []

    n = len(plans)
    dp = [1] * n
    prev = [-1] * n
    priority_queue = []

    for i in range(1, n):
        for j in range(i):
            if plans[j][1] < plans[i][1] and plans[j][2] < plans[i][2]:
                if dp[i] < dp[j] + 1:
                    dp[i] = dp[j] + 1
                    prev[i] = j
                    heapq.heappush(priority_queue, (-dp[i], i))

    max_expansions = -priority_queue[0][0]
    last_plan = priority_queue[0][1]
    sequence = []

    while last_plan != -1:
        sequence.append(plans[last_plan][0])
        last_plan = prev[last_plan]

    return max_expansions, sequence[::-1]


def main():
    n, W, L, plans = read_input()
    valid_plans = filter_valid_plans(W, L, plans)
    sorted_plans = sort_plans(valid_plans)
    max_expansions, expansion_sequence = find_longest_expansion_sequence(sorted_plans)

    print(max_expansions)
    print(*expansion_sequence)

if __name__ == "__main__":
    main()
