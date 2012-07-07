public class Producer implements Runnable{

    protected BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.enqueue("1");
            System.out.println("producter 1");
            Thread.sleep(1000);
            queue.enqueue("2");
            System.out.println("producter 2");
            Thread.sleep(1000);
            queue.enqueue("3");
            System.out.println("producter 3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}