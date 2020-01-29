package hard;

import java.util.*;

/*
    Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y),
    blue(B), green(G), and white(W). You also have several balls in your hand.

    Each time, you may choose a ball in your hand, and insert it into the row (including the
    leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the
    same color touching, remove these balls. Keep doing this until no more balls can be removed.

    Find the minimal balls you have to insert to remove all the balls on the table.
    If you cannot remove all the balls, output -1.
 */
public class ZumaGame {

    public static void main(String... args) {

        ZumaGame zumaGame = new ZumaGame();
        System.out.println(zumaGame.findMinStep("RWYWRRWRR", "YRY"));
    }

    private void zuma(List<Integer> d, int k) {

        int n = d.size() - 1;

        for (int i = 0; i < n-1; i++)
            if (d.get(i) == k) {

                int j = i+1;
                while (j <= n && d.get(j) == k) j++;

                if (j-i >= 3) {
                    while (--j >= i) d.remove(i);
                    return;
                }
            }
    }

    private boolean find(List<Integer> d) {

        int n = d.size() - 1;

        for (int i = 0; i < n-1; i++) {

            int j = i+1, k = d.get(i);
            while (j <= n && d.get(j) == k) j++;

            if ((k = j - i) >= 3) {
                while (--k >= 0) d.remove(i);
                return find(d);
            }
        }

        return d.isEmpty();
    }

    public int findMinStep(String board, String hand) {

        int n = board.length();
        if (n == 0) return 0;
        int[] a = new int[5];
        List<Integer> f = new ArrayList<>();

        for (int i = 0; i < hand.length(); i++) {

            char c = hand.charAt(i);

            if (c == 'R') a[0]++;
            else if (c == 'Y') a[1]++;
            else if (c == 'B') a[2]++;
            else if (c == 'G') a[3]++;
            else a[4]++;
        }

        for (int i = 0; i < n; i++) {

            char c = board.charAt(i);

            if (c == 'R') f.add(0);
            else if (c == 'Y') f.add(1);
            else if (c == 'B') f.add(2);
            else if (c == 'G') f.add(3);
            else f.add(4);
        }

        for (int i = 0; i < 5; i++)
            if (!f.contains(Integer.valueOf(i)))
                a[i] = 0;

        Queue<Object[]> q = new ArrayDeque<>();
        Set<String> t = new HashSet<>();
        q.offer(new Object[]{1, a, f});
        t.add(f.toString());

        while (!q.isEmpty()) {

            Object[] o = q.poll();
            int depth = (int) o[0];
            int[] b = (int[]) o[1];
            List<Integer> c = (List<Integer>) o[2];
            int m = c.size();

            for (int i = 0; i < 5; i++)
                if (b[i] != 0) {

                    int[] e = Arrays.copyOf(b, 5);
                    e[i]--;

                    if (m == 1) {

                        List<Integer> d = Arrays.asList(c.get(0), i);
                        String s = d.toString();

                        if (!t.contains(s)) {
                            t.add(s);
                            q.offer(new Object[]{depth + 1, e, d});
                        }
                    } else for (int j = 0; j < m; j++)
                        if (c.get(j) == i) {

                            List<Integer> d = new ArrayList<>();
                            d.addAll(c);
                            d.add(j+1, i);
                            zuma(d, i);
                            List<Integer> g = new ArrayList<>();
                            g.addAll(d);
                            if (find(g)) return depth;
                            String s = d.toString();
                            if (j != m-1 && c.get(j+1) == i) j++;

                            if (!t.contains(s)) {
                                t.add(s);
                                q.offer(new Object[]{depth + 1, e, d});
                            }

                        }
                }
        }

        return -1;
    }

}
