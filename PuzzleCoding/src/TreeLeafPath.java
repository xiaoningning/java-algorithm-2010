import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

/**
 * print out all leaf node path with non recursive method
 * The structure of node is *NOT* binary tree, is just a normal tree.
 * <p/>
 * print out all leaf node path
 * //              12
 * //    4         8        22
 * //1  2  3     9     18   24
 * <p/>
 * the output is like:
 * <p/>
 * 12, 4, 1,
 * 12, 4, 2,
 * 12, 4, 3,
 * 12, 4, 8, 9,
 * 12, 4, 8, 22, 18,
 * 12, 4, 8, 22, 24,
 */

public class TreeLeafPath {
    public Integer value;
    public ArrayList<TreeLeafPath> children;

    public TreeLeafPath(Integer v) {
        value = v;
        children = new ArrayList<TreeLeafPath>();
    }

    public void addTree(TreeLeafPath node) {
        children.add(node);
    }

    public static void printPath(Stack<TreeLeafPath> s) {

        for (int i = 0; i < s.size(); i++) {
            if (i + 1 == s.size())
                System.out.print(s.get(i).value);
            else
                System.out.print(s.get(i).value + "->");
        }
        System.out.println();
    }

    public static void leafPath(TreeLeafPath node, Stack<TreeLeafPath> stack) {

        stack.push(node);

        TreeLeafPath current = stack.peek();
        ArrayList<TreeLeafPath> children = current.children;

        if (children.size() == 0) {
            printPath(stack);
            stack.pop();
            return;
        } else {
            for (TreeLeafPath c : children) {
                leafPath(c, stack);
            }
            stack.pop();
            return;
        }

    }


    public static void main(String[] args) {

        TreeLeafPath root = new TreeLeafPath(12);
        TreeLeafPath node4 = new TreeLeafPath(4);
        TreeLeafPath node8 = new TreeLeafPath(8);
        TreeLeafPath node1 = new TreeLeafPath(1);
        TreeLeafPath node2 = new TreeLeafPath(2);
        TreeLeafPath node3 = new TreeLeafPath(3);
        TreeLeafPath node9 = new TreeLeafPath(9);
        TreeLeafPath node18 = new TreeLeafPath(18);
        TreeLeafPath node22 = new TreeLeafPath(22);
        TreeLeafPath node16 = new TreeLeafPath(16);

        node18.addTree(node16);
        node18.addTree(node22);
        node8.addTree(node9);
        node8.addTree(node18);
        node4.addTree(node1);
        node4.addTree(node2);
        node4.addTree(node3);
        root.addTree(node4);
        root.addTree(node8);

        Stack<TreeLeafPath> stack = new Stack<TreeLeafPath>();
        leafPath(root, stack);
        System.out.println();

    }
}
