package 백준.가장긴짝수연속한부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 해당 범위 내에 존재하는 홀 수 갯수
        int left = 0;
        int right = 0;

        int oddCnt = 0;
        int answer = 0;
        while(right < N) {

            int value = arr[right];

            if(value % 2 == 1) {
                oddCnt++;
            }

            while(oddCnt > K) {
                if(arr[left] % 2 == 1) {
                    oddCnt--;
                }
                left++;
            }

            answer = Math.max(answer, right - left - oddCnt + 1);
            right++;
        }

        System.out.println(answer);
    }
}