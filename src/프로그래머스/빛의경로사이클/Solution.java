package 프로그래머스.빛의경로사이클;

class Solution {
    // 빛이 이동할 수 있는 경로 사이클과 각 사이클의 길이
    // 시간 복잡도 : 500 x 500 x 4 = 1,000,000 (완탐 + 누적)
    private char[][] grid;
    // 시작점을 제외한 나머지 부분에서 같은 방향으로 같은 곳을 방문한 경우 return

    // 상,우,하,좌
    // 공간 복잡도 생각
    private int R;
    private int C;
    private int[] dy = {-1,0,1,0};
    private int[] dx = {0,1,0,-1};
    private boolean[][][] visited;

    public int[] solution(String[] grids) {

        ArrayList<Integer> values = new ArrayList<>();

        R = grids.length;
        C = grids[0].length();

        grid = new char[R][C];

        for(int i = 0; i < grids.length; i++) {
            grid[i] = grids[i].toCharArray();
        }

        visited = new boolean[R][C][4];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                char start = grid[i][j];
                for(int dir = 0; dir < 4; dir++) {
                    if(!visited[i][j][dir]) {
                        int value = move(i, j, dir);
                        if(value != -1) {
                            values.add(value);
                        }
                    }
                }
            }
        }

        Collections.sort(values);

        int[] answer = new int[values.size()];
        for(int i = 0; i < values.size(); i++) {
            answer[i] = values.get(i);
        }

        return answer;
    }

    private int move(int y, int x, int dir) {

        int ny = y;
        int nx = x;
        int ndir = dir;
        int count = 0;

        while(true) {
            if(visited[ny][nx][ndir]) {
                if(ny == y && nx == x && ndir == dir) {
                    return count;
                }else {
                    return -1;
                }
            }

            visited[ny][nx][ndir] = true;
            count++;

            if(grid[ny][nx] == 'L') {
                ndir = (ndir + 3) % 4;
            }else if(grid[ny][nx] == 'R') {
                ndir = (ndir + 1) % 4;
            }

            ny = (ny + dy[ndir] + R) % R;
            nx = (nx + dx[ndir] + C) % C;
        }
    }
}
