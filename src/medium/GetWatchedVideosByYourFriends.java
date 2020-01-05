package medium;

import java.util.*;
import java.util.stream.Collectors;

/*
    There are n people, each person has a unique id between 0 and n-1.
    Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain
    the list of watched videos and the list of friends respectively for the person with id = i.

    Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched
    videos by the friends of your friends and so on. In general, the level k of videos are all
    watched videos by people with the shortest path equal to k with you. Given your id and the
    level of videos, return the list of videos ordered by their frequencies (increasing).

    For videos with the same frequency order them alphabetically from least to greatest.
 */
public class GetWatchedVideosByYourFriends {

    public static void main(String... args) {

        int[][] friends = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        List<List<String>> videos = new ArrayList<>();
        videos.add(Arrays.asList("A", "B"));
        videos.add(Arrays.asList("C"));
        videos.add(Arrays.asList("B", "C"));
        videos.add(Arrays.asList("D"));
        GetWatchedVideosByYourFriends getWatchedVideosByYourFriends = new GetWatchedVideosByYourFriends();
        System.out.println(getWatchedVideosByYourFriends.watchedVideosByFriends(videos, friends, 0, 1));
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        int n = friends.length;
        boolean[] t = new boolean[n];
        Set<Integer>[] f = new Set[n];
        Queue<int[]> q = new LinkedList<>();
        Map<String, Integer> m = new HashMap<>();
        q.offer(new int[]{id, 0});
        t[id] = true;

        for (int i = 0; i < n; i++) {
            f[i] = new HashSet<>();
            for (int j : friends[i]) f[i].add(j);
        }

        while (!q.isEmpty()) {

            int[] x = q.poll();

            for (int y : f[x[0]])
                if (!t[y]) {

                    t[y] = true;
                    
                    if (x[1] + 1 != level) q.offer(new int[]{y, 1 + x[1]});
                    else {
                        List<String> list = watchedVideos.get(y);
                        for (String z : list) m.put(z, m.getOrDefault(z, 0) + 1);
                    }
                }
        }

        return m.entrySet().stream().sorted((x, y) -> {

            int a = x.getValue();
            int b = y.getValue();
            return a == b ? x.getKey().compareTo(y.getKey()) : a - b;
        }).map(i -> i.getKey()).collect(Collectors.toList());
    }

}
