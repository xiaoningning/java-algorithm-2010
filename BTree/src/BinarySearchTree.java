package com.company;

public class BinarySearchTree {
    private Node root;


    public void insert(int value) {
        if (root == null) {
            insertRoot(value);
            return;
        }
        insert(value, root);
    }

    private void insertRoot(int value) {
        this.root = new Node(value);
    }

    private void insert(int value, Node node) {
        if (node.value == value) {
            node.count++;
            return;
        }
        if (node.value > value) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                insert(value, node.left);
            }
        }
        if (node.value < value) {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                insert(value, node.right);
            }
        }
    }


    public void display() {
        display(root, "");
    }

    private void display(Node node, String indent) {
        if (node == null) {
            return;
        }
        System.out.println(indent + node.value);
        display(node.left, indent + "\t");
        display(node.right, indent + "\t");
    }

    public void populate(int[] data) {
        populate(data, 0, data.length - 1);
    }

    private void populate(int[] data, int start, int end) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;
        insert(data[mid]);
        populate(data, start, mid - 1);
        populate(data, start, mid + 1);
    }

    private void range(Node node, int k1, int k2) {
        if (node == null) {
            return;
        }

        if (node.value >= k1 && node.value <= k2) {
            System.out.println(node.value);
        }

        range(node.left, k1, k2);
        range(node.right, k1, k2);
    }


    public int smallest() {
        return smallest(root);
    }

    public int succesor(Node node) {
        return smallest(node.right);
    }

    private int smallest(Node node) {
        if (node.left == null) {
            return node.value;
        }
        return smallest(node.left);
    }

    public boolean isBST() {
        return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isBST(Node node, int max, int min) {
        if (node == null) {
            return true;
        }

        if (node.value >= max || node.value <= min) {
            return false;
        }

        return isBST(node.left, node.value, min) && isBST(node.right, max, node.value);
    }

    public Node rightRotate(Node node) {
//        before swap
        Node x = node;
        Node y = node.left;
        Node t2 = y.right;
//        after swap
        y.right = x;
        x.left = t2;
        return y;
    }
    public Node leftRotate(Node node) {

        // before swap
        Node y = node;
        Node x = y.right;
        Node t2 = x.left;


        // after swap
        x.left = y;
        y.right = t2;

        return x;
    }

    private class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;
        private int count;

        public Node(int value) {
            this.value = value;
            this.count = 1;
        }

    }
}
