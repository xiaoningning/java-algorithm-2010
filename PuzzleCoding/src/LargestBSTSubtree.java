/**
 * Given a binary tree,
 * find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 */
public class LargestBSTSubtree {

    public static class LargestBST {
        public Node node;
        public int maxNode;
        public int min;
        public int max;

        public LargestBST(Node n, int number, int min_value, int max_value) {
            node = n;
            max = max_value;
            min = min_value;
            maxNode = number;
        }
    } // end of class LargestBST

    public static void main(String[] args) {
        Node root = new Node(10);
        Node node5 = new Node(5);
        Node node15 = new Node(15);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node15.right = node7;
        Node node3 = new Node(3);
        Node node12 = new Node(12);
        Node node11 = new Node(11);
        Node node13 = new Node(13);
        Node node14 = new Node(14);

        root.insert(node5);
        root.insert(node6);
        root.insert(node15);
        root.insert(node3);
        root.insert(node12);
        root.insert(node11);
        root.insert(node13);
        root.insert(node14);

        int result = largestBSTSubtree(root);
        System.out.println("largest bst (may not include all children): " + result);

        LargestBST largeBST = largestBSTSubtree1(root);
        if (largeBST != null)
            System.out.println(largeBST.node.value + " : size " + largeBST.maxNode);

        LargestBST largeBST2 = largestBSTSubtree2(root);
        if (largeBST2 != null)
            System.out.println(largeBST2.node.value + " : size " + largeBST2.maxNode);
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST may or may not include all of its descendants.
    public static int largestBSTSubtree(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;

        int leftNode = largestBSTSubtree(node.left);
        int rightNode = largestBSTSubtree(node.right);

        if (node.left != null && node.right != null) {
            if ((node.left.value < node.value) && (node.right.value > node.value)) {
                return leftNode + rightNode + 1;
            } else if (node.left.value < node.value) {
                return leftNode + 1;
            } else if (node.right.value > node.value) {
                return rightNode + 1;
            } else {
                return Math.max(rightNode, leftNode);
            }
        } else if (node.left != null) {
            if (node.left.value < node.value)
                return leftNode + 1;
            else
                return leftNode;
        } else {// if (node.right != null){
            if (node.value < node.right.value)
                return rightNode + 1;
            else
                return rightNode;
        }
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST may or may not include all of its descendants.
    public static LargestBST largestBSTSubtree1(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            return new LargestBST(node, node.size(), node.value, node.value);
        }

        LargestBST leftNode  = largestBSTSubtree1(node.left);
        LargestBST rightNode = largestBSTSubtree1(node.right);

        if (leftNode != null && rightNode != null) {
            if ((node.value > leftNode.max && node.left == leftNode.node)
                    && (node.value < rightNode.min && node.right == rightNode.node)) {

                LargestBST bst = new LargestBST(node,
                        leftNode.maxNode + rightNode.maxNode + 1,
                        leftNode.min,
                        rightNode.max);

                return bst;
            } else if (node.value > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.value);

            } else if (node.value < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.value, rightNode.max);
            } else {
                return (leftNode.maxNode > rightNode.maxNode) ? leftNode : rightNode;
            }
        } else if (leftNode != null) {
            if (node.value > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.value);
            } else {
                return leftNode;
            }

        } else if (rightNode != null) {
            if (node.value < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.value, rightNode.max);
            } else {
                return rightNode;
            }
        }

        return null;
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST must include all of its descendants.
    public static LargestBST largestBSTSubtree2(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            return new LargestBST(node, node.size(), node.value, node.value);
        }

        LargestBST leftNode = largestBSTSubtree2(node.left);
        LargestBST rightNode = largestBSTSubtree2(node.right);

        if (leftNode != null && rightNode != null) {
            if ((node.value > leftNode.max && node.left == leftNode.node)
                    && (node.value < rightNode.min && node.right == rightNode.node)) {

                LargestBST bst = new LargestBST(node,
                        leftNode.maxNode + rightNode.maxNode + 1,
                        leftNode.min,
                        rightNode.max);

                return bst;
            } else {
                return (leftNode.maxNode > rightNode.maxNode) ? leftNode : rightNode;
            }
        } else if (leftNode != null) {
            if (node.value > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.value);
            } else {
                return leftNode;
            }

        } else if (rightNode != null) {
            if (node.value < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.value, rightNode.max);
            } else {
                return rightNode;
            }
        }
        return null;
    }


}
