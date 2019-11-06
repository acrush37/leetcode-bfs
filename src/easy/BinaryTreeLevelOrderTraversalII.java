package easy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        if (root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> m = new LinkedHashMap<>();
        Map<TreeNode, Integer> h = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        h.put(root, 0);
        m.put(0, Arrays.asList(root.val));

        while (!q.isEmpty()) {

            TreeNode node = q.poll();
            int x = 1 + h.get(node);
            List<Integer> list = m.get(x);
            if (list == null) list = new ArrayList<>();

            if (node.left != null) {

                q.offer(node.left);
                h.put(node.left, x);
                list.add(node.left.val);
                m.put(x, list);
            }

            if (node.right != null) {

                q.offer(node.right);
                h.put(node.right, x);
                list.add(node.right.val);
                m.put(x, list);
            }
        }

        List<List<Integer>> result = m.entrySet().stream().map(i -> i.getValue()).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

}
