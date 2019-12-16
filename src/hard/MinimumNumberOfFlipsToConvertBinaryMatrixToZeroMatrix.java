package hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
    Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and
    all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1).

    A pair of cells are called neighboors if they share one edge.
    Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

    Binary matrix is a matrix with all cells equal to 0 or 1 only.
    Zero matrix is a matrix with all cells equal to 0.
 */
public class MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {

    public static void main(String... args) {

        int[][] mat = {{1, 1, 1}, {1, 0, 1}, {0, 0, 0}};
        MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix minimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix = new MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix();
        System.out.println(minimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix.minFlips(mat));
    }

    public int minFlips(int[][] mat) {

        int p = 0, s = 0, m = mat.length, n = mat[0].length;
        int[][] c = new int[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                c[i][j] = p;
                s += mat[i][j] << p++;
            }

        if (s == 0) return 0;
        Queue<Object[]> q = new LinkedList<>();
        q.offer(new Object[]{mat, 0, s});
        boolean[] t = new boolean[512];
        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, -1, 1};
        t[s] = true;

        while (!q.isEmpty()) {

            Object[] o = q.poll();
            int[][] f = (int[][]) o[0];
            int d = (int) o[1];

            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {

                    s = (int) o[2];
                    int[][] g = new int[m][n];

                    for (int u = 0; u < m; u++)
                        for (int v = 0; v < n; v++)
                            g[u][v] = f[u][v];

                    g[i][j] = f[i][j] ^ 1;
                    s += f[i][j] == 0 ? 1 << c[i][j] : -(1 << c[i][j]);

                    for (int k = 0; k < 4; k++) {

                        int x = i + a[k];
                        int y = j + b[k];
                        if (x < 0 || x >= m || y < 0 || y >= n) continue;
                        s += f[x][y] == 0 ? 1 << c[x][y] : -(1 << c[x][y]);
                        g[x][y] = f[x][y] ^ 1;
                    }

                    if (s == 0) return 1+d;
                    if (t[s]) continue;
                    t[s] = true;
                    q.offer(new Object[]{g, 1+d, s});
                }
        }

        return -1;
    }

}
