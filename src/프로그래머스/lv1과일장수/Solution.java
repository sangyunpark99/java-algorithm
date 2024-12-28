package 프로그래머스.lv1과일장수;

import java.util.Arrays;

public class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;

        Arrays.sort(score);

        int check = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = score.length - 1; i >= 0; i--) {
            if (check != m) { // 같지 않은 경우
                minValue = Math.min(score[i], minValue);
                check++;
            }

            if (check == m) { // 같은 경우
                answer += minValue * m;
                minValue = Integer.MAX_VALUE;
                check = 0;
            }
        }

        return answer;
    }
}
