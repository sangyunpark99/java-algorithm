package 백준.텀프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] student;
    private static boolean[] visited;
    private static boolean[] finished;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            student = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= n; j++) {
                student[j] = Integer.parseInt(st.nextToken());
            }

            for(int j = 1; j <= n; j++) {
                if(!visited[j]) {
                    dfs(j);
                }
            }

            System.out.println(n - count);
        }
    }

    private static void dfs(int cur) {
        visited[cur] = true;

        int next = student[cur];

        if(!visited[next]) {
            dfs(next);
        }else if(!finished[next]) {
            count++; // cur
            for(int tmp = next; tmp != cur; tmp = student[tmp]) {
                count++;
            }
        }

        finished[cur] = true;
    }
}
