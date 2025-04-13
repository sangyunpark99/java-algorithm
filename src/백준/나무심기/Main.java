package 백준.나무심기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MAX_N = 200004;
    static final long MOD = 1000000007;
    static int n, value;
    static long ret = 1;
    static long[] treeCnt = new long[MAX_N];
    static long[] treeSum = new long[MAX_N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            value = Integer.parseInt(br.readLine());
            value++;

            if (i != 1) {
                long left = value * sum(treeCnt, 1, value - 1) - sum(treeSum, 1, value - 1); // 현재 기준으로 왼쪽 존재
                long right = sum(treeSum, value + 1, MAX_N - 1) - value * sum(treeCnt, value + 1, MAX_N - 1); // 현재 기준으로 오른쪽 존재
                ret = (ret * ((left + right) % MOD)) % MOD;
            }

            update(treeCnt, value, 1);
            update(treeSum, value, value);
        }

        System.out.println(ret);
    }

    public static long _sum(long[] tree, int value) { // 구간합
        long result = 0;
        int i = value;
        while (i > 0) {
            result += tree[i];
            i -= (i & -i);
        }
        return result;
    }

    public static long sum(long[] tree, int s, int e) { // 특정 구간 합
        if (s > e) return 0;
        return (_sum(tree, e) - _sum(tree, s - 1));
    }

    public static void update(long[] tree, int x, long value) { //
        int i = x;
        while (i < MAX_N) {
            tree[i] += value;
            i += (i & -i);
        }
    }
}