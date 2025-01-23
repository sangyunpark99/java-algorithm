package SW.상호의배틀필드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    // 북,동,남,서
    private static int[] dy = new int[]{-1, 0, 1, 0};
    private static int[] dx = new int[]{0, 1, 0, -1};
    private static String[][] map;
    private static String[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int direction = 0;
        int y = 0;
        int x = 0;
        String tank = "";
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            map = new String[H][W];
            for (int i = 0; i < H; i++) {
                String[] value = br.readLine().split("");
                for (int j = 0; j < value.length; j++) {
                    map[i][j] = value[j];
                    if (value[j].equals("^")) { // 북
                        direction = 0;
                        y = i;
                        x = j;
                        tank = "^";
                    }
                    if (value[j].equals(">")) { // 동
                        direction = 1;
                        y = i;
                        x = j;
                        tank = ">";
                    }
                    if (value[j].equals("v")) { // 남
                        direction = 2;
                        y = i;
                        x = j;
                        tank = "v";
                    }
                    if (value[j].equals("<")) { // 서
                        direction = 3;
                        y = i;
                        x = j;
                        tank = "<";
                    }
                }
            }
            int N = Integer.parseInt(br.readLine());
            order = br.readLine().split("");
            for (String orderValue : order) {
                int ny, nx = 0;
                int shootY = y;
                int shootX = x;
                if (orderValue.equals("U")) { // 위로 이동
                    ny = y + dy[0];
                    nx = x + dx[0];
                    tank = "^";
                    direction = 0;
                    map[y][x] = tank;
                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                        continue;
                    }
                    if (map[ny][nx].equals("*") || map[ny][nx].equals("#") || map[ny][nx].equals("-")) {
                        continue;
                    }
                    map[ny][nx] = tank;
                    map[y][x] = ".";
                    x = nx;
                    y = ny;
                }
                if (orderValue.equals("R")) {
                    ny = y + dy[1];
                    nx = x + dx[1];
                    tank = ">";
                    direction = 1;
                    map[y][x] = tank;
                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                        continue;
                    }
                    if (map[ny][nx].equals("*") || map[ny][nx].equals("#") || map[ny][nx].equals("-")) {
                        continue;
                    }
                    map[ny][nx] = tank;
                    map[y][x] = ".";
                    x = nx;
                    y = ny;
                }

                if (orderValue.equals("D")) {
                    ny = y + dy[2];
                    nx = x + dx[2];
                    tank = "v";
                    direction = 2;
                    map[y][x] = tank;
                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                        continue;
                    }
                    if (map[ny][nx].equals("*") || map[ny][nx].equals("#") || map[ny][nx].equals("-")) {
                        continue;
                    }
                    map[ny][nx] = tank;
                    map[y][x] = ".";
                    x = nx;
                    y = ny;
                }

                if (orderValue.equals("L")) {
                    ny = y + dy[3];
                    nx = x + dx[3];
                    tank = "<";
                    direction = 3;
                    map[y][x] = tank;
                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                        continue;
                    }
                    if (map[ny][nx].equals("*") || map[ny][nx].equals("#") || map[ny][nx].equals("-")) {
                        continue;
                    }
                    map[ny][nx] = tank;
                    map[y][x] = ".";
                    x = nx;
                    y = ny;
                }

                if (orderValue.equals("S")) {
                    while (true) {
                        shootY += dy[direction]; // 방향
                        shootX += +dx[direction]; // 방향
                        if (shootY < 0 || shootY >= H || shootX < 0 || shootX >= W) { // 맴 넘어간 경우
                            break;
                        }
                        if (map[shootY][shootX].equals("#")) { // 벽을 만난경우
                            break;
                        }
                        if (map[shootY][shootX].equals("*")) {
                            map[shootY][shootX] = "."; // 평지로 변경
                            break;
                        }
                    }
                }
            }
            System.out.print("#" + (t + 1) + " ");
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }
}
