/**
 * http://algs4.cs.princeton.edu/24pq/
 * min heap: only keep the min at the top, parent(root) < left and right node
 */
public class MinHeap {
    private int maxSize;
    private int current;
    private int[] heap;

    public MinHeap(int max) {
        maxSize = max;
        heap = new int[maxSize + 1]; // min is heap[1], it easy to calculate left/right
        current = 0; // current index
    }

    public void insert(int value) {
        if (current == maxSize) {
            throw new RuntimeException("Heap is full!");
        }
        heap[++current] = value; // start from 1
        shiftUp(current);
    }

    public boolean isEmpty() {
        return current == 0;
    }

    public int min() {
        if (isEmpty())
            throw new RuntimeException("heap is empty");
        return heap[1];
    }

    public int removeMin() {
        if (isEmpty())
            throw new RuntimeException("heap is empty");

        int min = heap[1];
        swap(1, current);
        // heap[current] = Integer.MAX_VALUE;
        current--;
        if(current != 0 )
            shiftDown(1);
        return min;
    }

    private void shiftUp(int k) {
        // k/2 is parent, parent < left/right
        while (k > 1 && heap[k / 2] > heap[k]) {
            swap(k / 2, k);
            k = k / 2;
        }

    }

    private void shiftDown(int k) {
        while (2 * k <= current) {
            int i = 2 * k; // left child
            int j = i + 1; // right child
            //find the smallest of left and right
            if (i < current && heap[i] > heap[j]) {
                i++; // i = j
            }
            if (heap[k] < heap[i]) {
                break;
            }
            swap(k, i);
            k = i;
        }
    }


    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // is pq[1..N] a min heap?
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    // is subtree of pq[1..N] rooted at k a min heap?
    private boolean isMinHeap(int k) {
        if (k > current) return true;
        int left = 2 * k, right = 2 * k + 1;
        if (left <= current && heap[k] > heap[left]) return false;
        if (right <= current && heap[k] > heap[right]) return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    public void printHeap() {
        System.out.print("heap: ");
        for (int i = 1; i <= current; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = new int[]{5, 3, 4, 1, 0, 6, 7, 2};
        MinHeap minHeap = new MinHeap(a.length);
        for (int i = 0; i < a.length; i++) {
            minHeap.insert(a[i]);
        }

        System.out.println(minHeap.isMinHeap());
        minHeap.printHeap();
        System.out.println(minHeap.removeMin());
        System.out.println(minHeap.removeMin());
        System.out.println(minHeap.isMinHeap());
        minHeap.printHeap();
        System.out.println(minHeap.removeMin());
        System.out.println(minHeap.removeMin());
        System.out.println(minHeap.removeMin());
        minHeap.printHeap();

    }
}
