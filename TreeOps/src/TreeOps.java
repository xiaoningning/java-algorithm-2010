import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ningwei
 * Date: 5/6/12
 * Time: 6:34 PM
 * a tree operation drill
 */
public class TreeOps<Key extends Comparable<Key>, Value> {
    private Node root;

    public class Node {
        private Node left;
        private Node right;
        private Node parent;
        private boolean visit;
        private Value val;
        private Key key;

        public Node(Key k, Value v) {
            this.key = k;
            this.val = v;
            this.left = null;
            this.right = null;
            this.parent = null;
        }


    }

    public Node getRoot() {
        return root;
    }


    public void insert(Key k, Value v) {

        if (v == null)
            return;
        else {

            root = insert(root, k, v);
        }


    }

    public Node insert(Node x, Key k, Value v) {
        if (x == null) {

            return new Node(k, v);
        }
        // bst
        int comp = k.compareTo(x.key);
        if (comp <= 0) {
            if (x.left != null)
                x.left = insert(x.left, k, v);
            else
                x.left = new Node(k, v);

        } else if (comp > 0) {
            if (x.right != null)
                x.right = insert(x.right, k, v);
            else
                x.right = new Node(k, v);

        } else
            x.val = v;
        return x;
    }

    public List<Node> getChildren(Node x) {
        List<Node> children = new ArrayList<Node>();
        if (x.left != null)
            children.add(x.left);
        if (x.right != null)
            children.add(x.right);

        return children;
    }


    public void DFS(Node x, Key k) {
        if (x == null || k == null){
            show(x);
            return;
        }

        x.visit = true;
        if (k.compareTo(x.key) == 0)
            show(x);
        else if (getChildren(x).size() != 0) {
            for (Node n : getChildren(x)) {
                n.visit = true;

                DFS(n, k);


            }
        }


    }

    public void BFS(Node x, Key k) {

        if (x == null || k == null){
            show(x);
            return;
        }

        x.visit = true;
        if (k.compareTo(x.key) == 0) {
            show(x);
        } else if (getChildren(x).size() != 0) {

            Queue<Node> queue = new LinkedList<Node>();

            queue.add(x);

            while (!queue.isEmpty()) {
                Node r = queue.poll();

                for (Node n : getChildren(r)) {
                    n.visit = true;
                    if (k.compareTo(n.key) == 0) {
                        show(n);
                    } else {
                        queue.add(n);
                    }


                }

            }
        }


    }

    public void show(Node x) {
        if (x == null)
            System.out.println("Null node");
        else
            System.out.println(x.key + " : " + x.val);
    }

    public void intoLinkedList(Node x) {
        LinkedList<Node> list = new LinkedList<Node>();
        list = intoLinkedList(x, list);
        show(list);
    }

    public LinkedList<Node> intoLinkedList(Node x, LinkedList<Node> list) {

        if (x != null) {

            list = intoLinkedList(x.left, list);
            list.add(x);
            list = intoLinkedList(x.right, list);

        }

        return list;
    }

    public TreeOps<Key, Value> mergeBst(TreeOps<Key, Value> bst12, Node r, Node l) {
        LinkedList<Node> rl = intoLinkedList(r, new LinkedList<Node>());
        show(rl);
        LinkedList<Node> ll = intoLinkedList(l, new LinkedList<Node>());

        show(ll);
        LinkedList<Node> ml = mergeList(rl, intoLinkedList(l, new LinkedList<Node>()));

        return list2Bst(bst12, ml);


    }

    public TreeOps<Key, Value> list2Bst(TreeOps<Key, Value> bst12, LinkedList<Node> l){
        int lo = 0, hi = l.size()-1, m = (lo+hi)/2;
        bst12.insert(l.get(m).key, l.get(m).val);
        for(int j= 1 ; m -j >=0 || m+j <= hi; j++){
            bst12.insert(l.get(m-j).key, l.get(m-j).val);
            bst12.insert(l.get(m+j).key, l.get(m+j).val);
        }
        return bst12;
    }


    public  LinkedList<Node> mergeList(LinkedList<Node> r, LinkedList<Node> l) {
        LinkedList<Node> ml = new LinkedList<Node>();
        while (r.size() != 0 && l.size() != 0)
            if (r.getLast().key.compareTo(l.getLast().key) < 0) {
                ml.addFirst(l.getLast());
                l.removeLast();
            } else if (r.getLast().key.compareTo(l.getLast().key) > 0) {
                ml.addFirst(r.getLast());
                r.removeLast();
            } else {
                ml.addFirst(r.getLast());
                r.removeLast();
                ml.addFirst(l.getLast());
                l.removeLast();
            }


        if (r.size() > 0)
            ml.addAll(0, r);
        if (l.size() > 0)
            ml.addAll(0, l);

        show(ml);
        return ml;

    }

    public  void show(LinkedList<Node> list) {
        System.out.println("bst <-> list");
        for (Node n : list) {

            System.out.print(n.key + " -> " + n.val + " ");
        }
        System.out.println();
    }

    public void inOrderShow(Node root) {
        if (root == null)
            return;
        else {
            inOrderShow(root.left);
            System.out.println(root.key + " -> " + root.val);
            inOrderShow(root.right);

        }

    }


    public int depth(Node n) {
        if (n == null)
            return 0;
        else // (n.left != null || n.right != null) {
            return 1 + Math.max(depth(n.left), depth(n.right));
    }

    //pre-order show
    public void preOrderShow(Node n){
        if (n == null)
            return;
        else {
            int level = 1;
            System.out.println(n.key + " ( " + n.val + ") ");
            Queue<Node> cq = new LinkedList<Node>(), nq = new LinkedList<Node>();

            cq.add(n);

            while(!cq.isEmpty()){
                Node r = cq.poll();

                if(r.left != null)                      {
                    System.out.print( r.left.key + " (" + r.left.val+") ");
                    nq.add(r.left);
                }
                if(r.right != null)                      {
                    System.out.print(r.right.key + "( " + r.right.val+") ");
                    nq.add(r.right);
                }
                if(cq.isEmpty()){

                    cq = nq;
                    nq = new LinkedList<Node>();

                    level++;
                    System.out.println();
                }


            }

        }
    }

    public static void main(String args[]) {
        TreeOps<Integer, String> bst1 = new TreeOps<Integer, String>();
        bst1.insert(1, "a");
        bst1.insert(2, "b");
        bst1.insert(-1, "c");
        bst1.insert(3, "d");
        bst1.insert(-2, "e");

        System.out.println("bst1 search:");
        bst1.inOrderShow(bst1.getRoot());
        bst1.BFS(bst1.root, 3);
        bst1.DFS(bst1.root, 3);

        System.out.println("bst1 pre-order show:");
        bst1.preOrderShow(bst1.getRoot());

        System.out.println("bst 1 list:");

        bst1.intoLinkedList(bst1.getRoot());

        TreeOps<Integer, String> bst2 = new TreeOps<Integer, String>();
        bst2.insert(3, "a2");
        bst2.insert(2, "b2");
        bst2.insert(0, "c2");
        bst2.insert(-1, "d2");
        bst2.insert(1, "e2");
        bst2.insert(5, "f2");
        bst2.insert(4, "g2");
        bst2.insert(6, "h2");
        bst2.insert(-3, "m2");
        bst2.insert(-4, "n2");


        System.out.println("bst2 search:");
        bst2.show(bst2.getRoot());
        bst2.BFS(bst2.root, 2);
        bst2.DFS(bst2.root, -1);



        System.out.println("bst 2 list:");

        bst2.intoLinkedList(bst2.getRoot());

        System.out.println("bst2 inorder show:");

        bst2.inOrderShow(bst2.getRoot());
        System.out.println("bst2 pre-order show:");
        bst2.preOrderShow(bst2.getRoot());

        System.out.println("height: ");
        System.out.println(bst2.depth(bst2.getRoot()));

        System.out.println("merger");

        TreeOps<Integer, String> bst12 = new TreeOps<Integer, String>();
        bst12.mergeBst(bst12, bst1.getRoot(), bst2.getRoot()).preOrderShow(bst12.getRoot());

        /*
        Stack<String> stack = new Stack<String>();
        stack.push("a");
        stack.push("b");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        */

    }
}
