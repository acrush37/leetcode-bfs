package medium;

import java.util.ArrayDeque;
import java.util.Queue;

/*
    Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land,
    find a water cell such that its distance to the nearest land cell is maximized and return the distance.

    The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

    If no land or water exists in the grid, return -1.
 */
public class AsFarFromLandAsPossible {

    public static void main(String... args) {

        int[][] grid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        AsFarFromLandAsPossible asFarFromLandAsPossible = new AsFarFromLandAsPossible();
        System.out.println(asFarFromLandAsPossible.maxDistance(grid));
    }

    private int bfs(int x, int y, int n, int[][] grid, boolean[][] f) {

        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, -1, 1};
        int[][] c = new int[n][n];
        int[][] d = new int[n][n];
        boolean[][] t = new boolean[n][n];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, 0});
        t[x][y] = f[x][y] = true;
        c[x][y] = x;
        d[x][y] = y;

        while (!q.isEmpty()) {

            int[] g = q.poll();

            for (int i = 0; i < 4; i++) {

                int u = g[0] + a[i];
                int v = g[1] + b[i];
                if (u < 0 || u >= n || v < 0 || v >= n || t[u][v]) continue;
                c[u][v] = g[0];
                d[u][v] = g[1];

                if (grid[u][v] == 1) {

                    while (c[u][v] != x || d[u][v] != y) {

                        int pu = c[u][v];
                        int pv = d[u][v];
                        f[pu][pv] = true;
                        u = pu;
                        v = pv;
                    }

                    return 1 + g[2];
                }

                t[u][v] = true;
                q.offer(new int[]{u, v, 1 + g[2]});
            }
        }

        return 0;
    }

    public int maxDistance(int[][] grid) {

        boolean zero = false;
        boolean one = false;
        int n = grid.length;
        boolean[][] f = new boolean[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1) one = true;
                else zero = true;

        if (!zero || !one) return -1;
        int max = 1;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 0 && !f[i][j])
                    max = Math.max(max, bfs(i, j, n, grid, f));

        return max;
    }

}
