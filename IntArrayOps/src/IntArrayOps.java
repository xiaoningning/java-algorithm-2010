import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Collections;


public class IntArrayOps {
    public static void main(String[] args) {
        int length = 10;
        int[] a = new int[length];
        for (int i = 0; i < length; ++i) {
            a[i] = 0 + (int) (Math.random() * length);
        }

        System.out.println(Arrays.toString(a));

        int[] a1 = new int[]{16, 18, 7, 8, 9, 10, 13, 0, 1, 2, 0, 9, 13, 14};
        System.out.println(Arrays.toString(a1));
        ArrayList<Integer> lis = LIS(a1);
        System.out.println("LIS: " + lis.toString());

        // a1 = new int[]{ 0, 1, 2, 0, 3,};
        System.out.println(Arrays.toString(a1));
        int max = LIS1(a1);
        System.out.println("LIS1: " + max);

        System.out.println("quick sort " + Arrays.toString(a1));
        int[] a2 = a1.clone();
        quickSort(a2, 0, a2.length - 1);
        System.out.println("quick sort result " + Arrays.toString(a2));


        System.out.println("merge sort " + Arrays.toString(a1));
        int[] a22 = a1.clone();
        mergeSort(a22, 0, a22.length - 1);
        System.out.println("merge sort result " + Arrays.toString(a22));


        // int pi = partition(a2, 0, 4);
        // System.out.println(pi);

        int rank = 9;
        int pick = pickRank(a1, 0, a1.length - 1, rank);
        System.out.println("a1 rank " + rank + " : " + pick);

        int a3 = 134;
        System.out.println(a3 + " reversed to " + isPalindrome1(a3));

        int a4 = 1551;
        System.out.println(a4 + " isPalindrome: " + isPalindrome(a4));

        int[] a5 = new int[]{3, -1, 1, 2, 4};

        System.out.println(Arrays.toString(a5) + " 1st missing positiv: " + firstMissingPositive(a5, a5.length));

        int[] a6 = new int[]{1, 3, 5, 0, 0, 0};
        int[] a7 = new int[]{2, 4, 6};
        mergeSorted(a6, a7);
        System.out.println("merge two sorted: " + Arrays.toString(a6));

        int[] a8 = new int[]{1, 1, 1, 2, 2, 3, 4, 4, 5, 6, 6};
        removeDupInplace(a8);
        System.out.println("remove dup of a sorted arr in place: " + Arrays.toString(a8));

        int[] a9 = new int[]{-1, -1, 1, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8};
        removeDupInplace2(a9);
        System.out.println("remove dup of a sorted arr in place2: " + Arrays.toString(a9));

        int[] A = new int[]{1, 2, 4, 8, 9, 10};
        int[] B = new int[]{11, 12, 13, 14};

        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));

        System.out.println("find the k smallest element of A&B: " + findK(A, 0, A.length - 1, B, 0, B.length - 1, 5));
        System.out.println("find the k smallest element of A&B1: " + findK1(A, B, 5));

        if ((A.length + B.length) % 2 == 0) {
            int m1 = findK1(A, B, (A.length + B.length) / 2);
            int m2 = findK1(A, B, (A.length + B.length) / 2 + 1);
            System.out.println("find median: " + (double) (m1 + m2) / 2);

        } else {
            System.out.println("find median: " + findK1(A, B, (A.length + B.length) / 2 + 1));
        }

        System.out.println("find median1: " + findMedianSortedArrays(A, B));

    }

    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length)
            return findMedianSortedArrays(b, a, 0, b.length - 1);
        else
            return findMedianSortedArrays(a, b, 0, a.length - 1);

    }

    public static double findMedianSortedArrays(int[] a, int[] b, int la, int ra) {
        if (la > ra)
            return findMedianSortedArrays(b, a, Math.max(0, (a.length + b.length) / 2 - a.length), Math.min(b.length, (a.length + b.length) / 2));

        int i = (ra + la) / 2;
        int j = (a.length + b.length) / 2 - i - 1; // i+j = meidan -1

        if (j >= 0 && a[i] < b[j])
            return findMedianSortedArrays(a, b, i + 1, ra);
        else if (j < b.length - 1 && a[i] > b[j + 1])
            return findMedianSortedArrays(a, b, la, i - 1);

        if ((a.length + b.length) % 2 == 1)
            return (double) a[i];
        else {
            if (i > 0) {
                if (j >= 0) return (double) (a[i] + Math.max(b[j], a[i - 1])) / 2;
                else return (double) (a[i] + a[i - 1]) / 2;
            } else
                return (double) (a[i] + b[j]) / 2;
        }

    }

    public static int findK1(int[] a, int[] b, int k) {
        if (k > (a.length + b.length) || k < 0)
            return -1;

        int i = (int) ((double) (a.length) / (a.length + b.length) * (k - 1));
        int j = k - 1 - i; // i+j = k-1

        int ai_1 = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
        int bj_1 = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
        int ai = (i == a.length) ? Integer.MAX_VALUE : a[i];
        int bj = (j == b.length) ? Integer.MAX_VALUE : b[j];

        if (bj_1 < ai && ai < bj)
            return ai;
        else if (ai_1 < bj && bj < ai)
            return bj;

        if (ai < bj_1)
            return findK1(Arrays.copyOfRange(a, i + 1, a.length), Arrays.copyOfRange(b, 0, j), k - i - 1);
        else // bj < ai_1
            return findK1(Arrays.copyOfRange(a, 0, i), Arrays.copyOfRange(b, j + 1, b.length), k - j - 1);

    }

    // find the k smallest element of two sorted arrays
    public static int findK(int[] a, int la, int ra, int[] b, int lb, int rb, int k) {
        if (la < 0 || lb < 0 || ra > a.length - 1 || rb > b.length - 1 || k > (ra - la + 1 + rb - lb + 1) || k < 0)
            return -1;

        int i = (int) ((double) (ra - la + 1) / (ra - la + 1 + rb - lb + 1) * (k - 1)) + la;

        int j = k - 1 - (i - la) + lb; // i+j = k-1

        int ai_1 = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
        int bj_1 = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
        int ai = (i == ra + 1) ? Integer.MAX_VALUE : a[i];
        int bj = (j == rb + 1) ? Integer.MAX_VALUE : b[j];

        if (bj_1 < ai && ai < bj)
            return ai;
        else if (ai_1 < bj && bj < ai)
            return bj;

        if (ai < bj_1)
            return findK(a, i + 1, ra, b, lb, j - 1, k - 1 - (i - la));
        else // bj < ai_1
            return findK(a, la, i - 1, b, j + 1, rb, k - 1 - (j - lb));

    }

    public static void removeDupInplace2(int[] a) {
        if (a.length <= 1) return;

        int i = 0, j = 0;

        while (++i < a.length) {
            if (a[j] != a[i])
                a[++j] = a[i];
        }

        while (++j < a.length)
            a[j] = 0;

    }

    public static void removeDupInplace1(int[] a) {
        if (a.length <= 1) return;
        int i, j = 0;
        for (i = 1; i < a.length; ++i) {
            if (a[j] != a[i]) {
                a[j + 1] = a[i];
                j++;
            }
        }
        j++;
        while (j < a.length) {
            a[j] = 0;
            j++;
        }
    }

    // not a good solution
    public static void removeDupInplace(int[] a) {
        if (a.length <= 1) return;

        for (int i = 1; i < a.length; ++i) {
            if (a[i] <= a[i - 1]) {
                int j = i + 1;
                while (j < a.length) {
                    if (a[j] > a[i - 1]) {
                        //int tmp = a[i];
                        a[i] = a[j];
                        //a[j] = tmp;
                        break;
                    }
                    j++;
                    if (j == a.length - 1) {
                        while (i < a.length) {
                            a[i] = 0;
                            i++;
                        }
                        return;
                    }

                }

            }
            if (a[i] == 0)
                return;
        }

    }

    public static void mergeSorted(int[] l, int[] s) {
        int space = 0, i = 0, j = -1, k = s.length - 1;

        while (i < l.length) {
            if (l[i] == 0) space++;
            else j++;   // last non 0 int
            i++;

        }
        if (space < s.length)
            return;

        i = l.length - 1;
        while (k >= 0) {
            if (s[k] >= l[j]) {
                l[i--] = s[k--];
            } else {
                l[i--] = l[j--];
            }
        }

        while (k >= 0) {
            l[j--] = s[k--];
        }
    }

    public static int firstMissingPositive(int A[], int n) {

        if (A.length == 0) return 1;

        //The key idea is to put every element in A such that A[i]=i+1
        for (int i = 0; i < n; i++) {
            while (A[i] != i + 1 && A[i] > 0 && A[i] <= n && A[i] != A[A[i] - 1]) {
                int temp = A[i];
                A[i] = A[temp - 1];
                A[temp - 1] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (A[i] != i + 1) return i + 1;
        }
        return n + 1;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static boolean isPalindrome1(int x) {
        if (x < 0) return false;
        return x == reverseInt(x);
    }

    public static int reverseInt(int a) {
        int r = 0;
        while (a != 0) {
            r = a % 10 + r * 10;
            a = a / 10;
        }
        return r;

    }

    // longest increasing sequence
    public static ArrayList<Integer> LIS(int[] a) {

        LinkedList<Integer> trackLargestIndex = new LinkedList<Integer>();
        int[] path = new int[a.length];

        trackLargestIndex.add(0);
        path[0] = 0;

        for (int i = 1; i < a.length; ++i) {

            if (a[trackLargestIndex.getLast()] <= a[i]) {
                path[i] = trackLargestIndex.getLast(); // record the index of previous smaller element
                trackLargestIndex.add(i);  // record the index of the largest element
            } else {
                int s, e;
                for (s = 0, e = trackLargestIndex.size() - 1; s < e; ) {

                    int m = (e + s) / 2;

                    if (a[trackLargestIndex.get(m)] <= a[i])
                        s = m + 1;
                    else
                        e = m;

                }
                if (a[i] < a[trackLargestIndex.get(s)]) {
                    trackLargestIndex.set(s, i);
                    if (s > 0)
                        path[i] = trackLargestIndex.get(s - 1);
                    else
                        path[i] = 0;
                }
            }
        }

        int index = trackLargestIndex.getLast();
        ArrayList<Integer> lis = new ArrayList<Integer>();
        for (int n = 0; n < trackLargestIndex.size(); ++n) {
            lis.add(0, a[index]);
            index = path[index];
        }

        return lis;
    }

    public static int LIS1(int[] a) {
        int max = 1;
        int[] trackTable = new int[a.length + 1]; // initial all 0 ; track index of the length of sequence
        trackTable[1] = 0;        // 0 is the index  of the smallest length 1.

        for (int i = 1; i < a.length; ++i) {

            if (a[i] < a[trackTable[1]])
                trackTable[1] = i;
            else if (a[i] >= a[trackTable[max]]) {
                trackTable[max + 1] = i;
                max++;

            } else { // smallest < a[i] < largest
                int s = 1;
                int e = max;
                while (s < e) {
                    int m = (e + s) / 2;
                    if (a[trackTable[m]] <= a[i])
                        s = m + 1;
                    else
                        e = m;
                }
                trackTable[s] = i;
            }

        }
        return max;
    }

    public static int partition(int[] a, int left, int right) {
        int pivot = a[(left + right) / 2]; //a random pick
        while (left <= right) {
            while (a[right] > pivot)
                right--;
            while (a[left] < pivot)
                left++;

            if (left <= right) {
                int tmp = a[right];
                a[right] = a[left];
                a[left] = tmp;

                right--;
                left++;
            }

        }
        return left;
    }

    public static void quickSort(int[] a, int s, int e) {
        int pivot = partition(a, s, e);
        if (s < pivot - 1)
            quickSort(a, s, pivot - 1);

        if (pivot < e)
            quickSort(a, pivot, e);
    }

    public static int max(int[] array, int left, int right) {
        int max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            max = Math.max(array[i], max);
        }
        return max;
    }

    public static int pickRank(int[] a, int left, int right, int rank) {
        int pivotIndex = partition(a, left, right);
        int leftSize = pivotIndex - left;
        if (leftSize == rank)
            return max(a, left, pivotIndex);
        else if (rank < leftSize) {
            return pickRank(a, left, pivotIndex - 1, rank);
        } else //(rank > leftSize)
            return pickRank(a, pivotIndex, right, rank - leftSize);
    }

    public static void mergeSort(int[] a, int s, int e) {
        if (s < e) {
            int m = (s + e) / 2;
            mergeSort(a, s, m);
            mergeSort(a, m + 1, e);
            merge(a, s, m, e);
        }
    }

    public static void merge(int[] a, int left, int middle, int right) {

        int[] helper = new int[a.length];

        for (int i = left; i <= right; ++i) {
            helper[i] = a[i];
        }

        int helperRight = right;
        int helperMiddle = middle;
        int current = right;
        while (helperMiddle >= left && helperRight > middle) {
            if (helper[helperRight] > helper[helperMiddle]) {
                a[current] = helper[helperRight];
                helperRight--;
                current--;
            } else {
                a[current] = helper[helperMiddle];
                helperMiddle--;
                current--;
            }
        }

        while (helperRight > middle) {
            a[current] = helper[helperRight];
            current--;
            helperRight--;
        }

    }

}
