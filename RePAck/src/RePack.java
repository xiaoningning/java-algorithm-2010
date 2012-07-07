import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Card shuffle problem. I have a card pack of 313 cards.
 * I remove the topmost card from card pack and place it on desk and
 * I remove the next one and put it below the pack of cards(that you are holding).
 * keep doing this till all the cards are on desk.
 * Pick up the card stack from desk and repeat these steps
 * till you find retrieve the original cards ordered.
 * I was asked to code to find how many rounds it will take to retrieve pack order.
 */
public class RePack {

    public static void main(String args[]) {
        RePack rp = new RePack();
        rp.init();

        rp.goOverAllRounds();

    }

    int capacity = 313;
    int countRound = 0;

    Queue<Integer> pack = new LinkedList<Integer>();
    Stack<Integer> desk = new Stack<Integer>();

    public void init() {
        for (int i = 0; i < capacity; i++) {
            this.pack.add(i);
        }

    }

    public void goOverAllRounds() {
        while (!this.done) {
            this.oneRound();
            this.countRound++;

            if (this.done) {
                System.out.println();
                System.out.println("**" + this.countAction + " total rounds :" + this.countRound + "***");
            }

            /*
            for (int i = 0; i < this.capacity; i++) {
                System.out.println(i + ":" + this.desk.get(i));
            }
            */

            // System.out.println();
            if (!this.done) {
                //reload from desk
                this.pack.clear();
                this.pack.addAll(this.desk);
                this.desk.clear();

            }

        }
    }

    int countAction = 0;
    boolean done = false;

    public void oneRound() {
        while (!this.pack.isEmpty()) {
            this.oneAction1();
        }
    }

    // my cleaner version
    public void oneAction1() {

        while (this.pack.size() > 0) {
            this.countAction++;

            this.desk.add(this.pack.poll());
            if (this.pack.size() > 1)
                this.pack.add(this.pack.poll());

        }
        // check the order
        for (int i = 0; i < this.capacity; ++i) {
            if (this.desk.get(i) != i)
                return;
        }

        this.done = true;
        return;

    }


    public void oneAction() {
        this.countAction++;
        //System.out.println("loop count:" + this.countAction);

        if (this.pack.size() == 1) {
            //just put to desk
            this.desk.add(this.pack.poll());

            //check
            for (int i = 0; i < this.capacity; i++) {
                boolean correct = (this.desk.get(i) == i);
                if (!correct) {
                    //reload
                    return;
                }

            }
            // System.out.print("correct : " + this.countAction);
            this.done = true;
            return;

        } else {
            // move to desk
            this.desk.add(this.pack.poll());

            // move back to pack
            this.pack.add(this.pack.poll());

        }


    }


}

