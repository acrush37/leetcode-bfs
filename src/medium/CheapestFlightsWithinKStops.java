package medium;

import java.util.*;

/*
    There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

    Now given all the cities and flights, together with starting city src and the destination dst,
    your task is to find the cheapest price from src to dst with up to k stops.
    If there is no such route, output -1.
 */
public class CheapestFlightsWithinKStops {

    public static void main(String... args) {

        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
        System.out.println(cheapestFlightsWithinKStops.findCheapestPrice(3, flights, 0, 2, 0));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        int[][] f = new int[n][n];
        int min = Integer.MAX_VALUE;
        Set<Integer>[] p = new Set[n];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) p[i] = new HashSet<>();
        q.offer(new int[]{src, 0, 0});

        for (int[] a : flights) {

            p[a[0]].add(a[1]);
            f[a[0]][a[1]] = a[2];
        }

        while (!q.isEmpty()) {

            int[] x = q.poll();
            if (x[1] > K) break;

            for (int y : p[x[0]]) {

                if (y == dst) {

                    min = Math.min(min, x[2] + f[x[0]][y]);
                    continue;
                }

                int s = x[2] + f[x[0]][y];
                if (s >= min) continue;
                q.offer(new int[]{y, 1 + x[1], s});
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
