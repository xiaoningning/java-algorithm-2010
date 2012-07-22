import java.util.LinkedList;
import java.util.Queue;

/**
 * a node class for tree
 */
public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
        value = v;
        left = null;
        right = null;

    }

    public boolean insert(Node n) {
        if (n == null)
            return false;
        if (value >= n.value) {
            if (left == null)
                left = n;
            else
                left.insert(n);
        } else {
            if (right == null)
                right = n;
            else
                right.insert(n);
        }
        return true;
    }

    public int size() {
        int size = 0;
        if (left != null) {
            size += left.size();
        }
        if (right != null) {
            size += right.size();
        }
        return size + 1;
    }

    public void printBT(){
        Queue<Node> current = new LinkedList<Node>();
        Queue<Node> next = new LinkedList<Node>();

        current.add(this);
        System.out.println(this.value);
        while (!current.isEmpty()){
            Node n = current.poll();
            if(n.left != null){
                System.out.print(n.left.value + " ");
                next.add(n.left);
            }
            if(n.right != null){
                System.out.print(n.right.value + " ");
                next.add(n.right);
            }
            if(current.isEmpty()){
                current.addAll(next);
                next.clear();
                System.out.println();
            }
        }

    }
}
