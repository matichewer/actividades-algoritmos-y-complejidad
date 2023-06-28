import java.util.LinkedList;
import java.util.Scanner;

public class Main {

  private long[][] flow;
  private long[][] capacity;
  private int[] parent;
  private boolean[] visited;
  private int n;

  public Main(int numOfVerticles) {
    this.n = numOfVerticles;
    this.flow = new long[n][n];
    this.capacity = new long[n][n];
    this.parent = new int[n];
    this.visited = new boolean[n];
  }

  public void addEdge(int from, int to, long capacity) {
    this.capacity[from][to] += capacity;
  }

  public static int indiceDelNodo(int i, int j, int n) {
    return (i * n + j) * 2 + 2; // hardcodeado
  }

  public long getMaxFlow(int s, int t) {
    while (true) {
      final LinkedList<Integer> cola = new LinkedList<Integer>();
      cola.add(s);

      for (int i = 0; i < this.n; ++i) visited[i] = false;
      visited[s] = true;

      boolean check = false;
      int current;
      while (!cola.isEmpty()) {
        current = cola.peek();
        if (current == t) {
          check = true;
          break;
        }
        cola.remove();
        for (int i = 0; i < n; ++i) {
          if (!visited[i] && capacity[current][i] > flow[current][i]) {
            visited[i] = true;
            cola.add(i);
            parent[i] = current;
          }
        }
      }
      if (check == false) 
        break;

      long temp = capacity[parent[t]][t] - flow[parent[t]][t];
      for (int i = t; i != s; i = parent[i]) temp =
        Math.min(temp, (capacity[parent[i]][i] - flow[parent[i]][i]));

      for (int i = t; i != s; i = parent[i]) {
        flow[parent[i]][i] += temp;
        flow[i][parent[i]] -= temp;
      }
    }

    long result = 0;
    for (int i = 0; i < n; ++i){
      result += flow[s][i];
    }
    return result;
  }

  public static void main(String args[]) {
    int n, m, c;
    long maxFlow;
    final int s = 0;
    final int t = 1;
    final long inf = Long.MAX_VALUE;

    Scanner scanner = new Scanner(System.in);
    n = scanner.nextInt();
    m = scanner.nextInt();
    c = scanner.nextInt();
    scanner.nextLine();

    char[][] grid = new char[m][n];

    Main grafo = new Main(2 * (n * m) + 2);

    String line;
    for (int i = 0; i < m; i++) {
      line = scanner.nextLine();
      for (int j = 0; j < n; j++) {
        char nextChar = line.charAt(j);
        grid[i][j] = nextChar;
      }
    }


    int[] cost = new int[c];
    for (int k = 0; k < c; k++) {
      cost[k] = scanner.nextInt();
    }
    scanner.close(); // fin lectura datos


    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char cursor = grid[i][j];
        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
          grafo.addEdge(indiceDelNodo(i, j, n) + 1, t, inf);
        }
        switch (cursor) {
          case 'B':
            grafo.addEdge(s, indiceDelNodo(i, j, n), inf);
            grafo.addEdge(indiceDelNodo(i, j, n), indiceDelNodo(i, j, n) + 1, inf);
            break;
          case '.':
            grafo.addEdge(indiceDelNodo(i, j, n), indiceDelNodo(i, j, n) + 1, inf);
            break;
          default:
            int index = (int) cursor;
            grafo.addEdge(
              indiceDelNodo(i, j, n),
              indiceDelNodo(i, j, n) + 1,
              cost[index - 97]
            );
            break;
        }

        if ((i - 1 >= 0 && j >= 0 && i - 1 < m && j < n)) {
          grafo.addEdge(indiceDelNodo(i, j, n) + 1, indiceDelNodo(i - 1, j, n), inf);
        }
        if (i >= 0 && j - 1 >= 0 && i < m && j - 1 < n) {
          grafo.addEdge(indiceDelNodo(i, j, n) + 1, indiceDelNodo(i, j - 1, n), inf);
        }
        if (i >= 0 && j + 1 >= 0 && i < m && j + 1 < n) {
          grafo.addEdge(indiceDelNodo(i, j, n) + 1, indiceDelNodo(i, j + 1, n), inf);
        }
        if (i + 1 >= 0 && j >= 0 && i + 1 < m && j < n) {
          grafo.addEdge(indiceDelNodo(i, j, n) + 1, indiceDelNodo(i + 1, j, n), inf);
        }
      }
    }

    // solucion:
    maxFlow = grafo.getMaxFlow(s, t);
    if (maxFlow == inf) 
      System.out.println("-1"); 
    else 
      System.out.println(maxFlow);
      
  }
}
