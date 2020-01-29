package hard;

import java.util.*;

import static java.util.Map.Entry;

/*
    You are asked to cut off trees in a forest for a golf event.
    The forest is represented as a non-negative 2D map, in this map:

    0 represents the obstacle can't be reached.
    1 represents the ground can be walked through.
    The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.

    You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first.
    And after cutting, the original place has the tree will become a grass (value 1).

    You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees.
    If you can't cut off all the trees, output -1 in that situation.

    You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 */
public class CutOffTreesForGolfEvent {

    public static void main(String... args) {

        List<Integer> first = Arrays.asList(1, 2, 3);
        List<Integer> second = Arrays.asList(0, 0, 4);
        List<Integer> third = Arrays.asList(7, 6, 5);
        List<List<Integer>> forest = Arrays.asList(first, second, third);
        CutOffTreesForGolfEvent cutOffTreesForGolfEvent = new CutOffTreesForGolfEvent();
        System.out.println(cutOffTreesForGolfEvent.cutOffTree(forest));
    }

    private int bfs(int x, int y, int u, int v, int m, int n, int[][] f) {

        if (x == u && y == v) return 0;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] t = new boolean[m][n];
        q.offer(new int[]{0, x, y});
        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, 1, -1};
        t[x][y] = true;

        while (!q.isEmpty()) {

            int[] c = q.poll();

            for (int k = 0; k <= 3; k++) {

                int i = c[1] + a[k];
                int j = c[2] + b[k];
                if (i < 0 || j < 0 || i >= m || j >= n || t[i][j] || f[i][j] == 0) continue;
                if (i == u && j == v) return 1 + c[0];
                q.offer(new int[]{1 + c[0], i, j});
                t[i][j] = true;
            }
        }

        return -1;
    }

    public int cutOffTree(List<List<Integer>> forest) {

        int result = 0;
        int m = forest.size();
        int n = forest.get(0).size();
        int[][] f = new int[m][n];
        Map<Integer, int[]> t = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            List<Integer> list = forest.get(i);
            for (int j = 0; j < n; j++) t.put(f[i][j] = list.get(j), new int[]{i, j});
        }

        int x = 0, y = 0;
        Set<Entry<Integer, int[]>> entries = t.entrySet();

        for (Entry<Integer, int[]> entry : entries)
            if (entry.getKey() > 1) {

                int[] value = entry.getValue();
                int z = bfs(x, y, value[0], value[1], m, n, f);
                if (z == -1) return -1;
                x = value[0];
                y = value[1];
                result += z;
            }

        return result;
    }
}
