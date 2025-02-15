package 백준.인기가넘쳐흘러;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k, t, a, b;
    static int[] count = new int[301];
    static int[][] dp;
    static List<Pair> v = new ArrayList<>();

    static int go(int curIdx, int remainPerson, int prevPerson) {
        if (curIdx == v.size()) {
            return 0;
        }
        if (dp[curIdx][remainPerson] != Integer.MIN_VALUE) {
            return dp[curIdx][remainPerson];
        }

        int cost = Math.max(0, t - v.get(curIdx).second);
        int realCost = Math.max(0, cost - prevPerson);

        int result = go(curIdx + 1, remainPerson, cost);

        if (remainPerson >= realCost) {
            result = Math.max(result, go(curIdx + 1, remainPerson - realCost, cost) + v.get(curIdx).first);
        }

        return dp[curIdx][remainPerson] = result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            for (int i = a; i < b; i++) {
                count[i] = Math.min(t, ++count[i]);
            }
        }

        int currentCount = count[1];
        int segmentCount = 1;
        for (int i = 2; i <= n; i++) {
            if (currentCount != count[i]) {
                v.add(new Pair(segmentCount, currentCount));
                segmentCount = 1;
                currentCount = count[i];
            } else {
                segmentCount++;
            }
        }
        v.add(new Pair(segmentCount, currentCount));

        dp = new int[v.size()][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        System.out.println(go(0, k, 0));
    }

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
