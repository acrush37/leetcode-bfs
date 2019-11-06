package easy;

/*
    In a given grid, each cell can have one of three values:

    the value 0 representing an empty cell;
    the value 1 representing a fresh orange;
    the value 2 representing a rotten orange.

    Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

    Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 */
public class RottingOranges {

    public static void main(String... args) {

        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        RottingOranges rottingOranges = new RottingOranges();
        System.out.println(rottingOranges.orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, -1, 1};
        int[][][] t = new int[m][n][101];
        int s = 0;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1) s++;
                else if (grid[i][j] == 2) t[i][j][1] = 1;

        if (s == 0) return 0;

        for (int k = 1; k < 100; k++)
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 2 && t[i][j][k] == 1) {

                        t[i][j][k]++;

                        for (int p = 0; p < 4; p++) {

                            int x = i + a[p];
                            int y = j + b[p];

                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {

                                if (t[x][y][k] == 0) t[x][y][k+1] = 1;
                                grid[x][y] = 2;
                                if (--s == 0) return k;
                            }
                        }
                    }

        return -1;
    }

}
