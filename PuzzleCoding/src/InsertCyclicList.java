/**
 * Given a node from a cyclic linked list which has been sorted,
 * write a function to insert a value into the list
 * such that it remains a cyclic sorted list.
 * The given node can be any single node in the list.
 */
public class InsertCyclicList {
    private static class LinkedNode {
        public int value;
        public LinkedNode next;

        public LinkedNode() {
        }

        public LinkedNode(int v) {
            value = v;
            next = null;
        }

        public String toString() {
            return String.valueOf(value);
        }

        public LinkedNode clone() {
            LinkedNode n = new LinkedNode(value);
            n.next = next;
            return n;
        }
    }  // end of linkednode class

    public static void main(String[] args) {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(2);
        LinkedNode n4 = new LinkedNode(4);
        LinkedNode n6 = new LinkedNode(6);
        n1.next = n2;
        n2.next = n4;
        n4.next = n6;
        n6.next = n1;

        int x = 5;
        // list must be sorted.
        insertCyclicList(n1, x);
        LinkedNode p = n1;
        do {
            System.out.print(p.toString());
            p = p.next;
        } while (!p.equals(n1));

    }

    public static void insertCyclicList(LinkedNode head, int x) {
        if (head == null) {
            LinkedNode n = new LinkedNode(x);
            n.next = n;
            head = n;
        }

        LinkedNode p = head.clone();
        LinkedNode prev;
        do {
            prev = p;
            p = p.next;
            if (x <= p.value && x >= prev.value) break;
            // x is min or max of the list
            if ((prev.value > p.value) && (x < p.value || x > prev.value)) break;
        } while (!p.equals(head));

        LinkedNode n = new LinkedNode(x);
        prev.next = n;
        n.next = p;
    }
}
