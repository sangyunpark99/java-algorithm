package 프로그래머스.lv0순서쌍의개수;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) { // 나눠떨어지는 경우
                answer++;
            }
        }

        return answer;
    }
}
