package hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/*
    Given a m x n grid.
    Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell.
    Notice that there could be some invalid signs on the cells of the grid which points outside the grid.

    You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from
    the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid.

    The valid path doesn't have to be the shortest.
    You can modify the sign on a cell with cost = 1.
    You can modify the sign on a cell one time only.

    Return the minimum cost to make the grid have at least one valid path.
 */
public class MinimumCostMakeValidPathInGrid {

    public static void main(String... args) {

        int[][] grid = {{1, 1, 3}, {3, 2, 2}, {1, 1, 4}};
        MinimumCostMakeValidPathInGrid minimumCostMakeValidPathInGrid = new MinimumCostMakeValidPathInGrid();
        System.out.println(minimumCostMakeValidPathInGrid.minCost(grid));
    }

    public int minCost(int[][] grid) {

        int[] a = {0, 0, 1, -1}, b = {1, -1, 0, 0};
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] t = new boolean[m][n];
        q.offer(new int[]{0, 0, 0});

        while (!q.isEmpty()) {

            int[] o = q.poll();
            if (t[o[0]][o[1]]) continue;
            if (o[0] == m-1 && o[1] == n-1) return o[2];
            int j = grid[o[0]][o[1]] - 1;
            t[o[0]][o[1]] = true;

            for (int i = 0; i < 4; i++) {

                int x = o[0] + a[i], y = o[1] + b[i];
                if (x < 0 || x == m || y < 0 || y == n) continue;

                if (i == j) q.addFirst(new int[]{x, y, o[2]});
                else q.offer(new int[]{x, y, 1 + o[2]});
            }
        }

        return -1;
    }

}
