/**
 * find the second largest integer in an array
 */
public class SecondLargeInt {
    public static void main(String[] args) {
        int[] a = new int[]{-1, 3, 4, 2, 2};
        boolean found = secondLargeInt(a);
        System.out.println(found);
    }

    public static boolean secondLargeInt(int[] a) {
        if (a == null || a.length <= 1)
            return false;

        int[] result = secondLargeInt(a, 0, a.length - 1);
        if (result[1] != Integer.MIN_VALUE) {
            System.out.println(result[1]);
            return true;
        } else {
            return false;
        }

    }

    public static int[] secondLargeInt(int[] a, int s, int e) {
        if (e - s == 0) {
            return new int[]{a[e], Integer.MIN_VALUE};
        }

        int m = (s + e) / 2;

        int[] result1 = secondLargeInt(a, s, m);
        int[] result2 = secondLargeInt(a, m + 1, e);

        int x1 = result1[0];
        int x2 = result1[1];
        int y1 = result2[0];
        int y2 = result2[1];

        if (x1 > y1) {
            if (y1 > x2)
                return new int[]{x1, y1};
            else
                return new int[]{x1, x2};
        } else if (y1 > x1) {
            if (x1 > y2)
                return new int[]{y1, x1};
            else
                return new int[]{y1, y2};
        } else {
            return new int[]{x1, Integer.MIN_VALUE};
        }

    }
}
