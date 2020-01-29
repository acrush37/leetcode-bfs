package medium;

import java.util.ArrayDeque;
import java.util.Queue;

/*
    In an N by N square grid, each cell is either empty (0) or blocked (1).

    A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

    Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
    C_1 is at location (0, 0) (ie. has value grid[0][0])
    C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
    If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
    Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 */
public class ShortestPathInBinaryMatrix {

    public static void main(String... args) {

        int[][] grid = {{0}};
        ShortestPathInBinaryMatrix shortestPathInBinaryMatrix = new ShortestPathInBinaryMatrix();
        System.out.println(shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid));
    }

    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        if (n == 1) return 1;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] t = new boolean[n][n];
        int[] a = {-1, 1, 0, 0, 1, -1, 1, -1};
        int[] b = {0, 0, -1, 1, 1, -1, -1, 1};
        q.offer(new int[]{0, 0, 1});
        t[0][0] = true;

        while (!q.isEmpty()) {

            int[] x = q.poll();

            for (int i = 0; i < 8; i++) {

                int u = x[0] + a[i];
                int v = x[1] + b[i];
                if (u < 0 || v < 0 || u >= n || v >= n || t[u][v] || grid[u][v] == 1) continue;
                if (u == n-1 && v == n-1) return 1 + x[2];
                q.offer(new int[]{u, v, 1 + x[2]});
                t[u][v] = true;
            }
        }

        return -1;
    }

}
