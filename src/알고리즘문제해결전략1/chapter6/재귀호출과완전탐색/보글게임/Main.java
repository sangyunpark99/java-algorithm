package 알고리즘문제해결전략1.chapter6.재귀호출과완전탐색.보글게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static char[][] map = new char[5][5];

    // 북, 북동, 동, 남동, 남, 남서, 서, 북서
    private static int[] dy = {-1,-1,0,1,1,1,0,-1};
    private static int[] dx = {0,1,1,1,0,-1,-1,-1};

    private static String word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        word = br.readLine();

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(map[i][j] == word.charAt(0)) { // 시작이 같은 경우
                    if(dfs(i,j,1)) System.out.println(true);
                    return;
                }
            }
        }        // 시간 복잡도 : O(25 x 8^(찾고자 하는 단어의 길이))
        // 단어의 길이가 짧은 경우에만 완전 탐색으로 해결할 수 있다.
        System.out.println(false);
    }

    public static boolean dfs(int y, int x, int idx) {

        if(word.length() == idx) return true; // 기저 사례

        for(int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;
            if(map[ny][nx] != word.charAt(idx)) continue;  // 같은 단어만 탐색
            if(dfs(ny,nx,idx + 1)) { // 핵심 코드
                return true;
            }
        }
        return false;
    }
}
