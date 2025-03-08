package 백준.트리와쿼리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n,r,q;
    private static List<List<Integer>> tree = new ArrayList<>();
    private static int[] subtree;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        subtree = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        subtree[r] = 1;
        visited[r] = true;
        dfs(r);

        for(int i = 0; i < q; i++) {
            int value = Integer.parseInt(br.readLine());
            System.out.println(subtree[value]);
        }
    }

    private static int dfs(int cur) {

        for(int next : tree.get(cur)) {
            if(!visited[next]) {
                visited[next] = true;
                subtree[next] = 1;
                subtree[cur] += dfs(next);
            }
        }

        return subtree[cur];
    }
}
