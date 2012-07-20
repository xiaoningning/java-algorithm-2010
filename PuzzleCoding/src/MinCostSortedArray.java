import java.util.Arrays;

/**
 * You are given an array of positive integers.
 * Convert it to a sorted array with minimum cost. Only valid operation are
 * <p/>
 * 1) Decrement -> cost = 1
 * 2) Delete an element completely from the array -> cost = value of element
 * <p/>
 * <p/>
 * For example:
 * 4,3,5,6, -> cost 1
 * 10,3,11,12 -> cost 3
 * <p/>
 * idea:
 * In each case, you have 2 choices.  The first is to decrement
 * elements to the left by the amount needed to restore non-decreasing
 * order.  The second is to delete the new element.  The cost of each is
 * easy to calculate.  Pick the choice with least cost and continue.
 * This algorithm is O(n^2).
 * <p/>
 * Remember C(n, m) is the cost of making a[1 .. n] into a non-decreasing
 * sequence with the last element being no more than m.  And we always
 * draw m from the set V of values in a.
 * So here is the new DP:
 * C(1, m) = max(a[1] - m, 0)  // first row only decrement is possible
 * C(n, m) = min (
 * a[n] + C(n - 1, m),   // delete
 * (a[n] <= m) ?  C(n - 1, a[n]) : C(n - 1, m) + a[n] - m // decrement
 * )
 * <p/>
 * Here is an example.  Suppose we have a = [5, 1, 1, 1, 3, 1].  The
 * least cost here is obtained by decrementing the 5 to 1 (cost 4) and
 * deleting the final 1 (cost 1) for a total cost of 5.
 * So let's try the algorithm. (You must view this with a fixed font.)
 * Table of C(n, m) values:
 * m = 1   3   5
 * n = 1 : 4   2   0
 * n = 2 : 4   3*  1*
 * n = 3 : 4   4   2*
 * n = 4 : 4   4   3*
 * n = 5 : 6m  4   4
 * n = 6 : 6   5*  5*
 * Here * means C resulted from decrementing and "m" means that a
 * decrement was based on the value of m rather than a[n].
 * We take the answer from C(6,5) = 5.
 * <p/>
 * <p/>
 * Now the solution becomes easy to understand: it is a DP in two dimensions.
 * If we sort the elements of the distinct elements of the original sequence d into a sorted array s,
 * the length of d becomes the first dimension of the DP; the length of s becomes the second dimension.
 * <p/>
 * We declare dp[d.Length,s.Length]. The value of dp[i,j] is the cost of solving subproblem d[0 to i]
 * while keeping the last element of the solution under s[j].
 * Note: this cost includes the cost of eliminating d[i] if it is less than s[j].
 * <p/>
 * The first row dp[0,j] is computed as the cost of trimming d[0] to s[j],
 * or zero if d[0] < s[j]. The value of dp[i,j] next row is calculated
 * as the minimum of dp[i-1, 0 to j] + trim,
 * where trim is the cost of trimming d[i] to s[j],
 * or d[i] if it needs to be eliminated because s[j] is bigger than d[i].
 */
public class MinCostSortedArray {
    public static void main(String[] args) {
        int[] a1 = new int[]{4, 3, 5, 6};
        int[] a2 = new int[]{10, 3, 11, 12};
        int[] a3 = new int[]{1, 10, 2, 11, 12};
        int[] a4 = new int[]{5, 1, 1, 1, 3, 1};

        minCostNonDecreasingArray(a1);
        minCostNonDecreasingArray(a2);
        minCostNonDecreasingArray(a3);
        minCostNonDecreasingArray(a4);

    }

    public static void minCostNonDecreasingArray(int[] a) {

        System.out.println(Arrays.toString(a));
        if (a.length <= 1) {
            System.out.println("min cost: " + 0);
            return;
        }

        int[] sorted = a.clone();
        Arrays.sort(sorted);

        int[][] cost = new int[a.length][sorted.length];

        int[] index = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < sorted.length; k++) {
                if (sorted[k] == a[i])
                    index[i] = k;
            }
        }

        for (int j = 0; j < sorted.length; j++) {
            cost[0][j] = (sorted[j] < a[0]) ? (a[0] - sorted[j]) : 0; // to a[0]
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < sorted.length; j++) {

                int del_cost = cost[i - 1][j] + a[i];
                int decr_cost = (a[i] > sorted[j]) ? (cost[i - 1][j] + (a[i] - sorted[j])) : cost[i - 1][index[i]];

                cost[i][j] = Math.min(del_cost, decr_cost);

            }

        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < cost[0].length; j++) {
            min = Math.min(min, cost[a.length - 1][j]);
        }
        // System.out.println(Arrays.deepToString(cost));
        System.out.println("min cost: " + min);
    }
}
