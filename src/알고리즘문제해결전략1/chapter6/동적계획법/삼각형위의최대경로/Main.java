package 알고리즘문제해결전략1.chapter6.동적계획법.삼각형위의최대경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int[][] dp;
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 5; i++) {
            list.add(Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toList());
        }

        dp = new int[5][5];

        for(int i = 0; i < 5; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(list);

        System.out.println(solve(0,0));
    }

    private static int solve(int y, int x) {

        if(y >= 5 || x >= list.get(y).size()) return 0;

        int curValue = list.get(y).get(x);

        if(dp[y][x] != -1) return dp[y][x];

        int down = curValue + solve(y + 1, x);
        int right = curValue + solve(y + 1, x + 1);

        dp[y][x] = Math.max(dp[y][x], Math.max(down, right));

        return dp[y][x];
    }
}
