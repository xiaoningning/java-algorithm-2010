/**
 * Given a square matrix, write a program to print the items in zig zag diagonal order.
 * int a[MAX][MAX] =
 * {{ 1, 2, 3, 4, 5},
 * { 6, 7, 8, 9,10},
 * {11,12,13,14,15},
 * {16,17,18,19,20},
 * {21,22,23,24,25}};
 * <p/>
 * output:
 * 1 -> 2 -> 6 -> 3 -> 7 -> 11 -> 4 -> 8 -> 12 -> 16 -> 5 -> 9 -> 13 -> 17 -> 21 -> 10 -> 14 -> 18 -> 22 -> 15 -> 19 -> 23 -> 20 -> 24 -> 25
 */
public class ZigZagPrintMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        int i = 0, j = 0;
        int N = matrix.length;
        while (i < N) {
            if (i == N - 1 && j == N - 1)
                System.out.print(matrix[i][j]);
            else
                System.out.print(matrix[i][j] + "->");

            if (i == N - 1) {
                i = j + 1;
                j = N - 1;
            } else if (j == 0) {
                j = i + 1;
                i = 0;
            } else {
                i++;
                j--;
            }
        }
        System.out.println();
    }
}
