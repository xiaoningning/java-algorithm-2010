/*
**  The problem could be solved by dynamic problem, if we see the stairs as an array,
**  we could calculate from back to front. Each steps required in a stair to reach top
**  is the sum of next 2 stairs.
**
**  Finally, we would recognize it is just Fibonacci Sequence (just step 1 or 2),
*   the result is merely the
**  nth value in the sequence.
**
*/
public class ClimbStairs {
    public static void main(String[] args) {
        int N = 5;
        System.out.println(climbStairs(N));
    }

    public static int climbStairs(int n) {
        if (0 < n && n <= 1) return n;
        else if (n <= 0) return 1;
        else return climbStairs(n - 1)
                    + climbStairs(n - 2)
                    + climbStairs(n - 3); // add the last step 1 or 2 or 3.
    }
}
