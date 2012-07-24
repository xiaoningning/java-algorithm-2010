import java.util.ArrayList;

/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 8 -> 0 -> 7
 */
public class AddTwoNumber {
    public static void main(String[] args) {
        int[] a = {2, 9, 3};
        int[] b = {9, 9};
        ArrayList<Integer> result = addTwoNumber(a, b);
        System.out.println(result);
    }

    public static ArrayList<Integer> addTwoNumber(int[] n1, int[] n2) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        int l1 = n1.length - 1;
        int l2 = n2.length - 1;
        int carry = 0;

        while (l1 >= 0 && l2 >= 0) {
            int tmp = n1[l1--] + n2[l2--] + carry;
            result.add(0, tmp % 10);
            if (tmp / 10 >= 1)
                carry = 1;
            else
                carry = 0;

        }

        int len = (l1 > l2) ? l1 : l2;
        int[] n = (l1 > l2) ? n1 : n2;
        while (len >= 0) {
            int tmp = n[len--] + carry;
            result.add(0, tmp % 10);
            if (tmp / 10 >= 1)
                carry = 1;
            else
                carry = 0;
        }

        return result;
    }

}
