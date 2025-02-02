package 프로그래머스.아이템줍기;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int[][] map;
    private int CharacterX;
    private int CharacterY;
    private int ItemX;
    private int ItemY;
    private int[] dy = new int[]{-1, 0, 1, 0};
    private int[] dx = new int[]{0, 1, 0, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        map = new int[101][101];
        CharacterX = characterX;
        CharacterY = characterY;
        ItemX = itemX;
        ItemY = itemY;

        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;

            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;

            for (int y = y1 + 1; y < y2; y++) {
                for (int x = x1 + 1; x < x2; x++) {
                    map[y][x] = 0;
                }
            }
        }

        return bfs() / 2;
    }

    private int bfs() {
        int[][] visited = new int[101][101];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{2 * CharacterY, 2 * CharacterX});
        visited[2 * CharacterY][2 * CharacterX] = 1;

        while (!queue.isEmpty()) {
            int[] curPosition = queue.poll();
            int curY = curPosition[0];
            int curX = curPosition[1];

            if (curY == 2 * ItemY && curX == 2 * ItemX) {
                return visited[curY][curX] - 1;
            }

            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];

                if (ny < 0 || ny >= 101 || nx < 0 || nx >= 101) {
                    continue;
                }

                if (visited[ny][nx] == 0 && map[ny][nx] == 1) {
                    visited[ny][nx] = visited[curY][curX] + 1;
                    queue.add(new int[]{ny, nx});
                }
            }
        }

        return 0;
    }
}
