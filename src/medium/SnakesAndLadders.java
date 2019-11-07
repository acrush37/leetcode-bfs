package medium;

import java.util.LinkedList;
import java.util.Queue;

/*
    On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting
    from the bottom left of the board, and alternating direction each row.

    Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
 */
public class SnakesAndLadders {

    public static void main(String... args) {

        int[][] board = {{-1,-1,27,13,-1,25,-1},
                         {-1,-1,-1,-1,-1,-1,-1},
                         {44,-1,8,-1,-1,2,-1},
                         {-1,30,-1,-1,-1,-1,-1},
                         {3,-1,20,-1,46,6,-1},
                         {-1,-1,-1,-1,-1,-1,29},
                         {-1,29,21,33,-1,-1,-1}};
        SnakesAndLadders snakesAndLadders = new SnakesAndLadders();
        System.out.println(snakesAndLadders.snakesAndLadders(board));
    }

    public int snakesAndLadders(int[][] board) {

        int k = 0;
        int n = board.length;
        int m = n * n;
        int[] f = new int[m+1];
        boolean[] t = new boolean[m+1];
        Queue<int[]> q = new LinkedList<>();

        for (int i = n-1; i >= 0; i--)
            if (((n-i) & 1) == 1) for (int j = 0; j < n; j++) f[++k] = board[i][j];
            else for (int j = n-1; j >= 0; j--) f[++k] = board[i][j];

        t[1] = true;
        q.offer(new int[]{1, 0, 0});

        while (!q.isEmpty()) {

            int[] x = q.poll();

            for (int i = 1; i <= 7; i++) {

                if (i == 7 && (f[x[0]] == -1 || x[2] == 7)) continue;
                int y = i == 7 ? f[x[0]] : x[0] + i;
                if (i != 7 && f[y] != -1) y = f[y];
                if (y == m) return 1 + x[1];
                if (y > m || t[y]) continue;
                t[y] = true;
                q.offer(new int[]{y, 1 + x[1], i != 7 && f[y] != -1 ? 7 : i});
            }
        }

        return -1;
    }

}
