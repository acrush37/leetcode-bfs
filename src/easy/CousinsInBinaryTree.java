package easy;

import java.util.LinkedList;
import java.util.Queue;

/*
    In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

    Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

    We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

    Return true if and only if the nodes corresponding to the values x and y are cousins.
 */
public class CousinsInBinaryTree {

    public static void main(String... args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        CousinsInBinaryTree cousinsInBinaryTree = new CousinsInBinaryTree();
        System.out.println(cousinsInBinaryTree.isCousins(root, 4, 3));
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isCousins(TreeNode root, int x, int y) {

        int[] d = new int[101];
        int[] p = new int[101];
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {

            TreeNode node = q.poll();

            if (node.left != null) {

                p[node.left.val] = node.val;
                d[node.left.val] = 1 + d[node.val];
                q.offer(node.left);
            }

            if (node.right != null) {

                p[node.right.val] = node.val;
                d[node.right.val] = 1 + d[node.val];
                q.offer(node.right);
            }
        }

        return d[x] == d[y] && p[x] != p[y];
    }

}
