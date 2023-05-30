import java.util.Scanner;

public class Main {
    public static int countIntersections(int n, int[] cables) {
        int intersections = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cables[i] > cables[j]) {
                    intersections++;
                }
            }
        }
        return intersections;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] cables = new int[n];

            for (int j = 0; j < n; j++) {
                cables[j] = scanner.nextInt();
            }

            int intersections = countIntersections(n, cables);
            System.out.println(intersections);
        }

    }
}
