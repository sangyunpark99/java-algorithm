package 백준.벽부수고이동하기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] map;
    private static int N, M;
    private static boolean[][] visited;
    private static int[] cnt;
    private static int[][] area;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) { // O(1000)
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        cnt = new int[N * M + 1]; // 각 영역에 해당하는 0의 갯수
        area = new int[N + 1][M + 1]; // 각 좌표가 해당하는 영역 번호
        int areaNumber = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    cnt[areaNumber] = bfs(i, j, areaNumber);
                    areaNumber++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int total = 1;
                    Set<Integer> findArea = new HashSet<>();

                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

                        int a = area[ny][nx]; // 지역 찾기
                        if (!findArea.contains(a)) { // 지역 포함시키기
                            findArea.add(a);
                            total += cnt[a];
                        }
                    }

                    map[i][j] = total % 10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs(int y, int x, int areaNumber) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y,x});
        visited[y][x] = true;
        area[y][x] = areaNumber;

        int size = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cy = cur[0];
            int cx = cur[1];

            for(int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(!visited[ny][nx] && map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    area[ny][nx] = areaNumber;
                    queue.add(new int[]{ny,nx});
                    size++;
                }
            }
        }

        return size;
    }
}
