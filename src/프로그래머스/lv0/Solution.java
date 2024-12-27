package 프로그래머스.lv0;

public class Solution {
    public int solution(int[] numbers) {
        int answer = 0;

        // O(n^2)
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                answer = Math.max(answer, numbers[i] * numbers[j]);
            }
        }

        return answer;
    }
}
