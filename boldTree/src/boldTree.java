import java.util.*;

public class boldTree {
    private int number;
    private boolean isBold;
    private List<boldTree> children;
    private boolean keep;

    public boldTree(int n, boolean bold) {
        number = n;
        isBold = bold;
        keep = true;
        children = new ArrayList<boldTree>();
    }

    public static boldTree copy(boldTree node){
        return new boldTree(node.number, node.isBold);
    }

    public void addChild(boldTree node) {
        children.add(node);
    }

    public List<boldTree> getChildren() {
        return children;
    }

    public boldTree getChild(int n) {
        for (boldTree c : this.children) {
            if (c.number == n)
                return c;
        }
        return null;
    }

    public void removeChild(boldTree c) {
        if (this.children.contains(c))
            this.children.remove(c);
    }

    //the best, bottom-up by copying
    public static boldTree boldSubTreeCopy(boldTree root) {
        if (root == null)
            return null;

        boldTree tmp = copy(root);

        for(boldTree c : root.getChildren()){
            if(boldSubTreeCopy(c) != null){
                tmp.addChild(c);
            }
        }
        if(tmp.isBold ||tmp.getChildren().size() != 0)
            return tmp;
        else
            return null;
    }

    // use flag, bottom-up
    public static boldTree boldSubTree(boldTree root) {
        if (root == null)
            return null;

        root.keep = root.isBold;

        for(boldTree c : root.getChildren()){
            if(boldSubTree(c).keep)
                root.keep = true;
        }
        return root;
    }

    public static void show2(boldTree n) {
        if (n == null){
            System.out.println("no tree");
            return;
        }

        Queue<boldTree> currentLevel = new LinkedList<boldTree>();
        Queue<boldTree> nextLevel = new LinkedList<boldTree>();
        currentLevel.add(n);
        System.out.println(n.number + "  ");

        while (!currentLevel.isEmpty()) {
            boldTree r = currentLevel.poll();
            for (boldTree c : r.getChildren()) {
                if (c.keep) {
                    if (c.isBold)
                        System.out.print(c.number + "(b)  ");
                    else
                        System.out.print(c.number + "  ");
                }
                nextLevel.add(c);
            }
            if (currentLevel.isEmpty()) {
                currentLevel.addAll( nextLevel);
                nextLevel.clear();
                System.out.println();
            }
        }
    }

    // track non-bold node and remove, top-down approach
    public static boldTree extractBoldSubTree(boldTree root) {
        if (root == null) {
            return null;
        }

        Queue<boldTree> currentLevel = new LinkedList<boldTree>();
        Queue<boldTree> nextLevel = new LinkedList<boldTree>();
        HashMap<boldTree, List<boldTree>> nonBoldNodeList = new HashMap<boldTree, List<boldTree>>();

        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            boldTree r = currentLevel.poll();
            for (boldTree c : r.getChildren()) {

                if (!hasBoldChild(c)) {
                    if (!nonBoldNodeList.containsKey(r))
                        nonBoldNodeList.put(r, new ArrayList<boldTree>());
                    nonBoldNodeList.get(r).add(c);
                } else {
                    nextLevel.add(c);
                }

            }
            if (currentLevel.isEmpty()) {
                currentLevel.addAll(nextLevel);
                nextLevel.clear();
                for (boldTree k : nonBoldNodeList.keySet()) {
                    for (boldTree c : nonBoldNodeList.get(k)) {
                        k.removeChild(c);
                    }
                }
                nonBoldNodeList.clear();
            }
        }
        return root;
    }

    public static boolean hasBoldChild(boldTree n) {
        if(n == null)
            return false;

        for(boldTree c : n.getChildren()){
           if(hasBoldChild(c))
               return true;
        }

        return n.isBold;
    }

    public static void show(boldTree n) {
        if (n == null){
            System.out.println("no tree");
            return;
        }

        Queue<boldTree> currentLevel = new LinkedList<boldTree>();
        Queue<boldTree> nextLevel = new LinkedList<boldTree>();
        currentLevel.add(n);
        System.out.println(n.number + "  ");

        while (!currentLevel.isEmpty()) {
            boldTree r = currentLevel.poll();
            for (boldTree c : r.getChildren()) {
                if (c.isBold)
                    System.out.print(c.number + "(b)  ");
                else
                    System.out.print(c.number + "  ");
                nextLevel.add(c);
            }
            if (currentLevel.isEmpty()) {
                currentLevel = nextLevel;
                nextLevel = new LinkedList<boldTree>();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        boldTree root = new boldTree(1, false);
        boldTree c2 = new boldTree(2, false);
        boldTree c3 = new boldTree(3, false);
        boldTree c4 = new boldTree(4, true);
        root.addChild(c2);
        root.addChild(c3);
        root.addChild(c4);

        boldTree c22 = new boldTree(22, true);
        c2.addChild(c22);
        boldTree c31 = new boldTree(31, false);
        boldTree c32 = new boldTree(32, true);
        c3.addChild(c31);
        c3.addChild(c32);
        boldTree c41 = new boldTree(41, false);
        boldTree c42 = new boldTree(42, false);
        boldTree c43 = new boldTree(43, false);
        c4.addChild(c41);
        c4.addChild(c42);
        c4.addChild(c43);
        boldTree c221 = new boldTree(221, false);
        c22.addChild(c221);
        boldTree c311 = new boldTree(311, true);
        c31.addChild(c311);

        boldTree c421 = new boldTree(421, true);
        boldTree c431 = new boldTree(431, false);
        c42.addChild(c421);
        c43.addChild(c431);

        boldTree root2 = root;

        System.out.println("source tree");
        show(root);

        System.out.println("bold tree");
        boldTree newRoot = extractBoldSubTree(root);
        show(newRoot);

        // need a flag to keep if the node should be included.
        System.out.println("another way to extract bold tree");
        boldTree newRoot2 = boldSubTree(root2);
        show2(newRoot2);

        System.out.println("another way to extract bold tree by copying: ");
        boldTree newRoot3 = boldSubTreeCopy(root2);
        show(newRoot3);


    }
}
