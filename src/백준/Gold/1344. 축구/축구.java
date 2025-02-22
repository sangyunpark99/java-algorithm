import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static double A;
    private static double B;
    private static double[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine()) / 100.0;
        B = Integer.parseInt(br.readLine()) / 100.0;

        dp = new double[18][18][18];

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                for (int k = 0; k < 18; k++) {
                    dp[i][j][k] = -1.0;
                }
            }
        }

        System.out.printf("%.16f", goal(0, 0, 0));
    }

    // 0 ~ 17
    public static double goal(int scoreA, int scoreB, int turn) {
        if (turn == 18) { // 최종적
            // 득점 계산
            return isPrime(scoreA) || isPrime(scoreB) ? 1.0 : 0.0;
        }

        if (dp[turn][scoreA][scoreB] != -1.0) { // 이미 지나간 자리면 굳이 더 갈 필요가 없다.
            return dp[turn][scoreA][scoreB];
        }

        double ret = 0.0;
        // 1. A골, B노골
        ret += goal(scoreA + 1, scoreB, turn + 1) * A * (1 - B);
        // 2. A골, B골
        ret += goal(scoreA + 1, scoreB + 1, turn + 1) * A * B;
        // 3. A노골, B노골
        ret += goal(scoreA, scoreB, turn + 1) * (1 - A) * (1 - B);
        // 4. A노골, B골
        ret += goal(scoreA, scoreB + 1, turn + 1) * (1 - A) * B;

        dp[turn][scoreA][scoreB] = ret; // dp 값 입력
        return ret;
    }

    public static boolean isPrime(int score) {

        if (score < 2) {
            return false;
        }

        // 소수 판별
        for (int i = 2; i <= Math.sqrt(score); i++) {
            if (score % i == 0) {
                return false;
            }
        }

        return true;
    }
}
