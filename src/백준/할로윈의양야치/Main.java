package 백준.할로윈의양야치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 한 아이의 사탕을 뺏으면, 그 아이 친구들의 사탕도 모조리 빼앗아버린다.
    // 거리에 주저앉아서 운다. K명 이상 아이들이 우는 경우 어른들이 나옴
    // 어른들에게 들키지 않고 최대한 많이 사탕을 뺏는 방법

    private static int N,M,K;
    private static int[] candies;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static List<Group> groups = new ArrayList<>();
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candies = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            graph.get(first).add(second);
            graph.get(second).add(first);
        }

        visited = new boolean[N + 1]; // 1번

        for(int cur = 1; cur <= N; cur++) {
            if(!visited[cur]) {
                Group group = new Group(1, candies[cur - 1]);
                dfs(cur, group);
                groups.add(group);
            }
        }

        dp = new int[groups.size()][K];

        for(int i = 0; i < groups.size(); i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(go(0,0));
    }

    private static int go(int idx, int person) {
        if(person >= K) {
            return Integer.MIN_VALUE;
        }

        if(idx == groups.size()) {
            return 0;
        }

        if(dp[idx][person] != -1) {
            return dp[idx][person];
        }

        int notTake = go(idx + 1, person);

        int take = 0;
        Group cur = groups.get(idx);

        if(person + cur.person < K) {
            take = cur.candy + go(idx + 1, person + cur.person);
        }

        return dp[idx][person] = Math.max(notTake, take);
    }

    private static void dfs(int cur, Group group) {
        visited[cur] = true;

        for(int i = 0; i < graph.get(cur).size(); i++) {
            int next = graph.get(cur).get(i);
            if(!visited[next]) {
                group.addPerson();
                group.addCandy(candies[next - 1]);
                dfs(next, group);
            }
        }
    }

    public static class Group {
        int person;
        int candy;

        public Group(int person, int candy) {
            this.person = person;
            this.candy = candy;
        }

        public void addPerson() {
            this.person++;
        }

        public void addCandy(int value) {
            this.candy += value;
        }
    }
}
