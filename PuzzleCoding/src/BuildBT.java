import java.util.Arrays;
import java.util.HashMap;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * in-order: left, root, right
 * pre-order: root, left, right
 * <p/>
 * <p/>
 * ________ 7______
 * /                  \
 * __10__             ___2
 * /      \           /
 * 4       3        _8
 *          \     /
 *          1   11
 * preorder = {7,10,4,3,1,2,8,11}
 * inorder = {4,10,3,1,7,11,8,2}
 */
public class BuildBT {
    public static void main(String[] args) {
        int[] preorder = new int[]{7, 10, 4, 3, 1, 2, 8, 11};
        int[] inorder = new int[]{4, 10, 3, 1, 7, 11, 8, 2};

        // build b tree
        Node bt = buildBTInorderPreorder(inorder, preorder);
        bt.printBT();
    }

    public static HashMap<Integer, Integer> mapIndex(int[] inorder) {
        HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            index.put(inorder[i], i);
        }
        return index;
    }

    public static Node buildBTInorderPreorder(int[] in,
                                              int[] pre) {
        if (in.length == 0 || pre.length != in.length)
            return null;

        // get index of element of inOrder
        HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            index.put(in[i], i);
        }

        Node root = new Node(pre[0]);
        int rootIndex = index.get(pre[0]);
        root.left = buildBTInorderPreorder(Arrays.copyOfRange(in, 0, rootIndex),
                Arrays.copyOfRange(pre, 1, rootIndex+1));
        root.right = buildBTInorderPreorder(Arrays.copyOfRange(in, rootIndex + 1, in.length),
                Arrays.copyOfRange(pre, rootIndex+1, pre.length));
        return root;
    }
}
