package hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
    We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.
    We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.
    We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

    For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.
    This means that there is exactly one key for each lock, and one lock for each key; and also that the
    letters used to represent the keys and locks were chosen in the same order as the English alphabet.

    Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.
 */
public class ShortestPathToGetAllKeys {

    public static void main(String... args) {

        String[] grid = {"@.a.#", "###.#", "b.A.B"};
        ShortestPathToGetAllKeys shortestPathToGetAllKeys = new ShortestPathToGetAllKeys();
        System.out.println(shortestPathToGetAllKeys.shortestPathAllKeys(grid));
    }

    public int shortestPathAllKeys(String[] grid) {

        int m = grid.length, n = grid[0].length(), keys = 0;
        Queue<Object[]> q = new LinkedList<>();
        char[][] f = new char[m][n];

        for (int i = 0; i < m; i++) {

            char[] c = grid[i].toCharArray();

            for (int j = 0; j < n; j++) {

                f[i][j] = c[j];

                if (c[j] == '@') q.offer(new Object[]{i, j, 0, 0, new HashSet<>()});
                else if (c[j] >= 'a' && c[j] <= 'g') keys++;
            }
        }

        if (keys == 0) return 0;
        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, -1, 1};
        boolean[][][] t = new boolean[m][n][128];

        while (!q.isEmpty()) {

            Object[] o = q.poll();
            int x = (int) o[0];
            int y = (int) o[1];
            int z = (int) o[2];
            int s = (int) o[3];
            Set<Character> c = (Set<Character>) o[4];

            for (int i = 0; i < 4; i++) {

                int u = x + a[i];
                int v = y + b[i];
                if (u < 0 || u >= m || v < 0 || v >= n || f[u][v] == '#') continue;
                if (f[u][v] >= 'A' && f[u][v] <= 'G' && !c.contains(f[u][v]+32)) continue;
                boolean flag = f[u][v] >= 'a' && f[u][v] <= 'g' && !c.contains(f[u][v]);
                int p = !flag ? s : s + (1 << (f[u][v] - 97));
                if (t[u][v][p]) continue;
                Set<Character> d = new HashSet<>();
                d.addAll(c);

                if (flag) {
                    d.add(f[u][v]);
                    if (d.size() == keys) return 1+z;
                }

                t[u][v][p] = true;
                q.offer(new Object[]{u, v, 1+z, p, d});
            }
        }

        return -1;
    }

}
