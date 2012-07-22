/**
 * Is array post order of BST?
 * post order: left, right, root
 */
public class IsBSTPostOrder {
    public static void main(String[] args) {
        int[] a = new int[]{0, 2, 1, 9, 12, 10, 6};
        boolean postOrder = isBstPostOrder(a, 0, a.length - 1);
        System.out.println(postOrder);
    }

    public static boolean isBstPostOrder(int[] a, int left, int right) {
        if (left > right)
            return false;
        if ( right == left)
            return true;
        if(right - left == 1){
            if(a[left] < a[right])
                return false;
            else
                return true;
        }

        int i = left;
        while (a[i] < a[right])
            i++;

        for (int j = i; j <= right; j++) {
            if (a[j] < a[right])
                return false;
        }

        return isBstPostOrder(a, left, i - 1) && isBstPostOrder(a, i, right - 1);

    }
}
