package 프로그래머스.lv0제곱수판별하기;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        // 제곱수 n x n
        for (int i = 1; i <= n; i++) {
            if (i * i == n) {
                return 1;
            }
        }
        return 2;
    }
}
