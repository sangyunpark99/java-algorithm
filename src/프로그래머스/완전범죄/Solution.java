package 프로그래머스.완전범죄;

import java.util.*;

class Solution {
    // 완전 탐색은 시간 초과
    // DP? A도둑이 훔치는 물건을 최소화
    // 1. A가 훔친다  2. A가 훔치지 않는다.

    private int[][] dp; // A의 최솟값 갱신

    public int solution(int[][] infos, int n, int m) {

        dp = new int[infos.length + 1][m + 1];

        for(int[] value: dp) { // A의 최댓값으로 초기화
            Arrays.fill(value, n);
        }

        dp[0][0] = 0;

        for(int i = 1; i <= infos.length; i++) { // O(40)
            int[] info = infos[i - 1];
            int a = info[0];
            int b = info[1];

            for(int j = 0; j <= m; j++) { // b가 남긴 흔적 갯수
                // 1. A를 뽑은 경우
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                // 2. B를 뽑은 경우
                if(j + b < m) {
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }

        int answer = n;
        for(int i = 0; i < m; i++) {
            answer = Math.min(answer, dp[infos.length][i]);
        }
        return answer >= n ? -1 : answer;
    }
}
