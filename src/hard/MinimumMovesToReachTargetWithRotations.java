package hard;

import java.util.LinkedList;
import java.util.Queue;

/*
    In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and (0, 1).
    The grid has empty cells represented by zeros and blocked cells represented by ones.
    The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1).

    Return the minimum number of moves to reach the target.
    If there is no way to reach the target, return -1.
 */
public class MinimumMovesToReachTargetWithRotations {

    public static void main(String... args) {

        int[][] grid = {{0,0,0,0,0,1}, {1,1,0,0,1,0}, {0,0,0,0,1,1}, {0,0,1,0,1,0}, {0,1,1,0,0,0}, {0,1,1,0,0,0}};
        MinimumMovesToReachTargetWithRotations minimumMovesToReachTargetWithRotations = new MinimumMovesToReachTargetWithRotations();
        System.out.println(minimumMovesToReachTargetWithRotations.minimumMoves(grid));
    }

    public int minimumMoves(int[][] grid) {

        int n = grid.length;
        boolean[][][] t = new boolean[n][n][2];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0, 0});
        t[0][0][0] = true;

        while (!q.isEmpty()) {

            int[] x = q.poll();

            if (x[3] == 0) {

                if (x[2] < n-2 && grid[x[1]][x[2]+2] == 0) {

                    if (x[1] == n-1 && x[2] == n-3) return 1 + x[0];

                    if (!t[x[1]][x[2]+1][0]) {
                        t[x[1]][x[2]+1][0] = true;
                        q.offer(new int[]{1+x[0], x[1], 1+x[2], 0});
                    }
                }

                if (x[1] < n-1 && grid[x[1]+1][x[2]] == 0 && grid[x[1]+1][x[2]+1] == 0) {

                    if (x[1] == n-2 && x[2] == n-2) return 1 + x[0];

                    if (!t[x[1]+1][x[2]][0]) {
                        t[x[1]+1][x[2]][0] = true;
                        q.offer(new int[]{1+x[0], 1+x[1], x[2], 0});
                    }

                    if (!t[x[1]][x[2]][1]) {
                        t[x[1]][x[2]][1] = true;
                        q.offer(new int[]{1+x[0], x[1], x[2], 1});
                    }
                }
            } else {

                if (x[1] < n-2 && grid[x[1]+2][x[2]] == 0) {

                    if (!t[x[1]+1][x[2]][1]) {
                        t[x[1]+1][x[2]][1] = true;
                        q.offer(new int[]{1+x[0], 1+x[1], x[2], 1});
                    }
                }

                if (x[2] < n-1 && grid[x[1]][x[2]+1] == 0 && grid[x[1]+1][x[2]+1] == 0) {

                    if (!t[x[1]][x[2]][0]) {
                        t[x[1]][x[2]][0] = true;
                        q.offer(new int[]{1+x[0], x[1], x[2], 0});
                    }

                    if (!t[x[1]][x[2]+1][1]) {
                        t[x[1]][x[2]+1][1] = true;
                        q.offer(new int[]{1+x[0], x[1], 1+x[2], 1});
                    }
                }
            }
        }

        return -1;
    }

}
