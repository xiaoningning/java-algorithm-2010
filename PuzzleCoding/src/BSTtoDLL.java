import java.util.ArrayList;
import java.util.Stack;

/**
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the
 * left and right pointers as synonymous to the previous and next pointers in a
 * doubly-linked list.
 * <p/>
 * This is a modified in-order traversal adapted to this problem. prev (init to
 * NULL) is used to keep track of previously traversed node. head pointer is
 * updated with the list's head as recursion ends.
 * <p/>
 * do it in place. time complexity: O(n)
 */
public class BSTtoDLL {
    public static void main(String[] args) {
        Node root = new Node(10);
        Node node5 = new Node(5);
        Node node15 = new Node(15);
        Node node6 = new Node(6);
        Node node20 = new Node(20);
        Node node3 = new Node(3);
        Node node12 = new Node(12);
        Node node14 = new Node(14);

        root.insert(node5);
        root.insert(node6);
        root.insert(node15);
        root.insert(node20);
        root.insert(node3);
        root.insert(node12);
        root.insert(node14);

        ArrayList<Node> dll = bstToDLL(root);
        printDll(dll);

        bstToDllinPlace(root);
        printDll(head);

    }

    public static void printDll(Node n) {
        if (n == null) {
            System.out.println("null");
        } else {
            int s = n.value;
            System.out.print(n.value + " ");
            n = n.right;
            while (n.value != s) {
                System.out.print(n.value + " ");
                n = n.right;
            }
        }
        System.out.println();
    }

    public static void printDll(ArrayList<Node> dll) {
        if (dll.size() != 0) {
            for (Node n : dll) {
                System.out.print(n.value + " ");
            }
        }
        System.out.println();
    }

    // not in place
    public static ArrayList<Node> bstToDLL(Node node) {
        if (node == null)
            return null;

        Stack<Node> stack = new Stack<Node>();
        ArrayList<Node> dll = new ArrayList<Node>();
        Node current = node;
        boolean done = false;
        while (!done) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                if (stack.isEmpty()) {
                    done = true;
                } else {
                    current = stack.pop();
                    dll.add(current);
                    current = current.right;
                }
            }
        }
        return dll;
    }

    // in place approach
    static Node prev, head;
    public static void bstToDllinPlace(Node p) {
        if (p == null)
            return;

        bstToDllinPlace(p.left);
        p.left = prev;
        // current node (smallest element) is head of
        // the list if previous node is not available
        if (prev == null) {
            head = p;
        } else {
            prev.right = p;
        }

        System.out.print("parent: " + p.value + " prev: ");
        nodeToString(prev);
        System.out.print(" head: ");
        nodeToString(head);

        // as soon as the recursion ends, the head's left pointer
        // points to the last node, and the last node's right pointer
        // points to the head pointer.

        Node rightNode = p.right;
        head.left = p;
        p.right = head;
        prev = p;

        bstToDllinPlace(rightNode);

        System.out.print("parent: " + p.value + " prev: ");
        nodeToString(prev);
        System.out.print(" head: ");
        nodeToString(head);

    }

    public static void nodeToString(Node n) {
        if (n == null) {
            System.out.println("null");
        } else {
            System.out.println(n.value);
        }

    }

}