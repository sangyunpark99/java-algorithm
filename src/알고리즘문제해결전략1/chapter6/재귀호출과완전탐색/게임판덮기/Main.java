package 알고리즘문제해결전략1.chapter6.재귀호출과완전탐색.게임판덮기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 완전탐색 + 백트래킹으로 모든 경우 구하기
    // 늘 잘게 쪼개기 한칸을 기준으로 생각
    private static int[][][] coverType = {
            {{0,0},{1,0},{0,1}},
            {{0,0},{0,1},{1,1}},
            {{0,0},{1,0},{1,1}},
            {{0,0},{1,0},{1,-1}}
    };

    private static char[][] board;
    private static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int c = Integer.parseInt(br.readLine());

        while(c-- > 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            board = new char[h][w];
            int whiteCount = 0;

            for(int i = 0; i < h; i++) {
                char[] arr = br.readLine().toCharArray();
                for(int j = 0; j < w; j++) {
                    board[i][j] = arr[j];
                    if(board[i][j] == '.') {
                        whiteCount++;
                    }
                }
            }

            if(whiteCount % 3 != 0) {  // 빈칸이 3의 배수가 아닌경우
                System.out.println(0);
            }else {
                System.out.println(cover());
            }
        }
    }

    // 재귀 호출은 항상 종료 조건을 생각
    // 갯수 반환시 누적되는 부분도 생각
    private static int cover() {

        int y = -1;
        int x = -1;

        outer:
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(board[i][j] == '.') {
                    y = i;
                    x = j;
                    break outer;
                }
            }
        }

        if(y == -1) return 1; // 완전히 블록을 다 채운 경우

        int result = 0;

        // y, x를 기준으로 4가지 유형의 도형을 놓기
        for(int[][] type: coverType) { // 각 유형별로 커버
            if(tryCover(y,x,type)) {
                result += cover();
                unCover(y,x,type);
            }
        }

        return result;
    }

    private static boolean tryCover(int y, int x, int[][] type) { // cover
        if(!canPlace(y, x, type)) { // 커버할 수 없는 경우
            return false;
        }

        // 커버 가능한 경우
        for(int[] t: type) {
            board[y + t[0]][x + t[1]] = '#';
        }

        return true;
    }

    private static void unCover(int y, int x, int[][] type) {
        for(int[] t: type) {
            board[y + t[0]][x + t[1]] = '.';
        }
    }

    private static boolean canPlace(int y, int x, int[][] type) {
        for(int[] t : type) {
            int ny = y + t[0];
            int nx = x + t[1];

            if(ny < 0 || ny >= h || nx < 0 || nx >= w || board[ny][nx] != '.') return false;
        }

        return true;
    }

//    private static boolean tryCover(int y, int x, int[][] type) {
//
//        if(!canPlace(y, x, type)) return false;
//
//        for(int[] t : type) {
//            board[y + t[0]][x + t[1]] = '#';
//        }
//
//        return true;
//    }
//
//    private static void unCover(int y, int x, int[][] type) {
//        for(int[] t : type) {
//            board[y + t[0]][x + t[1]] = '.';
//        }
//    }
//
//    private static boolean canPlace(int y, int x, int[][] type) {
//        for(int[] t: type) {
//            int ny = y + t[0];
//            int nx = x + t[1];
//
//            if(ny < 0 || ny >= h || nx < 0 || nx >= w) {
//                return false;
//            }
//            if(board[ny][nx] != '.') {
//                return false;
//            }
//        }
//        return true;
//    }
}
