import java.util.Arrays;

public class FindMiddleShiftedSorted {
    public static void main(String[] args) {
        int[] a1 = new int[]{15, 17, 1, 3, 6, 7};
        int[] a2 = new int[]{15, 16, 17, 18, 1};
        int[] a3 = new int[]{1, 2, 3, 4, 5};
        findMiddleShiftedSorted(a1);
        findMiddleShiftedSorted(a2);
        findMiddleShiftedSorted(a3);

    }

    public static void findMiddleShiftedSorted(int[] a) {
        if (a == null || a.length == 0) {
            System.out.println("not a valid array");
            return;
        }
        int len = a.length;
        int index = findShiftedIndex(a, 0, len - 1);
        System.out.print(Arrays.toString(a) + " shifted index: " + index + " result: ");
        System.out.print(a[(len / 2 + index) % (len)]);
        if (len % 2 == 0) {
            System.out.print(" and " + a[-1 + (len / 2 + index) % (len)]);
        }

        System.out.println();
    }

    public static int findShiftedIndex(int[] a, int left, int right) {

        if (right - left == 1) {
            if (a[right] < a[left])
                return right;
            else
                return 0; // it is not shifted
        }
        if (left > right)
            throw new RuntimeException("a bad call");
        else if (left < right) {
            int mid = (left + right) / 2;
            if (a[left] < a[mid]) {
                left = mid;
                return findShiftedIndex(a, left, right);
            } else {
                right = mid;
                return findShiftedIndex(a, left, right);
            }
        } else {
            return right;
        }

    }
}
