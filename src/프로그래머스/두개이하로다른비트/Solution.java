package 프로그래머스.두개이하로다른비트;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        int idx = 0;

        for (long number : numbers) {
            for (long i = 1; ; i <<= 1) {
                if ((number & i) == 0) { // i번째가 0
                    number |= i; // 0을 1로 변환하고
                    number ^= (i >> 1); // 0위치보다 한칸 이전을 1로 -> 나머지는 무조건 1
                    break;
                }
            }
            answer[idx++] = number;
        }

        return answer;
    }
}
