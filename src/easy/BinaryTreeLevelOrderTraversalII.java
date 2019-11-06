package easy;

import java.util.List;

/*
    Given a binary tree, return the bottom-up level order traversal of its nodes' values.
    (ie, from left to right, level by level from leaf to root).
 */
public class BinaryTreeLevelOrderTraversalII {

    public static void main(String... args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeLevelOrderTraversalII binaryTreeLevelOrderTraversalII = new BinaryTreeLevelOrderTraversalII();
        System.out.println(binaryTreeLevelOrderTraversalII.levelOrderBottom(root));
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        return null;
    }

}
