import java.util.LinkedList;
import java.util.Queue;


public class BlockingQueue<T> {

    private int maxSize;
    private Queue<T> queue;

    public BlockingQueue(int size) {
        maxSize = size;
        queue = new LinkedList<T>();
    }

    public synchronized T dequeue() {

        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T value = queue.poll();
        notifyAll();
        return value;
    }

    public synchronized void enqueue(T value) {

        while (queue.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        notifyAll();
    }

    public static void main(String[] args) throws Exception{

        BlockingQueue<String> queue = new BlockingQueue<String>(2);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(400);
    }

}

