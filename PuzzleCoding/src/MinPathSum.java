import java.util.ArrayList;
import java.util.Arrays;


/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p/>
 * Note: You can only move either down or right at any point in time.
 * <p/>
 * If we could move to any direction in the path, then we kind of use
 * Dijkstra's algorithm to solve this problem. The difference with the
 * shotest path problem in graph is that in the graph, different node
 * to one node may have path with different weight. But int the grid,
 * each neighbor to this cell would sum with the same value. So if we
 * would also get minimun sum in fisrt calculate for this cell.
 * <p/>
 * Since the question ask to move either right or down only, then the
 * question merely become Robort move question. With dynamic programming
 * we could solve it in O(mn) time complexity.
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        // result 7
        DijkstraPathSum(grid);
        minPahtSum(grid);


        int[][] a2 = new int[][]{{3}};
        //3
        DijkstraPathSum(a2);
        minPahtSum(a2);


        int[][] a3 = new int[][]{
                {1, 0, 4, 9, 6, 0, 9, 1, 8, 9, 5},
                {1, 2, 8, 9, 2, 4, 8, 1, 7, 3, 2},
                {5, 0, 7, 9, 3, 5, 1, 3, 8, 2, 3},
                {3, 2, 2, 5, 3, 3, 3, 2, 0, 5, 6},
                {9, 6, 8, 3, 6, 2, 0, 1, 4, 6, 1},
                {1, 7, 4, 8, 8, 9, 7, 1, 3, 2, 5},
                {7, 7, 8, 0, 3, 0, 0, 0, 8, 1, 8},
                {8, 7, 4, 0, 9, 5, 4, 7, 9, 8, 5},
                {5, 6, 3, 5, 5, 6, 0, 7, 1, 7, 7},
                {9, 9, 2, 1, 1, 2, 1, 5, 0, 0, 4}
        };
        //40
        DijkstraPathSum(a3);
        minPahtSum(a3);


    }

    // only left or down, Dynamic programming to solve this.
    public static void minPahtSum(int[][] grid) {
        if (grid.length == 0) {
            System.out.println("sum path for zero grid " + 0);
            return;
        }

        int row = grid.length;
        int col = grid[0].length;

        // calculate left edge
        for (int i = row - 1; i > 0; --i)
            grid[i - 1][col - 1] += grid[i][col - 1];

        // calculate bottom edge
        for (int j = col - 1; j > 0; --j)
            grid[row - 1][j - 1] += grid[row - 1][j];

        for (int i = row - 2; i >= 0; --i) {
            for (int j = col - 2; j >= 0; --j) {
                //sum with the less one
                grid[i][j] +=
                        (grid[i + 1][j] < grid[i][j + 1]) ?
                                grid[i + 1][j] : grid[i][j + 1];
            }
        }

        System.out.println(grid[0][0]);

    }

    // Dijkstra's algorithm
    public static void DijkstraPathSum(int[][] grid) {
        if (grid.length == 0) {
            System.out.println("sum path for zero grid " + 0);
            return;
        }

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visit = new boolean[row][col];
        int[][] cost = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        String[][] path = new String[row][col];

        cost[0][0] = grid[0][0];
        path[0][0] = "0-0";
        visit[0][0] = true;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col && visit[i][j]; j++) {

                //left
                int r = i + 1, c = j;

                if (r < row) {
                    if (cost[r][c] > cost[i][j] + grid[r][c]) {
                        cost[r][c] = cost[i][j] + grid[r][c];
                        visit[r][c] = true;
                        path[r][c] = String.valueOf(i) + "-" + String.valueOf(j);
                    }

                }
                // down
                r = i;
                c = j + 1;
                if (c < col) {
                    if (cost[r][c] > cost[i][j] + grid[r][c]) {
                        cost[r][c] = cost[i][j] + grid[r][c];
                        visit[r][c] = true;
                        path[r][c] = String.valueOf(i) + "-" + String.valueOf(j);
                    }

                }
            }
        }

        System.out.println(cost[row - 1][col - 1]);
        // System.out.println(Arrays.deepToString(cost));
    }
}
