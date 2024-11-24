import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 완전 탐색 + 백트래킹 + DP
public class Main {

    private static int N;
    private static int[] number;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new long[N][21]; // [현재 index][합계]

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 21; j++) {
                dp[i][j] = -1;
            }
        }

        long result = go(1, number[0]);

        System.out.println(result);
    }

    public static long go(int idx, int sum) {

        if (sum < 0 || sum > 20) { // 가지치기
            return 0;
        }

        if (idx == N - 1) { // 전부 탐색한 경우
            return sum == number[N - 1] ? 1 : 0; // 합계가 같으면 1, 다르면 0
        }

        if (dp[idx][sum] != -1) { // 이미 값이 저장되어 있다면
            return dp[idx][sum];
        }

        dp[idx][sum] = 0;
        dp[idx][sum] += go(idx + 1, sum + number[idx]);
        dp[idx][sum] += go(idx + 1, sum - number[idx]);

        return dp[idx][sum];
    }
}