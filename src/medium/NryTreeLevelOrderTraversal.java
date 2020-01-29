package medium;

import java.util.*;
import java.util.stream.Collectors;

/*
    Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 */
public class NryTreeLevelOrderTraversal {

    public static void main(String... args) {

        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);
        Node node5 = new Node(5, null);
        Node node6 = new Node(6, null);
        node1.children = Arrays.asList(node3, node2, node4);
        node3.children = Arrays.asList(node5, node6);
        NryTreeLevelOrderTraversal nryTreeLevelOrderTraversal = new NryTreeLevelOrderTraversal();
        System.out.println(nryTreeLevelOrderTraversal.levelOrder(node1));
    }

    static class Node {

        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {

        if (root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> m = new LinkedHashMap<>();
        Map<Node, Integer> h = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        h.put(root, 0);
        m.put(0, Arrays.asList(root.val));

        while (!q.isEmpty()) {

            Node node = q.poll();
            int x = 1 + h.get(node);
            List<Integer> list = m.get(x);
            if (list == null) list = new ArrayList<>();
            List<Node> children = node.children;

            if (children != null)
                for (Node child : children) {

                    q.offer(child);
                    h.put(child, x);
                    list.add(child.val);
                    m.put(x, list);
                }
        }

        return m.entrySet().stream().map(i -> i.getValue()).collect(Collectors.toList());
    }

}
