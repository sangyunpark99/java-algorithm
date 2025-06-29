package swea.레벨업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 어떤걸 빼야 할까?
            // 완탐시 10,000 * 10,000
            int[] sum = new int[N];
            sum[0] = arr[0];

            // 두 지점의 합이 최대인 지점
            int left = 0;
            int right = 1;

            int total = 0;
            while (right < N) {
                int value = Math.abs(arr[right] - arr[left]);
                total += value;
                left++;
                right++;
            }

            int answer = Integer.MAX_VALUE;
            for(int i = 1; i < N - 1; i++) { // 빼는 지점
                int gap = Math.abs(arr[i] - arr[i-1]) + Math.abs(arr[i+1] - arr[i]);
                int mid = Math.min(answer, total - gap + Math.abs(arr[i + 1] - arr[i - 1]));
                answer = Math.min(answer, mid);
            }

            System.out.println(answer);
        }
    }
}