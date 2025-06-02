package 프로그래머스.요격시스템;

import java.util.Arrays;

class Solution {

    private int lastTarget = 0;

    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        for(int[] target: targets) {

            int start = target[0];
            int end = target[1];

            if(lastTarget <= start) {
                answer++;
                lastTarget = end;
            }
        }

        return answer;
    }
}