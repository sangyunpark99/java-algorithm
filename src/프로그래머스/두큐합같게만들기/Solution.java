package 프로그래머스.두큐합같게만들기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int size = queue1.length + queue2.length + 1;

        long sum1 = 0L;
        long sum2 = 0L;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        // 합계를 그때그때 구해준다? x 누적시켜준다. 시간 복잡도를 줄여야한다.

        for (int value : queue1) {
            sum1 += value;
            q1.offer(value);
        }

        for (int value : queue2) {
            sum2 += value;
            q2.offer(value);
        }

        if (sum1 == sum2) {
            return 0; // 시작하자마자 같은 경우 0 return;
        }

        int cnt = 0;

        // 합계를 누적시켜야 한다.
        // 다 돌아본 경우 --> cnt <= size

        while (cnt <= size && !q1.isEmpty() && !q2.isEmpty()) {
            int num1 = q1.peek();
            int num2 = q2.peek();

            if (sum1 > sum2) { // sum1이 합이 더 큰 경우
                q2.offer(q1.poll());
                sum1 -= num1;
                sum2 += num1;
            } else {
                q1.offer(q2.poll());
                sum2 -= num2;
                sum1 += num2;
            }

            cnt++;

            if (sum1 == sum2) { // 합계가 같은 경우
                return cnt;
            }
        }

        return -1;
    }
}
