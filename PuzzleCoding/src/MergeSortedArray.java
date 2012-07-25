import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/**
 * merge K sorted arrays into one. total: N
 * min heap: O(NlgK)
 */
public class MergeSortedArray {

    private static class ArrayNode {
        public Integer value;
        public int arrayIndex;

        public ArrayNode(Integer v, int index) {
            value = v;
            arrayIndex = index;
        }

        public String toString() {
            return String.valueOf(value);
        }
    } // end of ArrayNode

    private static class MinHeapArrayNode {
        private ArrayList<ArrayNode> heap;

        public MinHeapArrayNode() {
            heap = new ArrayList<ArrayNode>();
            // 1 is the min one.
            // it is easy to calculate left, right and parent
            heap.add(null);

        }

        public void insert(ArrayNode node) {
            if(node == null)
                throw new RuntimeException("can't insert null node");

            heap.add(node);
            shiftUp(heap.size() - 1);
        }

        public ArrayNode removeMin() {
            if (isEmpty())
                throw new RuntimeException("heap is empty");

            // System.out.println(heap.toString());
            ArrayNode min = heap.get(1);
            heap.remove(min);

            if (heap.size() != 1) {
                shiftDown(1);
            }
            return min;
        }

        private void shiftUp(int k) {
            //k/2 parent
            while (k > 1 && greater(k / 2, k)) {
                swap(k / 2, k);
                k = k / 2;
            }
        }

        private void shiftDown(int k) {
            while (2 * k <= heap.size() - 1) {
                int i = 2 * k; //left
                int j = i + 1; //right
                //find the smallest of left and right
                if (i < heap.size() - 1 && greater(i, j)) {
                    i++;  // i = j;
                }
                if (greater(i, k)) break;
                swap(i, k);
                k = i;
            }
        }

        private boolean greater(int i, int j) {

            if (i > 0 && j < heap.size()) {
                return heap.get(i).value > heap.get(j).value;
            }
            return false;
        }

        private void swap(int i, int j) {
            ArrayNode tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
        }

        private boolean isEmpty() {
            return heap.size() == 1;
        }


    } // end of MinHeapArrayNode

    public static void main(String[] args) {
        Integer[][] arrays = new Integer[][]{
                {1, 5, 9},
                {4, 8, 10,13},
                {6, 11},
                {2,7}
        };

        ArrayList<Integer> result = mergeSortedArray(arrays);
        System.out.println(result.toString());
    }

    public static ArrayList<Integer> mergeSortedArray(Integer[][] arrays) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Queue<Integer>> lists = new ArrayList<Queue<Integer>>();

        for (int i = 0; i < arrays.length; i++) {
            ArrayList<Integer> tmpArray = new ArrayList<Integer>(Arrays.asList(arrays[i]));
            Queue<Integer> tmpQueue = new LinkedList<Integer>(tmpArray);
            lists.add(tmpQueue);
        }

        MinHeapArrayNode heap = new MinHeapArrayNode();

        // initialize minheap
        for (int i = 0; i < lists.size(); i++) {
            heap.insert(new ArrayNode(lists.get(i).poll(), i));
        }

        boolean done = false;
        while (!done) {

            ArrayNode minNode = heap.removeMin();
            result.add(minNode.value);
            int nextIndex = minNode.arrayIndex;

            Queue<Integer> nextQueue = lists.get(nextIndex);
            while (nextQueue.size() == 0) {
                nextIndex = (nextIndex < lists.size() - 1) ? nextIndex + 1 : 0;
                nextQueue = lists.get(nextIndex);
                if (nextIndex == minNode.arrayIndex) {
                    done = true;
                    break;
                }
            }

            if (!done)
                heap.insert(new ArrayNode(nextQueue.poll(), nextIndex));

        }
        while (!heap.isEmpty())
            result.add(heap.removeMin().value);

        return result;
    }
}
