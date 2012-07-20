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
}
