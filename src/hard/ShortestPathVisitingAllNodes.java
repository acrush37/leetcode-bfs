package hard;

/*
    An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

    graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

    Return the length of the shortest path that visits every node.

    You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 */
public class ShortestPathVisitingAllNodes {

    private int result = Integer.MAX_VALUE;

    public static void main(String... args) {

        int[][] graph = {{1,2,3}, {0,2,3,5}, {0,1,4}, {0,1}, {2,6}, {1}, {4}};
        ShortestPathVisitingAllNodes shortestPathVisitingAllNodes = new ShortestPathVisitingAllNodes();
        System.out.println(shortestPathVisitingAllNodes.shortestPathLength(graph));
    }

    private void dfs(int x, int k, int n, int[][] f, boolean[] t, int s) {

        if (k == n) {
            result = s;
            return;
        }

        for (int i = 0; i < n; i++)
            if (!t[i]) {

                if (s + f[x][i] >= result) continue;
                t[i] = true;
                dfs(i,k+1, n, f, t, s + f[x][i]);
                t[i] = false;
            }
    }

    public int shortestPathLength(int[][] graph) {

        int s = 0;
        int n = graph.length;
        int[][] f = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j) f[i][j] = 100000;

        for (int i = 0; i < n; i++) {
            s += graph[i].length;
            for (int x : graph[i]) f[i][x] = 1;
        }

        if (s == n * (n-1)) return n-1;

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                if (i != k)
                    for (int j = 0; j < n; j++)
                        if (i != j && j != k && f[i][j] != 1)
                            f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j]);

        for (int i = 0; i < n-1; i++) {
            boolean[] t = new boolean[n];
            t[i] = true;
            dfs(i, 1, n, f, t, 0);
        }

        return result;
    }

}
