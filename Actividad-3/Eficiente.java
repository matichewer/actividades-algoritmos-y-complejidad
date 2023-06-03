import java.util.Scanner;

public class Eficiente {

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
        int[] temp = new int[n];
        intersections = mergeSortAndCount(a, temp, 0, n - 1);
        return intersections;
    }

    public static int mergeSortAndCount(int[] a, int[] temp, int left, int right) {
        int intersections = 0;

        if (left < right) {
            int mid = (left + right) / 2;
            intersections += mergeSortAndCount(a, temp, left, mid);
            intersections += mergeSortAndCount(a, temp, mid + 1, right);
            intersections += mergeAndCount(a, temp, left, mid, right);
        }

        return intersections;
    }

    public static int mergeAndCount(int[] a, int[] temp, int left, int mid, int right) {
        int intersections = 0;

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (a[i] >= a[j]) {
                intersections += mid - i + 1;
                temp[k++] = a[j++];
            } else {
                temp[k++] = a[i++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        for (i = left; i <= right; i++) {
            a[i] = temp[i];
        }

        return intersections;
    }
}
