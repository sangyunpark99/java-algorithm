package 프로그래머스.쿠키구입;

class Solution {

    private int[] pSum;

    // 배열이 정렬되어 있지 않기 때문에, 중간 값을 기준으로..!

    public int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;

        pSum = new int[n];
        pSum[0] = cookie[0];
        for (int i = 1; i < n; i++) {
            pSum[i] = pSum[i - 1] + cookie[i];
        }

        for (int m = 0; m < n - 1; m++) { // 중간 값을 기준으로 영역 확장
            int left = m;
            int right = m + 1;

            while (left >= 0 && right < n) {
                int sum1 = getSum(left, m);
                int sum2 = getSum(m + 1, right);

                if (sum1 == sum2) {
                    answer = Math.max(answer, sum1);
                    left--;
                    right++;
                } else if (sum1 < sum2) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        return answer;
    }

    private int getSum(int from, int to) {
        if (from == 0) return pSum[to];
        return pSum[to] - pSum[from - 1];
    }
}
