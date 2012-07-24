import java.util.ArrayList;

/**
 * Given two integer unsorted arrays, your task is
 * to compare the BST formed by both the arrays.
 * <p/>
 * e.g [3 2 1 0  5 4 6] & [3 5 2 6 4 1 0]
 * when the BST is formed by taking the elements from the left,
 * both BST turn out to be same. (in-order)
 * <p/>
 * 3
 * /  \
 * 2     5
 * /  \    /   \
 * 1   0   4     6
 * <p/>
 * Expected time complexity O(n).
 */

public class CompareArrayBST {
    public static void main(String[] args) {

        int[] a1 = new int[]{3, 2, 1, 0, 5, 4, 6};
        int[] a2 = new int[]{3, 5, 2, 6, 4, 1, 0};

        boolean same = identicalBST(a1, a2);

        System.out.println(same);

        int[] b1 = new int[]{3, 2, 1, 0, 5, 4, 6};
        int[] b2 = new int[]{3, 6, 2, 5, 4, 1, 0};

        boolean same1 = identicalBST(b1, b2);

        System.out.println(same1);
    }

    public static boolean identicalBST(int[] a, int[] b) {
        if (a.length != b.length)
            return false;
        if (a.length == 0)
            return true;
        if (a.length == 1 && a[0] == b[0])
            return true;
        if (a[0] != b[0])
            return false;

        ArrayList<Integer> aSmaller = new ArrayList<Integer>();
        ArrayList<Integer> aLarger = new ArrayList<Integer>();
        ArrayList<Integer> bSmaller = new ArrayList<Integer>();
        ArrayList<Integer> bLarger = new ArrayList<Integer>();

        //a[0] and b[0] are the roots of trees.
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[0])
                aSmaller.add(a[i]);
            else
                aLarger.add(a[i]);
        }

        for (int i = 1; i < b.length; i++) {
            if (b[i] < b[0])
                bSmaller.add(b[i]);
            else
                bLarger.add(b[i]);
        }

        return (identicalBST(convertIntArray(aSmaller), convertIntArray(bSmaller))
                && identicalBST(convertIntArray(aLarger), convertIntArray(bLarger)));
    }

    public static int[] convertIntArray(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
