package medium;

/*
    There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming
    a network where connections[i] = [a, b] represents a connection between computers a and b.

    Any computer can reach any other computer directly or indirectly through the network.

    Given an initial computer network connections. You can extract certain cables between two directly
    connected computers, and place them between any pair of disconnected computers to make them directly connected.

    Return the minimum number of times you need to do this in order to make all the computers connected.
    If it's not possible, return -1.
 */
public class NumberOfOperationsMakeNetworkConnected {

    public static void main(String... args) {

        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        NumberOfOperationsMakeNetworkConnected numberOfOperationsMakeNetworkConnected = new NumberOfOperationsMakeNetworkConnected();
        System.out.println(numberOfOperationsMakeNetworkConnected.makeConnected(6, connections));
    }

    public int makeConnected(int n, int[][] connections) {

        if (connections.length < n-1) return -1;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) f[i] = i;

        for (int[] c : connections) {

            int x = c[0], y = c[1];
            while (f[x] != x) x = f[x];
            while (f[y] != y) y = f[y];

            if (x != y) {
                n--;
                f[x] = y;
            }
        }

        return n-1;
    }

}
