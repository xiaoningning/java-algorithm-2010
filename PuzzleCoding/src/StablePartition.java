import java.util.Arrays;

/**
 * Given an array of positive and negative integers,
 * re-arrange it so that positives on one end and negatives on the other,
 * but retain the original order of appearance,
 * e.g. 2,7,-5, 9,-1, 10,-3 => -5, -1, -3, 2, 7, 9, 10.
 * Do it in-place without extra space, in O(nlogn).
 */

public class StablePartition {
    public static void main(String[] args) {
        // int[] a = new int[]{1, -5, 2, 3, -4, -2, 9, 3, -1};
        int[] a = new int[]{1, -5, 7, 3, -1, -2, 9, 3, -3};
        //int[] a = new int[]{1, -3, 2,-1,3};

        stable2WayPartition(a);

        System.out.println(Arrays.toString(a));
    }

    public static void stable2WayPartition(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        stable2WayPartition(a, 0, a.length - 1);
    }

    public static void stable2WayPartition(int[] a, int left, int right) {

        if (left >= right)
            return;

        int mid = (left + right) / 2;
        stable2WayPartition(a, left, mid);
        stable2WayPartition(a, mid + 1, right);

        int i = left, j = mid + 1;
        while (i <= mid && a[i] < 0)
            i++;
        while (j <= right && a[j] < 0)
            j++;

        reverse(a, i, mid);
        reverse(a, mid + 1, j - 1);
        reverse(a, i, j - 1);

    }

    public static void reverse(int[] a, int left, int right) {
        while (left < right) {
            swap(a, left++, right--);
        }
    }

    public static void swap(int[] a, int l, int r) {
        int tmp = a[r];
        a[r] = a[l];
        a[l] = tmp;
    }


}
