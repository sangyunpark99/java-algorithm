package 백준.사회망서비스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] dp;
    private static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][2]; // 현재 노드, 얼리어답터 유무

        for(int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tree.get(start).add(end);
            tree.get(end).add(start);
        }

        solution(1, -1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    // 트리 DP 유형
    private static void solution(int cur, int parent) {
        dp[cur][0] = 0; // 해당 노드를 끈 경우
        dp[cur][1] = 1; // 해당 노드를 켠 경우

        for(int i = 0; i < tree.get(cur).size(); i++) {
            int next = tree.get(cur).get(i);
            if(next == parent) continue; // 순환 참조 방지
            solution(next, cur);

            dp[cur][0] += dp[next][1];
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}
