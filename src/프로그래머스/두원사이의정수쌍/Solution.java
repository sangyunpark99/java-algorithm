package 프로그래머스.두원사이의정수쌍;

public class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int x = 1; x <= r2; x++) {
            long minY = 0;
            if (x < r1) {
                double minYDouble = Math.ceil(Math.sqrt((long)r1 * r1 - (long)x * x));
                minY = (long) minYDouble;
            }

            long maxY = (long) Math.floor(Math.sqrt((long)r2 * r2 - (long)x * x));

            if (maxY >= minY) {
                answer += (maxY - minY + 1);
            }
        }

        return answer * 4;
    }
}
