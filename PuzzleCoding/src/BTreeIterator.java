import java.util.NoSuchElementException;
import java.util.Stack;

public class BTreeIterator {
    private Node root;
    private Stack<Node> stack;
    private Stack<Node> preOrderStack;
    private Stack<Node> leftVisitedStack;

    public BTreeIterator(Node n) {
        root = n;
        stack = new Stack<Node>();
        leftVisitedStack = new Stack<Node>();
        preOrderStack = new Stack<Node>();
        preOrderStack.push(root);
        pushLeft(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void reset() {
        stack.empty();
        leftVisitedStack.empty();
        pushLeft(root);
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException("remove");
    }

    public Node inOrderNext() {
        return next();
    }

    // in-order
    public Node next() {
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

    public Node postOrderNext() {
        if (hasNext()) {
            if (stack.peek().right == null || leftVisitedStack.contains(stack.peek().right)) {
                Node node = stack.pop();
                return node;
            } else {
                leftVisitedStack.push(stack.peek().right);
                pushLeft(stack.peek().right);
                return postOrderNext();
            }

        } else {
            throw new NoSuchElementException();
        }
    }
}
