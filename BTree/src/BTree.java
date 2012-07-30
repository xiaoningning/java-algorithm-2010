import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class BTree {
    private int value;
    private BTree left;
    private BTree right;
    private BTree parent;

    public BTree() {
    }

    public BTree(int v) {
        this.value = v;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public static BTree createMinimalBT(BTree root, int[] array, int s, int e) {
        if (s >= 0 && e <= array.length - 1 && s <= e) {
            int m = (e + s) / 2;
            root = new BTree(array[m]);

            root.left = createMinimalBT(root.left, array, s, m - 1);
            root.right = createMinimalBT(root.right, array, m + 1, e);

            return root;

        } else
            return root;
    }

    public static void insertNode(BTree node, int v) {
        if (node != null) {
            if (v <= node.value) {
                if (node.left == null) {
                    node.left = new BTree(v);
                    node.left.parent = node;
                } else
                    insertNode(node.left, v);
            } else {
                if (node.right == null) {
                    node.right = new BTree(v);
                    node.right.parent = node;
                } else
                    insertNode(node.right, v);
            }
        }
    }

    public static void showByLevel(BTree root) {
        if (root == null) {
            System.out.println("a null tree");
            return;
        }
        Queue<BTree> current = new LinkedList<BTree>();
        Queue<BTree> next = new LinkedList<BTree>();
        current.add(root);
        System.out.println(root.value);

        while (!current.isEmpty()) {
            BTree node = current.poll();
            if (node.left != null) {
                next.add(node.left);
                System.out.print(node.left.value + "\t");
            }
            if (node.right != null) {
                next.add(node.right);
                System.out.print(node.right.value + "\t");
            }
            if (current.isEmpty()) {
                current.addAll(next);
                next.clear();
                System.out.println();
            }
        }
    }

    public static int width(BTree root) {
        if (root == null)
            return 0;

        Queue<BTree> current = new LinkedList<BTree>();
        Queue<BTree> next = new LinkedList<BTree>();
        current.add(root);
        int width = 1;

        while (!current.isEmpty()) {
            BTree node = current.poll();
            if (node.left != null) {
                next.add(node.left);

            }
            if (node.right != null) {
                next.add(node.right);

            }
            if (current.isEmpty()) {
                if (next.size() > width)
                    width = next.size();
                current.addAll(next);
                next.clear();
            }

        }
        return width;

    }

    public static int height(BTree node) {
        if (node == null)
            return 0;
        else
            return Math.max(height(node.left), height(node.right)) + 1;

    }

    public static boolean containTree(BTree t1, BTree t2) {
        if (t1 == null)
            return false;
        if (t2 == null)
            return true;

        if (t1.value == t2.value)
            return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
        else if (t1.value < t2.value)
            return containTree(t1.right, t2);
        else
            return containTree(t1.left, t2);
    }

    public static boolean matchTree(BTree t1, BTree t2) {
        if (t1 == null && t2 == null)
            return true;

        else if (t1 != null && t2 != null) {
            if (t1.value == t2.value)
                return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);

        }
        return false;
    }


    public static BTree commonRoot(BTree root, int p, int q) {
        if (root == null)
            return null;
        if (root.value == p || root.value == q)
            return root;

        BTree l = commonRoot(root.left, p, q);
        BTree r = commonRoot(root.right, p, q);

        /*
        if (r != null && r.value != q && r.value != p)
            return r;
        if (l != null && l.value != q && l.value != p)
            return l;
        */
        if (l != null && r != null && l.value != r.value)
            return root;
        else
            return (l != null) ? l : r;

    }

    public static BTree coverNode(BTree root, int p) {
        if (root == null)
            return null;

        BTree l = coverNode(root.left, p);
        BTree r = coverNode(root.right, p);
        // find the lowest one first
        if (l != null || r != null)
            return (l != null) ? l : r;
        else if (root.value == p)
            return root;
        else
            return null;

    }

    public static BTree commonRootTopDown(BTree root, int p, int q) {
        if (root == null)
            return null;
        if (root.value == p && root.value == q)
            return root;

        if (root.value == p) {
            if (coverNode(root.left, q) != null || coverNode(root.right, q) != null || ((coverNode(root.left, q) == null && coverNode(root.right, q) == null)))
                return root;
        } else if (root.value == q) {
            if (coverNode(root.left, p) != null || coverNode(root.right, p) != null || ((coverNode(root.left, q) == null && coverNode(root.right, q) == null)))
                return root;
        } else {
            BTree l = commonRootTopDown(root.left, p, q);
            BTree r = commonRootTopDown(root.right, p, q);
            if (l != null && r != null) {
                if (l.value != r.value)
                    return root;
            }
            return (l != null) ? l : r;
        }

        return null;
    }

    public static void findAllPathSum(BTree root, int k, ArrayList<BTree> p, ArrayList<ArrayList<BTree>> all) {
        if (root == null)
            return;
        ArrayList<BTree> path = new ArrayList<BTree>(p);
        path.add(root);
        int sum = 0;
        for (BTree node : path) {
            sum += node.value;
        }
        if (sum == k) {
            all.add(new ArrayList<BTree>(path));
        }

        findAllPathSum(root.left, k, path, all);
        findAllPathSum(root.right, k, path, all);
    }

    public static ArrayList<ArrayList<BTree>> findAllPathSum(BTree root, int k) {

        ArrayList<ArrayList<BTree>> all = new ArrayList<ArrayList<BTree>>();
        ArrayList<BTree> path = new ArrayList<BTree>();

        findAllPathSum(root, k, path, all);

        return all;

    }

    public static BTree leftMostNode(BTree node) {

        while (node.left != null)
            node = node.left;

        return node;
    }

    // left, root, right
    public static BTree inorderSuc(BTree root, int k) {
        if (root == null)
            return null;
        if (root.value == k) {
            if (root.right != null)
                return leftMostNode(root.right);
            else {
                BTree parent = root.parent;
                BTree tmp = root;
                while (parent != null) {
                    if (parent.left == tmp)
                        break;
                    else { // parent.right == root
                        tmp = parent;
                        parent = parent.parent;

                    }
                }
                return parent;
            }
        } else {
            BTree lnode = inorderSuc(root.left, k);
            BTree rnode = inorderSuc(root.right, k);
            return (lnode != null) ? lnode : rnode;

        }
    }
    //root, left, right
    public static BTree preorderSuc(BTree node, int k) {
        if (node == null)
            return null;
        if (node.value == k) {

            if (node.left != null)
                return node.left;
            else if (node.right != null)
                return node.right;
            else {

                BTree tmp = node;
                BTree parent = node.parent;

                while (parent != null) {
                    if (parent.right == tmp) {
                        tmp = parent;
                        parent = parent.parent;
                    } else {// parent.left == tmp
                        if (parent.right != null)
                            return parent.right;
                        else {
                            tmp = parent;
                            parent = parent.parent;
                        }
                    }

                }
                return parent;

            }
        } else {
            BTree lnode = preorderSuc(node.left, k);
            BTree rnode = preorderSuc(node.right, k);
            return (lnode != null) ? lnode : rnode;
        }
    }

    // left, right, root
    public static BTree postorderSuc(BTree root, int k) {
        if (root == null)
            return null;
        if (root.value == k) {

            BTree parent = root.parent;

            if (parent != null) {
                if (parent.left == root) {
                    if (parent.right != null)
                        return parent.right;
                    else
                        return parent;
                } else // parent.right == root
                    return parent;

            }
            return null;
        } else {
            BTree lnode = postorderSuc(root.left, k);
            BTree rnode = postorderSuc(root.right, k);
            return (lnode != null) ? lnode : rnode;
        }
    }

    public static int treeSize(BTree root) {
        if (root == null) return 0;
        else return treeSize(root.left) + treeSize(root.right) + 1;
    }

    public static BTree k_smallest_element(BTree root, int k) {
        if (k > treeSize(root))
            return null;

        int i = treeSize(root.left);
        if (i == k - 1)
            return root;
        else if (i > k - 1)
            return k_smallest_element(root.left, k);
        else // ( i < k-1)
            return k_smallest_element(root.right, k - i - 1);
    }

    public static int counter = 0;
    public static void k_smallest_element1(BTree root, int k) {
        if (root == null) return;

        k_smallest_element1(root.left, k);

        if(++counter == k)       // count root node
            System.out.println("tree smallest: "+ root.value);

        k_smallest_element1(root.right, k);

    }

    public static void main(String[] args) {
        int[] a1 = new int[]{2, 3, 4, 5, 7, 9, 12, 13, 10, 18, 20};


        BTree t1 = new BTree();
        t1 = createMinimalBT(t1, a1, 0, a1.length - 1);
        System.out.println("tree height: " + height(t1));
        System.out.println("t3 binary tree width " + width(t1));
        System.out.println("tree size: " + treeSize(t1));
        System.out.println("tree 4 smallest: " + k_smallest_element(t1, 4).value);
        k_smallest_element1(t1,3);

        showByLevel(t1);


        int[] a2 = new int[]{1, 3, 5, 6, 9};
        Arrays.sort(a2);

        BTree t2 = new BTree();
        t2 = createMinimalBT(t2, a2, 0, a2.length - 1);
        System.out.println("tree height: " + height(t2));
        System.out.println("t3 binary tree width " + width(t2));
        showByLevel(t2);

        System.out.println(containTree(t1, t2));

        int p = 12, q = 18;
        BTree cr = commonRootTopDown(t1, p, q);
        System.out.println("top down common root: " + p + " and  " + q);
        if (cr != null && coverNode(t1, p) != null && coverNode(t1, q) != null)
            System.out.println(cr.value);

        p = 12;
        q = 13;
        BTree cr1 = commonRoot(t1, p, q);
        System.out.println("common root: " + p + " and  " + q);
        if (cr1 != null && coverNode(t1, p) != null && coverNode(t1, q) != null)
            System.out.println(cr1.value);

        int sum = 19;
        ArrayList<ArrayList<BTree>> result = findAllPathSum(t1, sum);
        System.out.println("find sum: " + sum);
        for (ArrayList<BTree> l : result) {
            for (BTree n : l) {
                System.out.print(n.value + " ");
            }
            System.out.println();
        }

        BTree t3 = new BTree(10);
        insertNode(t3, 3);
        insertNode(t3, 7);
        insertNode(t3, 4);
        insertNode(t3, 5);
        insertNode(t3, 2);
        insertNode(t3, 1);
        insertNode(t3, 0);
        insertNode(t3, 15);
        insertNode(t3, 12);
        insertNode(t3, 18);
        insertNode(t3, 16);
        insertNode(t3, 20);

        System.out.println("t3 binary tree");
        System.out.println("t3 binary tree width " + width(t3));
        showByLevel(t3);

        int suc = 3;
        BTree nextNode = inorderSuc(t3, suc);
        System.out.println("t3 inorder successor " + suc);
        if (nextNode != null)
            System.out.println(nextNode.value);

        int suc2 = 3;
        BTree nextNode1 = postorderSuc(t3, suc2);
        System.out.println("t3 postorder successor " + suc2);
        if (nextNode1 != null)
            System.out.println(nextNode1.value);

        int suc3 = 0;
        BTree nextNode2 = preorderSuc(t3, suc3);
        System.out.println("t3 preorder successor " + suc3);
        if (nextNode2 != null)
            System.out.println(nextNode2.value);

    }

}

