package hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
    On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

    A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

    The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

    Given a puzzle board, return the least number of moves required so that the state of the board is solved.

    If it is impossible for the state of the board to be solved, return -1.
 */
public class SlidingPuzzle {

    public static void main(String... args) {

        int[][] board = {{4, 1, 2}, {5, 0, 3}};
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        System.out.println(slidingPuzzle.slidingPuzzle(board));
    }

    public int slidingPuzzle(int[][] board) {

        boolean[] t = new boolean[543211];
        t[123450] = true;
        int s = 100000 * board[0][0] + 10000 * board[0][1] + 1000 * board[0][2]
                    + 100 * board[1][0] + 10 * board[1][1] + board[1][2];
        if (t[s]) return 0;
        Queue<int[]> q = new ArrayDeque<>();
        int[][] a = {{2, 1, 3}, {3, 0, 2, 4}, {2, 1, 5}, {2, 0, 4}, {3, 1, 3, 5}, {2, 2, 4}};
        q.offer(new int[]{1, 2, 3, 4, 5, 0, 5, 0});

        while (!q.isEmpty()) {

            int[] x = q.poll();

            for (int i = 1; i <= a[x[6]][0]; i++) {

                int[] y = Arrays.copyOf(x, 6);
                y[a[x[6]][i]] = 0;
                y[x[6]] = x[a[x[6]][i]];
                int z = 100000 * y[0] + 10000 * y[1] + 1000 * y[2] + 100 * y[3] + 10 * y[4] + y[5];
                if (s == z) return 1 + x[7];
                if (t[z]) continue;
                t[z] = true;
                q.offer(new int[]{y[0], y[1], y[2], y[3], y[4], y[5], a[x[6]][i] , 1 + x[7]});
            }
        }

        return -1;
    }

}
