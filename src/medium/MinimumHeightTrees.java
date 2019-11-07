package medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    For an undirected graph with tree characteristics, we can choose any node as the root.
    The result graph is then a rooted tree.
    Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
    Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 */
public class MinimumHeightTrees {

    private int max;
    private int min;
    
    public static void main(String... args) {

        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        MinimumHeightTrees minimumHeightTrees = new MinimumHeightTrees();
        System.out.println(minimumHeightTrees.findMinHeightTrees(6, edges));
    }

    private void dfs(int k, int x, Set<Integer>[] s, boolean[] t) {

        if (max > min) return;
        t[x] = true;
        max = Math.max(max, k);
        for (int y : s[x]) if (!t[y]) dfs(k+1, y, s, t);
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        min = n;
        int[] f = new int[n];
        Set<Integer>[] s = new Set[n];
        for (int i = 0; i < n; i++) s[i] = new HashSet<>();

        for (int[] x : edges) {

            s[x[0]].add(x[1]);
            s[x[1]].add(x[0]);
        }

        for (int i = 0; i < n; i++) {

            max = 0;
            dfs(0, i, s, new boolean[n]);
            f[i] = max;
            min = Math.min(min, max);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) if (f[i] == min) result.add(i);
        return result;
    }

}
