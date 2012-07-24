import java.util.Stack;

/**
 * The discs differ in size and are initially arranged on one of the poles,
 * in order from largest (disc n) at the bottom to smallest (disc 1) at the top.
 * The task is to move the stack of discs to another pole, while obeying the following rules:
 * <p/>
 * Move only one disc at a time.
 * Never place a disc on a smaller one.
 * <p/>
 * Move the top N - 1 disks from Src to Aux (using Dst as an intermediary peg)
 * Move the bottom disk from Src to Dst
 * Move N - 1 disks from Aux to Dst (using Src as an intermediary peg)
 */
public class HanoiTower {
    public Stack<Integer> disks;
    public int index;

    public HanoiTower(int i) {
        index = i;
        disks = new Stack<Integer>();

    }

    public void addDisk(int disk) {
        if (!disks.isEmpty()) {
            if (disks.peek() <= disk) {
                System.out.println("error: " + disk + " larger than " + disks.peek());
            }
        }
        disks.push(disk);
    }

    public void printTower() {
        System.out.println("tower " + index + " : " + disks.toString());
    }

    public void moveTopDisk(HanoiTower tower) {
        if (!disks.isEmpty()) {
            Integer disk = disks.pop();
            tower.addDisk(disk);
            System.out.println("move disk " + disk + " from tower " + this.index + " to tower " + tower.index);
        } else {
            System.out.println("tower " + index + " is empty.");
        }
    }

    public void moveDisks(int n, HanoiTower dest, HanoiTower aux) {
        if (n > 0) {
            moveDisks(n - 1, aux, dest);
            moveTopDisk(dest);
            aux.moveDisks(n - 1, dest, this);
        }
    }

    public static void main(String[] args) {
        int N = 3;
        HanoiTower[] towers = new HanoiTower[N];
        for (int i = 1; i <= N; i++) {
            towers[i - 1] = new HanoiTower(i);
        }
        for (int i = N; i > 0; i--) {
            towers[0].addDisk(i);
        }
        towers[0].printTower();
        towers[0].moveDisks(N, towers[2], towers[1]);
        towers[2].printTower();


    }
}
