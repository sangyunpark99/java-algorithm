package 알고리즘문제해결전략1.chapter6.큐와스택데크.외계신호분석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /***
     * 단순한 노이즈인지 아니면 의미 있는 패턴을 가지고 있는지 파악
     * 길이 N인 신호 기록이 주어질 때, 합이 K인 부분 수열이 몇 개나 있는지 계산
     */

    private static int testCase;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            System.out.println(countRangesQueue(K,N));
        }
    }

    // Queue 방식
    // 그때그때 입력값을 전달 받는 온라인 알고리즘 방식은 queue를 사용해야 합니다.
    private static int countRangesQueue(int k, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;
        long sum = 0;
        long seed = 1983;

        for(int i = 0; i < n; i++) {
            int nextNum = (int)(seed % 10000 + 1);
            seed = (seed * 214013L + 2531011L) & 0xFFFFFFFFL;

            sum += nextNum;
            queue.offer(nextNum);

            while(sum > k && !queue.isEmpty()) {
                sum -= queue.poll();
            }

            if(sum == k) answer++;
        }
        return answer;
    }
}
