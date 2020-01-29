package hard;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*
    Strings A and B are K-similar (for some non-negative integer K) if we can swap the
    positions of two letters in A exactly K times so that the resulting string equals B.

    Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 */
public class KSimilarStrings {

    public static void main(String... args) {

        KSimilarStrings kSimilarStrings = new KSimilarStrings();
        System.out.println(kSimilarStrings.kSimilarity("abccaacceecdeea", "bcaacceeccdeaae"));
    }

    public int kSimilarity(String A, String B) {

        int n = A.length(), count = 0;
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        String anagrams = "", target = "";

        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
                if (a[i] != b[i] && a[i] == b[j] && a[j] == b[i]) {

                    char ch = a[i];
                    a[i] = a[j];
                    a[j] = ch;
                    count++;
                }

        for (int i = 0; i < n; i++)
            if (a[i] != b[i]) {
                anagrams += a[i];
                target += b[i];
            }

        if ((n = anagrams.length()) == 0) return count;
        Queue<Object[]> q = new ArrayDeque<>();
        Set<String> t = new HashSet<>();
        q.offer(new Object[]{0, anagrams});
        b = target.toCharArray();
        t.add(anagrams);

        while (!q.isEmpty()) {

            boolean flag = true;
            Object[] o = q.poll();
            int depth = (int) o[0];
            char[] c = ((String) o[1]).toCharArray();

            for (int i = 0; i < n; i++)
                if (c[i] != b[i]) {

                    flag = false;

                    for (int j = 0; j < n; j++)
                        if (c[j] == b[i] && c[j] != b[j] ) {

                            char ch = c[i];c[i] = c[j];c[j] = ch;
                            String s = new String(c);
                            ch = c[i];c[i] = c[j];c[j] = ch;

                            if (!t.contains(s)) {
                                t.add(s);
                                q.offer(new Object[]{depth+1, s});
                            }
                        }
                }

            if (flag) return depth + count;
        }

        return 0;
    }

}
