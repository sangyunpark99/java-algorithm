import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int c;

    private static int[] jewels;
    private static int[][][] dp; // 현재 가방 번째, 가방에 담긴 무게, 보석 인덱스

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        jewels = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[24][1 << 14][24]; // 비트 연산자는 제약이 적을 때 사용 가능하다.
        // 지금까지 담은 보석의 집합이 필요하다. 수가 작은 경우 비트마스킹을 사용해서 하나의 정수로 표현가능하다.

        System.out.println(go(0, 0, c)); // 0번째부터 시작
    }

    public static int go(int here, int visited, int capacity) {
        if (here == m || visited == (1 << n) - 1) {
            return 0;
        }

        if (dp[here][visited][capacity] != 0) {
            return dp[here][visited][capacity];
        }

        for (int i = 0; i < n; i++) {
            if (((1 << i) & visited) > 0) {
                continue;
            }

            if (capacity >= jewels[i]) {
                dp[here][visited][capacity] = Math.max(dp[here][visited][capacity],
                        go(here, visited | (1 << i), capacity - jewels[i]) + 1);
            } else {
                dp[here][visited][capacity] = Math.max(dp[here][visited][capacity], go(here + 1, visited, c));
            }
        }

        return dp[here][visited][capacity];
    }

}