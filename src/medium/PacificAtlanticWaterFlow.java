package medium;

import java.util.*;

/*
    Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
    the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

    Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

    Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 */
public class PacificAtlanticWaterFlow {

    public static void main(String... args) {

        int[][] matrix = {{1, 2, 2, 3, 5},
                          {3, 2, 3, 4, 4},
                          {2, 4, 5, 3, 1},
                          {6, 7, 1, 4, 5},
                          {5, 1, 1, 2, 4}};
        PacificAtlanticWaterFlow pacificAtlanticWaterFlow = new PacificAtlanticWaterFlow();
        System.out.println(pacificAtlanticWaterFlow.pacificAtlantic(matrix));
    }

    private void bfs(int x, int y, int m, int n, int p, int[][] matrix, boolean[][][] t) {

        if (t[x][y][p]) return;
        Queue<int[]> q = new LinkedList<>();
        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, -1, 1};
        t[x][y][p] = true;
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {

            int[] z = q.poll();

            for (int i = 0; i < 4; i++) {

                int u = z[0] + a[i];
                int v = z[1] + b[i];
                if (u < 0 || u >= m || v < 0 || v >= n || t[u][v][p] || matrix[u][v] < matrix[z[0]][z[1]]) continue;
                t[u][v][p] = true;
                q.offer(new int[]{u, v});
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        List<List<Integer>> result = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) return result;
        int n = matrix[0].length;
        boolean[][][] t = new boolean[m][n][2];
        for (int i = 0; i < n; i++) bfs(0, i, m, n,0, matrix, t);
        for (int i = 1; i < m; i++) bfs(i, 0, m, n,0, matrix, t);
        for (int i = 0; i < n; i++) bfs(m-1, i, m, n,1, matrix, t);
        for (int i = 0; i < m-1; i++) bfs(i, n-1, m, n,1, matrix, t);

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (t[i][j][0] && t[i][j][1])
                    result.add(Arrays.asList(i, j));

        return result;
    }

}
