package medium;

import java.util.*;

/*
    A company has n employees with a unique ID for each employee from 0 to n - 1.
    The head of the company has is the one with headID.

    Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the
    i-th employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.

    The head of the company wants to inform all the employees of the company of an urgent piece of news. He will inform
    his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.

    The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
    (i.e After informTime[i] minutes, all his direct subordinates can start spreading the news).

    Return the number of minutes needed to inform all the employees about the urgent news.
 */
public class TimeNeededInformAllEmployees {

    public static void main(String... args) {

        int[] manager = {-1};
        int[] informTime = {0};
        TimeNeededInformAllEmployees timeNeededInformAllEmployees = new TimeNeededInformAllEmployees();
        System.out.println(timeNeededInformAllEmployees.numOfMinutes(1, 0, manager, informTime));
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        int result = 0;
        List<Integer>[] s = new List[n];
        for (int i = 0; i < n; i++) s[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) if (manager[i] != -1) s[manager[i]].add(i);
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{headID, 0});

        while (!q.isEmpty()) {

            int[] o = q.poll();
            result = Math.max(o[1], result);
            for (int x : s[o[0]]) q.offer(new int[]{x, o[1] + informTime[o[0]]});
        }

        return result;
    }

}
