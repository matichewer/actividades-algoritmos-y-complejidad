import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            System.out.println(calcIntersecciones(n, a));
        }
        scanner.close();
    }

    public static int calcIntersecciones(int n, int[] a) {
        int intersections = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] >= a[j]) {
                    intersections++;
                }
            }
        }

        return intersections;
    }
}
