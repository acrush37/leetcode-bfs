package hard;

import java.util.*;

/*
    Given an array of integers arr, you are initially positioned at the first index of the array.
    In one step you can jump from index i to index:

    i + 1 where: i + 1 < arr.length.
    i - 1 where: i - 1 >= 0.
    j where: arr[i] == arr[j] and i != j.

    Return the minimum number of steps to reach the last index of the array.
 */
public class JumpGameIV {

    public static void main(String... args) {

        int[] arr = {6, 1, 9};
        JumpGameIV jumpGameIV = new JumpGameIV();
        System.out.println(jumpGameIV.minJumps(arr));
    }

    public int minJumps(int[] arr) {

        int n = arr.length;
        if (n <= 2) return n-1;
        boolean[] t = new boolean[n];
        Queue<int[]> q = new ArrayDeque<>();
        Map<Integer, List<Integer>> m = new HashMap<>();
        q.offer(new int[]{0, 1});
        t[0] = true;

        for (int i = 0; i < n; i++) {
            m.putIfAbsent(arr[i], new ArrayList<>());
            m.get(arr[i]).add(i);
        }

        while (!q.isEmpty()) {

            int[] x = q.poll();
            int y = x[0] + 1, z = x[0] - 1;

            if (!t[y]) {
                if (y == n-1) return x[1];
                t[y] = true;
                q.offer(new int[]{y, 1 + x[1]});
            }

            if (z >= 0 && !t[z]) {
                t[z] = true;
                q.offer(new int[]{z, 1 + x[1]});
            }

            List<Integer> f = m.get(arr[x[0]]);
            if (!f.isEmpty() && f.get(f.size()-1) == n-1) return x[1];

            for (int k : f)
                if (!t[k]) {
                    t[k] = true;
                    q.offer(new int[]{k, 1 + x[1]});
                }

            f.clear();
        }

        return 0;
    }

}
