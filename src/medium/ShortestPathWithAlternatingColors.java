package medium;

import java.util.ArrayDeque;
import java.util.Queue;

/*
    Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

    Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

    Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 */
public class ShortestPathWithAlternatingColors {

    public static void main(String... args) {

        int[][] red = {{0, 1}, {1, 2}};
        int[][] blue = {};
        ShortestPathWithAlternatingColors shortestPathWithAlternatingColors = new ShortestPathWithAlternatingColors();
        System.out.println(shortestPathWithAlternatingColors.shortestAlternatingPaths(3, red, blue));
    }

    private int bfs(int target, int n, boolean[][][] f) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] t = new boolean[n][n][2];
        q.offer(new int[]{0, 0, 0});
        q.offer(new int[]{0, 0, 1});

        while (!q.isEmpty()) {

            int[] x = q.poll();

            for (int i = 1; i < n; i++)
                if (!t[x[0]][i][x[2]] && f[x[0]][i][x[2]]) {

                    if (i == target) return 1 + x[1];
                    t[x[0]][i][x[2]] = true;
                    q.offer(new int[]{i, 1 + x[1], 1 - x[2]});
                }
        }

        return -1;
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {

        int[] a = new int[n];
        boolean[][][] f = new boolean[n][n][2];
        for (int[] x : red_edges) f[x[0]][x[1]][0] = true;
        for (int[] x : blue_edges) f[x[0]][x[1]][1] = true;
        for (int i = 1; i < n; i++) a[i] = bfs(i, n, f);
        return a;
    }

}
