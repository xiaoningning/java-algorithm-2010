import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of numbers and a sliding window size,
 * how to get the maximal numbers in all sliding windows?
 * <p/>
 * For example, if the input array is {2, 3, 4, 2, 6, 2, 5, 1}
 * and the size of sliding windows is 3,
 * the output of maximums are {4, 4, 6, 6, 6, 5}
 */
public class MaxInWindow {
    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 4, 2, 6, 2, 5, 1};
        int window = 3;
        ArrayList<Integer> result = maxInWindow(a, window);
        System.out.println(result.toString());
    }

    public static ArrayList<Integer> maxInWindow(int[] a, int windowSize) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Queue<Integer> maxIndexQueue = new LinkedList<Integer>();

        if (a.length >= windowSize && windowSize > 1) {
            int i = 0;
            for (; i < windowSize; i++) {
                while (!maxIndexQueue.isEmpty() && a[i] >= a[maxIndexQueue.peek()])
                    maxIndexQueue.poll();

                maxIndexQueue.add(i);
            }

            for (; i < a.length; i++) {
                result.add(a[maxIndexQueue.peek()]);

                while (!maxIndexQueue.isEmpty() && a[i] >= a[maxIndexQueue.peek()])
                    maxIndexQueue.poll();

                while (!maxIndexQueue.isEmpty() && maxIndexQueue.peek() <= i - windowSize)
                    maxIndexQueue.poll();

                maxIndexQueue.add(i);
            }
            result.add(maxIndexQueue.peek());
        }

        return result;
    }
}
