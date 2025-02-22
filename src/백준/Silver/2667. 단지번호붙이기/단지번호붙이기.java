import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static int[][] map;
    private static int[][] visited;
    private static int n;

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new int[n][n];

        for(int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = 0;

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j] == 0 && map[i][j] == 1) {
                    answer++;
                    visited[i][j] = 1;
                    list.add(dfs(i,j));
                }
            }
        }

        System.out.println(answer);
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + 1);
        }
    }

    private static int dfs(int y, int x) {

        int cnt = 0;

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= n || nx < 0 || nx >= n || visited[ny][nx] == 1) continue;

            if(map[ny][nx] == 1) {
                visited[ny][nx] = 1;
                cnt += dfs(ny,nx) + 1;
            }
        }

        return cnt;
    }
}