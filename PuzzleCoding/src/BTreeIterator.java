import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BTreeIterator {
    private Node root;
    private Stack<Node> stack;
    private Stack<Node> preOrderStack;
    private Queue<Node> postOrderQueue;

    public BTreeIterator(Node n) {
        root = n;
        stack = new Stack<Node>();
        pushLeft(root);

        // for post-order, no need for in-order
        postOrderQueue = new LinkedList<Node>();
        pushPostOrder(root);
        // for pre-order
        preOrderStack = new Stack<Node>();
        preOrderStack.push(root);
    }
    //in-order
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void reset() {
        stack.empty();
        postOrderQueue.clear();
        pushLeft(root);
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("remove");
    }

    public Node next() {
        return inOrderNext();
    }

    // in-order : left, root, right
    public Node inOrderNext() {
        if (hasNext()) {
            Node node = stack.pop();
            pushLeft(node.right);
            return node;
        } else {
            throw new NoSuchElementException();
        }
    }

    private void pushLeft(Node node) {
        if (node != null) {
            stack.push(node);
            pushLeft(node.left);
        }
    }

    public boolean hasNextPreOrder(){
        return !preOrderStack.isEmpty();
    }

    //pre-order: root, left, right
    public Node preOrderNext() {
        if (!preOrderStack.isEmpty()) {
            Node node = preOrderStack.pop();

            if (node.right != null)
                preOrderStack.push(node.right);
            if (node.left != null)
                preOrderStack.push(node.left);

            return node;
        } else {
            return null;
        }
    }
    public void pushPostOrder(Node n){
        if(n == null)
            return;
        if(n.left != null)
            pushPostOrder(n.left);
        if(n.right != null)
            pushPostOrder(n.right);

        postOrderQueue.add(n);
    }
    public boolean hasNextPostOrder(){
        return !postOrderQueue.isEmpty();
    }
    //post-order: left, right, root
    public Node postOrderNext() {
        if (!postOrderQueue.isEmpty()) {
            return postOrderQueue.poll();
        } else {
            throw new NoSuchElementException();
        }
    }
}
