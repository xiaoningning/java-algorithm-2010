import java.util.Arrays;

/**
 * Given an integer n,
 * generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p/>
 * For example,
 * Given n = 3,
 * <p/>
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        int N = 4;
        int[][] spiralMatrix = spiralMatrix(N);
        for (int i = 0; i < spiralMatrix.length; ++i)
            System.out.println(Arrays.toString(spiralMatrix[i]));

    }

    public static int[][] spiralMatrix(int n) {
        int[][] matrix = new int[n][n];
        int m = n - 1;
        int k = 0;
        for (int layer = 0; layer <= m / 2; ++layer) {
            //top
            for (int j = layer; j <= m - layer; ++j)
                matrix[layer][j] = ++k;
            //right
            for (int i = layer + 1; i <= m - layer; ++i)
                matrix[i][m - layer] = ++k;
            //bottom
            for (int j = m - layer - 1; j >= layer; --j)
                matrix[m - layer][j] = ++k;
            //left
            for (int i = m - layer - 1; i >= layer + 1; --i)
                matrix[i][layer] = ++k;
        }
        return matrix;

    }
}
