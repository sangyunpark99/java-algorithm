package 프로그래머스.충돌알고리즘찾기;

import java.util.*;

public class Solution {
    public int solution(int[][] points, int[][] routes) {
        Map<String, Integer> map = new HashMap<>();

        for (int[] route : routes) {
            int time = 0;
            int[] start = points[route[0] - 1];
            int r = start[0];
            int c = start[1];
            map.merge(time + "," + r + "," + c, 1, Integer::sum);

            for (int i = 1; i < route.length; i++) {
                int[] dest = points[route[i] - 1];
                int dr = dest[0];
                int dc = dest[1];

                while (r != dr) {
                    time++;
                    r += (dr > r) ? 1 : -1;
                    map.merge(time + "," + r + "," + c, 1, Integer::sum);
                }

                while (c != dc) {
                    time++;
                    c += (dc > c) ? 1 : -1;
                    map.merge(time + "," + r + "," + c, 1, Integer::sum);
                }
            }
        }

        int answer = 0;
        for (int count : map.values()) {
            if (count >= 2) answer++;
        }

        return answer;
    }
}
