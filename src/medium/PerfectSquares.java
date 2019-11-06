package medium;

/*
    Given a positive integer n, find the least number of perfect square numbers
    (for example, 1, 4, 9, 16, ...) which sum to n.
 */
public class PerfectSquares {

    public static void main(String... args) {

        PerfectSquares perfectSquares = new PerfectSquares();
        System.out.println(perfectSquares.numSquares(13));
    }

    public int numSquares(int n) {

        int[] f = new int[n+1];

        for (int i = 1; i <= n; i++) {

            f[i] = i;
            int j = (int) Math.sqrt(i);

            if (i == j*j) f[i] = 1;
            else for (int k = 0; k < i; k++) f[i] = Math.min(f[i], f[k] + f[i-k]);
        }

        return f[n];
    }

}
