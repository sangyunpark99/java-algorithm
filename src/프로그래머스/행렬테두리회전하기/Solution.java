package 프로그래머스.행렬테두리회전하기;

import java.util.*;

class Solution {

    private int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        // 테두리에서 제일 작은 수 추출
        map = new int[rows][columns];

        int value = 1;
        for(int i = 0; i < rows; i++ ) {
            for(int j = 0; j < columns; j++) {
                map[i][j] = value;
                value++;
            }
        }

        int idx = 0;

        for(int[] query: queries) {
            int y1 = query[0];
            int x1 = query[1];
            int y2 = query[2];
            int x2 = query[3];

            answer[idx++] = rotate(y1-1,x1-1,y2-1,x2-1);
        }

        return answer;
    }

    private int rotate(int y1, int x1, int y2, int x2) {

        int prev = map[y1][x1];
        int min = prev;

        for(int x = x1 + 1; x <= x2; x++) {
            int tmp = map[y1][x];
            map[y1][x] = prev;
            prev = tmp;
            min = Math.min(min, tmp);
        }

        for(int y = y1 + 1; y <= y2; y++) {
            int tmp = map[y][x2];
            map[y][x2] = prev; // 이전 값 갱신
            prev = tmp;
            min = Math.min(min, tmp);
        }

        for(int x = x2 - 1; x >= x1; x--) {
            int tmp = map[y2][x];
            map[y2][x] = prev;
            prev = tmp;
            min = Math.min(min, tmp);
        }

        for(int y = y2 - 1; y >= y1; y--) {
            int tmp = map[y][x1];
            map[y][x1] = prev;
            prev = tmp;
            min = Math.min(min, tmp);
        }

        return min;
    }
}
