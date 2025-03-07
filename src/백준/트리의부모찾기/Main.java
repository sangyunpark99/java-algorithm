package 백준.트리의부모찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] parents;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        parents = new int[n + 1];
        visited = new int[n + 1];

        for(int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited[1] = 1;
        dfs(1);

        for(int i = 2; i < parents.length; i++) {
            System.out.println(parents[i]);
        }
    }

    public static void dfs(int cur) {

        for(int i = 0; i < graph.get(cur).size(); i++) {
            int next = graph.get(cur).get(i);
            if(visited[next] == 0) {
                visited[next] = 1;
                dfs(next);
                parents[next] = cur;
            }
        }
    }
}