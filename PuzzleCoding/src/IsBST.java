/**
 * determine if a given binary tree is a binary search tree.
 */
public class IsBST {
    public static void main(String[] args) {
        Node root = new Node(10);
        Node node5 = new Node(5);
        Node node15 = new Node(15);
        Node node6 = new Node(6);
        Node node20 = new Node(20);
        Node node3 = new Node(3);
        Node node12 = new Node(12);

        root.insert(node5);
        root.insert(node6);
        root.insert(node15);
        root.insert(node20);
        root.insert(node3);
        root.insert(node12);

        System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(isBST1(root, Integer.MIN_VALUE));

        BTreeIterator treeIterator = new BTreeIterator(root);
        while (treeIterator.hasNext()){
            Node  next = treeIterator.inOrderNext();
            System.out.print(next.value + " ");
        }
        System.out.println();

        while (treeIterator.hasNextPostOrder()){
            Node  next = treeIterator.postOrderNext();
            System.out.print(next.value + " ");
        }
        System.out.println();

        treeIterator.reset();
        while (treeIterator.hasNextPreOrder()){
            Node  next = treeIterator.preOrderNext();
            System.out.print(next.value + " ");
        }
        System.out.println();
    }

    public static boolean isBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if ((min < node.value) && (node.value < max)) {
            return isBST(node.left, min, node.value) &&
                    isBST(node.right, node.value, max);
        } else {
            return false;
        }
    }

    public static boolean isBST1(Node node, int prev) {
        if (node == null) {
            return true;
        }
        if (isBST1(node.left, prev)) {
            if (node.value > prev) {
                prev = node.value;
                return isBST1(node.right, prev);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
