package 백준.낚시왕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 1. 오른쪽 한칸
    // 2. 상어 중에서 땅과 제일 가까운 상어를 잡는다.
    // 3. 상어가 이동한다. 입력으로 주어진 속도로 이동, 격자판의 경계를 넘는 경우엔 방향을 바꿔 이동
    // 4. 상어가 이동을 마친 후 한 칸에 상어 2마리 이상 존재시 -> 크기가 가장 큰 상어가 나머지 상어를 모두 잡아 먹는다.

    private static int R;
    private static int C;
    private static int M;

    private static Shark[][] map;

    // 1: 위, 2 : 아래, 3 : 오른쪽, 4 : 왼쪽
    private static int[] dy = {0,-1,1,0,0};
    private static int[] dx = {0,0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) { // 상어
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if (d == 1 || d == 2) {
                s %= (R - 1) * 2;
            } else {
                s %= (C - 1) * 2;
            }

            map[r][c] = new Shark(r, c, s, d, z);
        }

        int res = 0;

        for (int person = 1; person <= C; person++) {

            for (int i = 1; i <= R; i++) {
                if (map[i][person] != null) { // 상어 잡기
                    res += map[i][person].z;
                    map[i][person] = null;
                    break;
                }
            }

            Shark[][] newMap = new Shark[R + 1][C + 1]; // 맵 갱신

            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    if (map[i][j] != null) {
                        Shark shark = map[i][j];
                        int cy = shark.r;
                        int cx = shark.c;
                        int dir = shark.d;

                        for (int move = 0; move < shark.s; move++) { // 상어 이동
                            int ny = cy + dy[dir];
                            int nx = cx + dx[dir];

                            if (ny < 1 || ny > R || nx < 1 || nx > C) { // 넘어가는 경우
                                // 방향 전환
                                if (dir == 1) dir = 2;
                                else if (dir == 2) dir = 1;
                                else if (dir == 3) dir = 4;
                                else dir = 3;

                                // 방향 틀었기 때문에 재갱신 - 방향 전환하고 한칸 더 간다.
                                ny = cy + dy[dir];
                                nx = cx + dx[dir];
                            }

                            cy = ny;
                            cx = nx;
                        }

                        shark.r = cy;
                        shark.c = cx;
                        shark.d = dir;

                        if (newMap[cy][cx] == null || newMap[cy][cx].z < shark.z) { // 상어 없는 경우 or 현재 상어가 더 큰경우 갱신
                            newMap[cy][cx] = shark;
                        }
                    }
                }
            }

            map = newMap;
        }

        System.out.println(res);
    }

    public static class Shark {
        int r;
        int c;
        int s; // 속력
        int d; // 방향
        int z;

        public Shark(int y, int x, int s, int d, int z) {
            this.r = y;
            this.c = x;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
