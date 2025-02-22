import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, k, t;
    private static int[][] dp;
    private static int[] count = new int[301];
    private static ArrayList<Node> ranges = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // 친구를 투입해도 되고, 투입하지 않아도 된다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for (int j = a; j < b; j++) {
                count[j]++; // 각 위치당 존재하는 인원 수
            }
        }

        int curCnt = count[1];
        int rangeCnt = 1;
        for (int i = 2; i <= n; i++) {
            if (curCnt != count[i]) {
                ranges.add(new Node(rangeCnt, curCnt));
                rangeCnt = 1;
                curCnt = count[i];
            } else {
                rangeCnt++;
            }
        }
        
        // 마지막 구간 추가
        ranges.add(new Node(rangeCnt, curCnt));

        dp = new int[ranges.size()][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        System.out.println(go(0, k, 0));
    }

    public static int go(int curIdx, int remainPerson, int prev) {
        if (curIdx == ranges.size()) {
            return 0;
        }

        if (dp[curIdx][remainPerson] != Integer.MIN_VALUE) {
            return dp[curIdx][remainPerson];
        }

        int cost = Math.max(0, t - ranges.get(curIdx).count); // 추가해야 하는 인원
        int realCost = Math.max(0, cost - prev); // 이전 인원을 제외한 추가 인원

        int result = go(curIdx + 1, remainPerson, cost); // 인원 추가 안하고 진행

        if (remainPerson >= realCost) {
            result = Math.max(result, go(curIdx + 1, remainPerson - realCost, cost) + ranges.get(curIdx).range);
        }

        return dp[curIdx][remainPerson] = result;

    }

    static class Node {
        int range;
        int count;

        public Node(int range, int count) {
            this.range = range;
            this.count = count;
        }
    }
}