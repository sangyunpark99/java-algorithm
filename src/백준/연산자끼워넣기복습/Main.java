package 백준.연산자끼워넣기복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] A;
    private static int[] cnt;

    private static int maxValue = Integer.MIN_VALUE;
    private static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0, A[0]);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    // 덧셈, 뺄셈, 곱셈, 나눗셈
    private static void dfs(int depth, int value) {
        if(depth == N - 1) {
            maxValue = Math.max(maxValue, value);
            minValue = Math.min(minValue, value);
            return;
        }

        if(cnt[0] > 0) {
            cnt[0] -= 1;
            dfs(depth + 1, value + A[depth + 1]);
            cnt[0] += 1;
        }

        if(cnt[1] > 0) {
            cnt[1] -= 1;
            dfs(depth + 1, value - A[depth + 1]);
            cnt[1] += 1;
        }

        if(cnt[2] > 0) {
            cnt[2] -= 1;
            dfs(depth + 1, value * A[depth + 1]);
            cnt[2] += 1;
        }

        if(cnt[3] > 0) {
            cnt[3] -= 1;
            dfs(depth + 1, value / A[depth + 1]);
            cnt[3] += 1;
        }
    }
}
