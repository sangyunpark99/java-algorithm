package 백준.피리부는사나이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS 사이클 문제
// 사이클 지점에 SafeZone 설치
public class Main {

    private static String[][] graph;
    private static boolean[][] visited;
    private static boolean[][] finished;
    private static int safeZoneCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new String[N][M];
        visited = new boolean[N][M];
        finished = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            graph[i] = br.readLine().split("");
        }


        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) { // 다른 사이클 찾기
                    go(i,j);
                }
            }
        }

        System.out.println(safeZoneCnt);
    }

    private static void go(int y, int x) { // DFS 그래프 사이클 찾기
        visited[y][x] = true;

        int[] next = nextTarget(y,x);
        int ny = next[0];
        int nx = next[1];

        if(!visited[ny][nx]) {
            go(ny, nx);
        }else if(!finished[ny][nx]) { // 사이클 존재시 safeZone + 1
            safeZoneCnt++;
        }

        finished[y][x] = true; // 탐색 완료
    }

    private static int[] nextTarget(int y, int x) {
        String dir = graph[y][x];
        int ny = y;
        int nx = x;

        if("U".equals(dir)) {
            ny -= 1;
        }else if("R".equals(dir)) {
            nx += 1;
        }else if("D".equals(dir)) {
            ny += 1;
        }else if("L".equals(dir)){
            nx -= 1;
        }

        return new int[] {ny, nx};
    }
}
