package medium;

import java.util.ArrayDeque;
import java.util.Queue;

/*
    Given an array of non-negative integers arr, you are initially positioned at start index of the array.
    When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

    Notice that you can not jump outside of the array at any time.
 */
public class JumpGameIII {

    public static void main(String... args) {

        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        JumpGameIII jumpGameIII = new JumpGameIII();
        System.out.println(jumpGameIII.canReach(arr, 5));
    }

    public boolean canReach(int[] arr, int start) {

        if (arr[start] == 0) return true;
        int n = arr.length;
        boolean[] t = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        t[start] = true;

        while (!q.isEmpty()) {

            int x = q.poll();
            int y = x + arr[x];

            if (y >= 0 && y < n) {

                if (arr[y] == 0) return true;

                if (!t[y]) {
                    t[y] = true;
                    q.offer(y);
                }
            }

            y = x - arr[x];

            if (y >= 0 && y < n) {

                if (arr[y] == 0) return true;

                if (!t[y]) {
                    t[y] = true;
                    q.offer(y);
                }
            }
        }

        return false;
    }

}
