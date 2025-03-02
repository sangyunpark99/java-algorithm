package 백준.N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dfs(new int[m], 0, new boolean[n + 1], m, n);
    }

    private static  void dfs(int[] array, int depth, boolean[] visited, int m, int n) {

        if(depth == m) {
            for(int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                array[depth] = i;
                visited[i] = true;
                dfs(array, depth + 1, visited, m, n);
                visited[i] = false;
            }
        }
    }
}
