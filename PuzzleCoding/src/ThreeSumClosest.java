import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p/>
 * The solution is to use two nested loops to enumerate the combination of num[i], num[j],
 * and then use binary search to locate the numbers which may have the closest distance with the target.
 * The complexity of this algorithm is O(n^2*logn).
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] a = new int[]{-1, 2, 1, -4};
        int target = 1;

        int[] result = threeSumClosest(a, target);
        System.out.println(Arrays.toString(result));

        int[] result1 = threeSumClosest1(a, target);
        System.out.println(Arrays.toString(result1));
    }

    public static int[] threeSumClosest(int[] a, int target) {
        int[] result = new int[3];

        if (a.length < 3)
            return result;

        Arrays.sort(a);

        int min_distance = Integer.MAX_VALUE;

        for (int i = 0; i < a.length - 2; i++) {  // extra two element needed, so i<a.length-2
            for (int j = i + 1; j < a.length - 1; j++) {  // extra one element needed, so j<a.length-1
                int val = target - a[i] - a[j];
                int begin = j + 1, end = a.length - 1, mid, sum;
                int[] temp = new int[3];
                while (begin <= end) {
                    mid = (begin + end) / 2;

                    if (a[mid] == val) {
                        result[0] = a[i];
                        result[1] = a[j];
                        result[2] = a[mid];
                        return result;
                    } else if (a[mid] < val) {
                        begin = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
                if (begin > a.length - 1) {
                    sum = a[i] + a[j] + a[end];
                    temp[0] = a[i];
                    temp[1] = a[j];
                    temp[2] = a[end];
                } else if (end < j + 1) {
                    sum = a[i] + a[j] + a[begin];
                    temp[0] = a[i];
                    temp[1] = a[j];
                    temp[2] = a[begin];
                } else {
                    int d1 = a[i] + a[j] + a[begin];
                    int d2 = a[i] + a[j] + a[end];

                    int d1_distance = Math.abs(d1 - target);
                    int d2_distance = Math.abs(d2 - target);
                    if (d1_distance < d2_distance) {
                        sum = d1;
                        temp[0] = a[i];
                        temp[1] = a[j];
                        temp[2] = a[begin];

                    } else {
                        sum = d2;
                        temp[0] = a[i];
                        temp[1] = a[j];
                        temp[2] = a[end];

                    }

                }
                if (Math.abs(sum - target) < min_distance) {
                    min_distance = Math.abs(sum - target);

                    result = temp.clone();
                }

            }
        }
        return result;
    }

    // non binary search O(n^3)
    public static int[] threeSumClosest1(int[] a, int target) {
        int[] result = new int[3];

        if (a.length < 3)
            return result;

        Arrays.sort(a);

        int min_distance = Integer.MAX_VALUE;
        int x, y, z, sum, temp_distance;
        for (int i = 0; i < a.length - 2; i++) {
            x = a[i];
            for (int j = i + 1, k = a.length - 1; j < k; ) {
                y = a[j];
                z = a[k];
                sum = x + y + z;
                temp_distance = Math.abs(sum - target);
                if (sum < target) {

                    if (temp_distance < min_distance) {
                        min_distance = temp_distance;
                        result[0] = x;
                        result[1] = y;
                        result[2] = z;
                    }
                    j++;
                } else if (sum > target) {
                    if (temp_distance < min_distance) {
                        min_distance = temp_distance;
                        result[0] = x;
                        result[1] = y;
                        result[2] = z;
                    }
                    k--;
                } else {
                    result[0] = x;
                    result[1] = y;
                    result[2] = z;
                    return result;

                }

            }
        }

        return result;

    }
}
