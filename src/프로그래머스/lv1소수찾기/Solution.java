package 프로그래머스.lv1소수찾기;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (isPrime[i] == true) {
                answer++;
            }
        }

        return answer;
    }
}
