package hard;

import java.util.LinkedList;
import java.util.Queue;

/*
    Storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

    The game is represented by a grid of size m x n, where each element is a wall, floor, or a box.

    Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.
 */
public class MinimumMovesToMoveBoxToTarget {

    private int[] a = {-1, 1, 0, 0};
    private int[] b = {0, 0, 1, -1};

    public static void main(String... args) {

        char[][] grid = {{'#','.','.','#','T','#','#','#','#'},
                         {'#','.','.','#','.','#','.','.','#'},
                         {'#','.','.','#','.','#','B','.','#'},
                         {'#','.','.','.','.','.','.','.','#'},
                         {'#','.','.','.','.','#','.','S','#'},
                         {'#','.','.','#','.','#','#','#','#'}};
        MinimumMovesToMoveBoxToTarget minimumMovesToMoveBoxToTarget = new MinimumMovesToMoveBoxToTarget();
        System.out.println(minimumMovesToMoveBoxToTarget.minPushBox(grid));
    }

    private boolean push(int sx, int sy, int x, int y, int bx, int by, int m, int n, char[][] grid) {

        if (sx == x && sy == y) return true;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] t = new boolean[m][n];
        q.offer(new int[]{sx, sy});
        t[sx][sy] = true;

        while (!q.isEmpty()) {

            int[] c = q.poll();

            for (int i = 0; i <= 3; i++)  {

                int u = c[0] + a[i];
                int v = c[1] + b[i];
                if (u < 0 || v < 0 || u >= m || v >= n || t[u][v] || grid[u][v] == '#' || u == bx && v == by) continue;
                if (u == x && v == y) return true;
                q.offer(new int[]{u, v});
                t[u][v] = true;
            }
        }

        return false;
    }

    public int minPushBox(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] t = new boolean[m][n][4];
        Queue<int[]> q = new LinkedList<>();
        int tx = 0, ty = 0, bx = 0, by = 0, sx = 0 ,sy = 0;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 'T') {
                    tx = i;
                    ty = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                } else if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

        q.offer(new int[]{0, bx, by, sx, sy});

        while (!q.isEmpty()) {

            int[] c = q.poll();

            for (int i = 0; i <= 3; i++) {

                int x = c[1] + a[i];
                int y = c[2] + b[i];
                if (x < 0 || y < 0 || x >= m || y >= n || t[x][y][i] || grid[x][y] == '#') continue;
                if (!push(c[3], c[4], c[1] - a[i], c[2] - b[i], c[1], c[2], m, n, grid)) continue;
                if (x == tx && y == ty) return 1 + c[0];
                q.offer(new int[]{1 + c[0], x, y, c[1], c[2]});
                t[x][y][i] = true;
            }
        }

        return -1;
    }

}
