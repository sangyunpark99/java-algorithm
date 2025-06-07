package 프로그래머스.혼자놀기의달인;

import java.util.*;

public class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visited = new boolean[n];
        List<Integer> cycleSizes = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                int size = 0;
                int cur = i;
                while(!visited[cur]) {
                    visited[cur] = true;
                    cur = cards[cur] - 1;
                    size++;
                }
                cycleSizes.add(size);
            }
        }

        if(cycleSizes.size() < 2) {
            return 0;
        }

        Collections.sort(cycleSizes, Collections.reverseOrder());

        return cycleSizes.get(0) * cycleSizes.get(1);
    }
}
