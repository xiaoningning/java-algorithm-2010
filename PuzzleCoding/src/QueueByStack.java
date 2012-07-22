import java.util.Stack;

/**
 * Implement a queue with two stacks.
 */
public class QueueByStack<T> {
    private Stack<T> head;
    private Stack<T> tail;

    public QueueByStack() {
        head = new Stack<T>();
        tail = new Stack<T>();
    }

    public void enQueue(T v) {
        head.push(v);
    }

    public T deQueue() {

        if (tail.size() == 0) {
            while (head.size() > 0) {
                tail.push(head.pop());
            }
        }
        if (tail.size() == 0)
            throw new RuntimeException("queue is empty.");

        return tail.pop();
    }

    public static void main(String[] args) {
        QueueByStack<Integer> queue = new QueueByStack<Integer>();

        queue.enQueue(2);
        queue.enQueue(5);
        queue.enQueue(1);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());

    }

}
