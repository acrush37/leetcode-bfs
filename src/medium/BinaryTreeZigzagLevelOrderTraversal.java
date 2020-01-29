package medium;

import java.util.*;
import java.util.stream.Collectors;

/*
    Given a binary tree, return the zigzag level order traversal of its nodes' values.
    (ie, from left to right, then right to left for the next level and alternate between).
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String... args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeZigzagLevelOrderTraversal binaryTreeZigzagLevelOrderTraversal = new BinaryTreeZigzagLevelOrderTraversal();
        System.out.println(binaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(root));
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> m = new LinkedHashMap<>();
        Map<TreeNode, Integer> h = new HashMap<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        h.put(root, 0);
        m.put(0, Arrays.asList(root.val));

        while (!q.isEmpty()) {

            TreeNode node = q.poll();
            int x = 1 + h.get(node);
            List<Integer> list = m.get(x);
            if (list == null) list = new ArrayList<>();
            boolean flag = (x & 1) == 0;

            if (node.left != null) {

                q.offer(node.left);
                h.put(node.left, x);
                if (flag) list.add(node.left.val);
                else list.add(0, node.left.val);
                m.put(x, list);
            }

            if (node.right != null) {

                q.offer(node.right);
                h.put(node.right, x);
                if (flag) list.add(node.right.val);
                else list.add(0, node.right.val);
                m.put(x, list);
            }
        }

        return m.entrySet().stream().map(i -> i.getValue()).collect(Collectors.toList());
    }

}
