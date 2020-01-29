package hard;

import java.util.*;

/*
    Given n boxes, each box is given in the format [status, candies, keys, containedBoxes] where:

    status[i]: an integer which is 1 if box[i] is open and 0 if box[i] is closed.
    candies[i]: an integer representing the number of candies in box[i].
    keys[i]: an array contains the indices of the boxes you can open with the key in box[i].
    containedBoxes[i]: an array contains the indices of the boxes found in box[i].

    You will start with some boxes given in initialBoxes array.

    You can take all the candies in any open box and you can use the keys
    in it to open new boxes and you also can use the boxes you find in it.

    Return the maximum number of candies you can get following the rules above.
 */
public class MaximumCandiesGetFromBoxes {

    public static void main(String... args) {

        int[] initialBoxes = {0};
        int[] status = {1, 0, 1, 0};
        int[] candies = {7, 5, 4, 100};
        int[][] keys = {{}, {}, {1}, {}};
        int[][] containedBoxes = {{1, 2}, {3}, {}, {}};
        MaximumCandiesGetFromBoxes maximumCandiesGetFromBoxes = new MaximumCandiesGetFromBoxes();
        System.out.println(maximumCandiesGetFromBoxes.maxCandies(status, candies, keys, containedBoxes, initialBoxes));
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        int result = 0, count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int x : initialBoxes) q.offer(x);

        while (count++ != q.size()) {

            Integer box = q.poll();

            if (status[box] == 0) q.offer(box);
            else if (status[box] == 1) {

                for (int x : containedBoxes[box]) q.offer(x);
                for (int x : keys[box]) status[x] = 1;
                result += candies[box];
                status[box] = 2;
                count = 0;
            }
        }

        return result;
    }

}
