/**
 * Given an array of non-negative integers,
 * you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] a1 = new int[]{2, 3, 1, 1, 4};
        int[] a2 = new int[]{2, 3, 1, 1, 0,4};

        jumpGame(a1);
        jumpGame(a2);


    }

    public static void jumpGame(int[] a) {
        if (a.length == 0) return;

        int[] preStep = new int[a.length];

        for (int i = 0; i < preStep.length; i++)
            preStep[i] = -1;

        boolean reachEnd = false;
        int furthest = a[0];
        int i = 0;
        while (i <= furthest) {
            furthest = Math.max(furthest, a[i] + i);
            for (int j = 0; j <= furthest; j++) {
                if (j < a.length) {
                    if (preStep[j] == -1)
                        preStep[j] = i;
                    else {
                        if (preStep[j] > i)
                            preStep[j] = i;
                    }
                }
            }
            if (furthest >= a.length - 1) {
                reachEnd = true;
                break;
            }
            i++;
        }

        if (!reachEnd) {
            System.out.println("unreachable!");
        } else {
            int end = a.length - 1;
            while (end != 0) {
                System.out.print(a[end] +"->");
                end = preStep[end];
            }
            System.out.print(a[end]);
            System.out.println();
        }

    }
}
