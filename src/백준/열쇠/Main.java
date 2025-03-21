package 백준.열쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 재탐색
public class Main {

    private static int h, w;
    private static char[][] map;
    private static boolean[][] visited;
    private static Set<Character> keys;
    private static List<Point>[] doors;
    private static int documentCnt;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    // 문자와 index를 잘 활용해야 한다.
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h + 2][w + 2];
            visited = new boolean[h + 2][w + 2];

            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(map[i], '.');
            }

            for (int i = 1; i <= h; i++) {
                String line = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            keys = new HashSet<>();

            String keyInput = br.readLine();

            if (!keyInput.equals("0")) {
                for (char c : keyInput.toCharArray()) {
                    keys.add(c); // 열쇠 추가
                }
            }

            // 알파벳 문
            doors = new ArrayList[26]; // 알파벳을 인덱스로 0 ~ 25까지 총 26개
            for (int i = 0; i < 26; i++) {
                doors[i] = new ArrayList<>();
            }

            documentCnt = 0;
            bfs();
            System.out.println(documentCnt);
        }
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int y = p.y;
            int x = p.x;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= h + 2 || nx < 0 || nx >= w + 2) continue;
                if(visited[ny][nx]) continue;
                if(map[ny][nx] == '*') continue;

                visited[ny][nx] = true;

                char value = map[ny][nx];

                if(value == '$') { // 문서를 만난경우
                    documentCnt++;
                }

                if(value >= 'a' && value <= 'z') { // a - z 까지
                    // 열쇠로 문을 열 수 있는 경우 큐에 이동 추가
                    if(!keys.contains(value)) {
                        keys.add(value);
                        int doorIdx = value - 'a';
                        for(Point doorPoint: doors[doorIdx]) { // 이전에 열지 못했던 부분 재탐색
                            queue.add(doorPoint);
                        }

                        doors[doorIdx].clear(); // 열쇠로 들어온 문 지우기
                    }
                }

                if(value >= 'A' && value <= 'Z') { // A - Z 까지
                    if(!keys.contains(Character.toLowerCase(value))) { // 문을 발견한 경우
                        doors[value - 'A'].add(new Point(ny,nx));
                        continue;
                    }
                }

                queue.add(new Point(ny,nx));
            }
        }
    }

}
