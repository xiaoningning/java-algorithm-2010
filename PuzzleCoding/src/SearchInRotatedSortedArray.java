/*
* Suppose a sorted array is rotated at some pivot unknown to you beforehand.
*
* (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
*
* You are given a target value to search. If found in the array return its index, otherwise return -1.
*
*/

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] a = {2, 3, 2, 2, 2, 2, 2, 2, 2, 2};

        System.out.println(searchInSortedArray(a, 0, a.length - 1, 3));
        System.out.println(search(a, 0, a.length - 1, 3));

    }

    public static int searchInSortedArray(int[] a, int low, int high, int x) {
        if (low > high || low < 0 || high >= a.length || a.length == 0)
            return -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (x < a[mid]) {
                if (a[mid] < a[high])  // the higher is ordered
                    high = mid - 1;
                else {                // the lower is ordered
                    if (x < a[low])    // the higher is not ordered, x is less than all lowers.
                        low = mid + 1;
                    else
                        high = mid - 1;
                }
            } else if (x > a[mid]) {
                if (a[low] < a[mid]) //the lower is ordered
                    low = mid + 1;
                else {
                    if (x > a[high])
                        high = mid - 1;
                    else
                        low = mid + 1;
                }
            } else  // x==a[mid]
                return mid;
        }
        return -1;

    }

    public static int search(int a[], int left, int right, int x) {
        int mid = (left + right) / 2;
        if (x == a[mid]) { // Found element
            return mid;
        }
        if (right < left || right < 0 || left >= a.length || a.length == 0) {
            return -1;
        }

        /* While there may be an inflection point due to the rotation, either the left or
           * right half must be normally ordered.  We can look at the normally ordered half
           * to make a determination as to which half we should search.
           */
        if (a[left] < a[mid]) { // Left is normally ordered.
            if (x >= a[left] && x <= a[mid]) {
                return search(a, left, mid - 1, x);
            } else {
                return search(a, mid + 1, right, x);
            }
        } else if (a[mid] < a[left]) { // Right is normally ordered.
            if (x >= a[mid] && x <= a[right]) {
                return search(a, mid + 1, right, x);
            } else {
                return search(a, left, mid - 1, x);
            }
        } else if (a[left] == a[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
            if (a[mid] != a[right]) { // If right half is different, search there
                return search(a, mid + 1, right, x);
            } else { // Else, we have to search both halves
                int result = search(a, left, mid - 1, x);
                if (result == -1) {
                    return search(a, mid + 1, right, x);
                } else {
                    return result;
                }
            }
        }
        return -1;
    }
}
