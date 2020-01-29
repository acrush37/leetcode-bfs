package medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
    You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
    The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

    The lock initially starts at '0000', a string representing the state of the 4 wheels.

    You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

    Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 */
public class OpenTheLock {

    public static void main(String... args) {

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        OpenTheLock openTheLock = new OpenTheLock();
        System.out.println(openTheLock.openLock(deadends, "0202"));
    }

    private int bfs(boolean[] f, boolean[] t, int target) {

        Queue<int[]> q = new ArrayDeque<>();
        int[] y = new int[4];
        q.offer(new int[]{0, 0});
        t[0] = true;

        while (!q.isEmpty()) {

            int[] x = q.poll();
            y[0] = x[0] / 1000;
            x[0] %= 1000;
            y[1] = x[0] / 100;
            x[0] %= 100;
            y[2] = x[0] / 10;
            y[3] = x[0] % 10;

            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 2; j++) {

                    int[] z = Arrays.copyOf(y, 4);
                    z[i] = j == 0 ? (1 + z[i]) % 10 : (9 + z[i]) % 10;
                    int u = 1000 * z[0] + 100 * z[1] + 10 * z[2] + z[3];
                    if (u < 0 || u > 9999 || t[u] || f[u]) continue;
                    if (u == target) return 1 + x[1];
                    t[u] = true;
                    q.offer(new int[]{u, 1 + x[1]});
                }
            }
        }

        return -1;
    }

    public int openLock(String[] deadends, String target) {

        boolean[] f = new boolean[10000];
        boolean[] t = new boolean[10000];
        for (String d : deadends) f[Integer.parseInt(d)] = true;
        if (f[0]) return -1;
        return bfs(f, t, Integer.parseInt(target));
    }

}
