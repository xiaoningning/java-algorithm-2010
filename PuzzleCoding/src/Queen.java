import java.util.Arrays;

/**
 * Solve the 8 queens problem using recursion and backtracing.
 * Prints out all solutions.
 * <p/>
 * Limitations: works for N <= 25, but slows down considerably
 * for larger N.
 * <p/>
 * Remark: this program implicitly enumerates all N^N possible
 * placements (instead of N!), but the backtracing prunes off
 * most of them, so it's not necessarily worth the extra
 * complication of enumerating only permutations.
 * <p/>
 * <p/>
 * % java Queens 3
 * <p/>
 * % java Queens 4
 * * Q * *
 * * * * Q
 * Q * * *
 * * * Q *
 * <p/>
 * * * Q *
 * Q * * *
 * * * * Q
 * * Q * *
 * <p/>
 * % java Queens 8
 * Q * * * * * * *
 * * * * * Q * * *
 * * * * * * * * Q
 * * * * * * Q * *
 * * * Q * * * * *
 * * * * * * * Q *
 * * Q * * * * * *
 * * * * Q * * * *
 */
public class Queen {
    /**
     * ********************************************************************
     * Return true if queen placement q[n] does not conflict with
     * other queens q[0] through q[n-1]
     * *********************************************************************
     */

    public static boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n]) return false;   // same column
            if ((q[i] - q[n]) == (n - i)) return false;   // same major diagonal
            if ((q[n] - q[i]) == (n - i)) return false;   // same minor diagonal
        }
        return true;
    }

    // since it is unique int array, there is no need to compare column/row
    public static boolean isConsistent(int[] q) {
        for (int i = 0; i < q.length; i++) {
            for (int j = i + 1; j < q.length; j++) {
                if (q[i] == q[j]) return false;   // same column
                if ((q[i] - q[j]) == (j - i)) return false;   // same major diagonal
                if ((q[j] - q[i]) == (j - i)) return false;   // same minor diagonal
            }
        }
        return true;
    }

    /**
     * ********************************************************************
     * Print out N-by-N placement of queens from permutation q in ASCII.
     * *********************************************************************
     */
    public static void printQueens(int[] q) {
        int N = q.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (q[i] == j) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void permQueen(String prefix, String s) {
        int N = s.length();
        if (N == 0) {
            int[] tmp = new int[prefix.length()];
            for (int i = 0; i < prefix.length(); i++)
                tmp[i] = Integer.valueOf(prefix.substring(i, i + 1));
            if (isConsistent(tmp)) {
                System.out.println(prefix);
                printQueens(tmp);
            }
            return;
        }
        for (int i = 0; i < N; i++)
            permQueen(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1));

    }

    public static void enumerate(int[] q, int n) {
        int N = q.length;
        if (n == N) {
            System.out.println(Arrays.toString(q));
            printQueens(q);
        } else {
            for (int i = 0; i < N; i++) {
                q[n] = i;
                if (isConsistent(q, n)) enumerate(q, n + 1);
            }
        }
    }

    public static void main(String[] args) {
        int N = 4;
        enumerate(new int[N], 0);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N; i++)
            sb.append(String.valueOf(i));

        permQueen("", sb.toString());
    }
}
