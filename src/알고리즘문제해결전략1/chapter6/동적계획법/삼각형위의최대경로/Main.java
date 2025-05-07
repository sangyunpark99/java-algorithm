package 알고리즘문제해결전략1.chapter6.동적계획법.삼각형위의최대경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int[][] dp = new int[100][100];
    private static List<List<Integer>> list = new ArrayList<>();
    private static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 5; i++) {
            list.add(Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toList());
        }

        for(int i = 0; i < 5; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(solve(0,0));

        for(int i = 0; i < 5; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(solve2(0,0));

        for(int i = 0; i < 5; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(solve3());
    }

    private static int solve(int y, int x) {

        if(y >= 5 || x >= list.get(y).size()) return 0;
        if(list.get(y).get(x) == 0) return 0;

        int curValue = list.get(y).get(x);

        if(dp[y][x] != -1) return dp[y][x];

        int down = curValue + solve(y + 1, x);
        int right = curValue + solve(y + 1, x + 1);

        dp[y][x] = Math.max(dp[y][x], Math.max(down, right));

        return dp[y][x];
    }

    private static int solve2(int y, int x) {

        int curValue = list.get(y).get(x);
        if(y == 4) return curValue;

        if(dp[y][x] != -1) return dp[y][x];

        return dp[y][x] = Math.max(solve2(y + 1, x), solve2(y + 1, x + 1)) + curValue;
    }

    private static int solve3() {

        dp[0][0] = list.get(0).get(0);
        int maxValue = Integer.MIN_VALUE;

        for(int y = 1; y < 5; y++) {
            for(int x = 1; x < list.get(y).size(); x++) {
                int cur = list.get(y).get(x);
                dp[y][x] = Math.max(dp[y][x], Math.max(cur + dp[y - 1][x - 1], cur + dp[y - 1][x]));
                maxValue = Math.max(maxValue, dp[y][x]);
            }
        }

        return maxValue;
    }
}
