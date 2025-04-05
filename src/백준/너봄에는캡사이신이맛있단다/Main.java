package 백준.너봄에는캡사이신이맛있단다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int n;
    private static int MOD = 1_000_000_007;
    private static int[] food;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        food = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 0 부터 1

        Arrays.sort(food);

        long[] pow2 = new long[n];
        pow2[0] = 1; // 1개

        for(int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        long result = 0;

        for(int i = 0; i < n; i++) {
            long maxCnt = pow2[i];
            long minCnt = pow2[n - 1 - i];
            long diff = (maxCnt - minCnt + MOD) % MOD; // 음수 방지
            result = (result + food[i] * diff) % MOD;
        }

        System.out.println(result);
    }
}
