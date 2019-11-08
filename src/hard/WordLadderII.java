package hard;

import java.util.*;
import java.util.stream.Collectors;

/*
    Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord,
    such that:

    Only one letter can be changed at a time
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 */
public class WordLadderII {

    private Set<String> set = new HashSet<>();
    private List<List<String>> result = new ArrayList<>();

    public static void main(String... args) {

        String[] w = {"ted","tex","red","tax","tad","den","rex","pee"};
        List<String> wordList= new ArrayList<>();
        for (String s : w) wordList.add(s);
        WordLadderII wordLadderII = new WordLadderII();
        System.out.println(wordLadderII.findLadders("red", "tax", wordList));
    }

    private boolean transform(String x, String y, int m) {

        int s = 0;
        char[] a = x.toCharArray();
        char[] b = y.toCharArray();

        for (int i = 0; i < m; i++)
            if (a[i] != b[i] && ++s > 1)
                return false;

        return true;
    }

    private void dfs(int x, int[][] f, String[] a, Set<Integer>[] s, boolean[] t, List<String> list) {

        if (x == 0) {

            List<String> arr = list.stream().collect(Collectors.toList());
            arr.add(a[0]);
            Collections.reverse(arr);
            String ss = arr.toString();

            if (!set.contains(ss)) {

                set.add(ss);
                result.add(arr);
            }

            return;
        }

        for (int i = x-1; i >= 0; i--) {

            int j = f[x][1] - f[i][1];
            if (j > 1) break;

            if (j == 1 && !t[i] && s[f[i][0]].contains(f[x][0])) {

                t[i] = true;
                list.add(a[f[x][0]]);
                dfs(i, f, a, s, t, list);
                list.remove(a[f[x][0]]);
                t[i] = false;
            }
        }

    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) return Collections.EMPTY_LIST;
        if (wordList.contains(beginWord)) wordList.remove(beginWord);
        wordList.add(0, beginWord);
        if (wordList.contains(endWord)) wordList.remove(endWord);
        wordList.add(endWord);
        int m = beginWord.length();
        int n = wordList.size();
        String[] a = new String[n];
        wordList.toArray(a);
        Set<Integer>[] s = new Set[n];
        for (int i = 0; i < n; i++) s[i] = new HashSet<>();

        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
                if (transform(a[i], a[j], m)) {

                    s[i].add(j);
                    if (i != 0 && j != n-1) s[j].add(i);
                }

        boolean[] t = new boolean[n];
        int[][] f = new int[100000][2];
        int min = Integer.MAX_VALUE;
        int x = -1;
        int y = 0;

        while (++x <= y) {

            if (f[x][1] == min) break;

            for (int z : s[f[x][0]])
                if (z == n-1) {

                    f[++y][0] = z;
                    f[y][1] = 1 + f[x][1];
                    min = f[y][1];
                } else if (!t[z]) {

                    t[z] = true;
                    f[++y][0] = z;
                    f[y][1] = 1 + f[x][1];
                }
        }

        for (int i = y; i > 0; i--)
            if (f[i][0] == n-1)
                dfs(i, f, a, s, new boolean[n], new ArrayList<>());

        return result;
    }

}
