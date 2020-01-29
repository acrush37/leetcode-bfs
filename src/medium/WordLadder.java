package medium;

import java.util.*;

/*
    Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 */
public class WordLadder {

    public static void main(String... args) {

        List<String> wordList = new ArrayList<>();
        wordList.add("talk");
        wordList.add("tons");
        wordList.add("fall");
        wordList.add("tail");
        wordList.add("gale");
        wordList.add("hall");
        wordList.add("negs");
        WordLadder wordLadder = new WordLadder();
        System.out.println(wordLadder.ladderLength("talk", "tail", wordList));
    }

    private boolean transform(String x, String y) {

        int s = 0;
        int n = x.length();
        char[] a = x.toCharArray();
        char[] b = y.toCharArray();
        for (int i = 0; i < n; i++) if (a[i] != b[i]) s++;
        return s == 1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) return 0;
        if (wordList.contains(beginWord)) wordList.remove(beginWord);
        wordList.add(0, beginWord);
        int n = wordList.size();
        boolean[][] f = new boolean[n][n];
        boolean[] t = new boolean[n];

        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
                f[i][j] = f[j][i] = transform(wordList.get(i), wordList.get(j));

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1});
        t[0] = true;

        while (!q.isEmpty()) {

            int[] x = q.poll();

            for (int i = 1; i < n; i++)
                if (!t[i] && f[i][x[0]]) {

                    if (endWord.equals(wordList.get(i))) return 1 + x[1];
                    q.offer(new int[]{i, 1 + x[1]});
                    t[i] = true;
                }
        }

        return 0;
    }

}
