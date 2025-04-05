package 백준.회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    // 초밥의 최댓값
    // 최대한 꽁짜 초밥은 피하면서 먹기
    // 순환 구조 이므로, left가 n에 갈때까지

    private static int n;
    private static int d;
    private static int k;
    private static int c;
    private static int[] food;

    // 벨트에 없을 경우도 고려

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        food = new int[n];


        for(int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            food[i] = value;
        }

        int left = 0;
        int right = left + k - 1;
        int answer = Integer.MIN_VALUE;

        while(left < n) { // O(n) = 30,000
            answer = Math.max(answer, getCnt(left, right)); // O(n) = 3,000
            left++;
            right++;
        }

        System.out.println(answer); // O(n) = 30,000 * 3,000
    }

    private static int getCnt(int start, int end) { // 초밥 갯수
        int cnt = 0;
        HashSet<Integer> set = new HashSet<>(); // 중복 제거
        for(int i = start; i <= end; i++) { //
            int value = i;
            if(i >= n) { // i가 n보다 큰 경우
                value = i % n;
            }
            set.add(food[value]);
        }

        if(!set.contains(c)) { // 꽁짜 초밥
            cnt++;
        }

        return cnt + set.size();
    }
}
