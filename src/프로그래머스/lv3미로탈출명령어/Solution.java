package 프로그래머스.lv3미로탈출명령어;

public class Solution {
    private boolean[][][] visited;
    private String answer;

    private int[] dy = new int[]{1, 0, 0, -1};
    private int[] dx = new int[]{0, -1, 1, 0};
    private String[] direction = new String[]{"d", "l", "r", "u"};
    // 사전순으로 가장 빠른 길을 찾는다.

    public String solution(int n, int m, int y, int x, int c, int r, int k) {
        answer = "";
        //(x,y) -> (r,c)
        //3가지 조건
        //1. 격자의 바깥으로는 나갈 수 없다.
        //2. (x,y)에서 (r,c)까지로 이동하는 거리가 총 K 여야 한다. 단, 같은 격자 두번 이상 방문 가능
        //3. 미로 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출

        //이동 경로 문자열로 바꿀 수 있다.
        //상,하,좌,우 격자로 한 칸씩 이동

        // 격자는 (0,0) -> (1,1)로 표현 : 1씩 빼주는 걸 고려해야 한다.
        // 탈출 못할 경우엔 impossible return

        // DFS탐색을 하되, 탈출 할 수 없는 경우를 저장해놓고 백트래킹으로 가능성을 배제한다.
        // 브루트 포스로 하게 되면 시간 초과 무조건

        visited = new boolean[n + 1][m + 1][k + 1];
        dfs(k, n, m, x - 1, y - 1, r - 1, c - 1, k, "");

        if (answer.isEmpty()) {
            answer = "impossible";
        }

        return answer;
    }

    public void dfs(int k, int n, int m, int x, int y, int targetX, int targetY, int remainingMoves, String path) {

        if (answer.length() == k) {
            return;
        }

        if (remainingMoves < 0) { // 이동 횟수를 다 사용한 경우
            return;
        }

        if (targetX == x && targetY == y && remainingMoves == 0) { // 도착한 경우
            answer = path; // 정답을 찾은 경우
            return;
        }

        if (visited[y][x][remainingMoves]) { // 이미 방문한 경우 -> 재방문 x
            return;
        }

        // 남은 거리가 남은 이동횟수보다 큰 경우도 제외
        int distance = Math.abs(targetX - x) + Math.abs(targetY - y);
        if (distance > remainingMoves || (distance - remainingMoves) % 2 != 0) {
            return;
        }

        visited[y][x][remainingMoves] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                continue;
            }

            dfs(k, n, m, nx, ny, targetX, targetY, remainingMoves - 1, path + direction[i]);
        }

        visited[y][x][remainingMoves] = false;
    }
}
