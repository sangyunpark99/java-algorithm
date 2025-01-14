package 프로그래머스.lv3기둥과보설치;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int[][] floor;
    private int[][] pillar;

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        // 1. 기둥, 보 세우기
        // 2. 조건 검사하기
        // 바닥과 기둥, 기둥과 보, 보와보

        floor = new int[n + 1][n + 1]; // 1부터 ~
        pillar = new int[n + 1][n + 1];

        for (int i = 0; i < build_frame.length; i++) { // 기둥과 보 위치 파악
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int construction = build_frame[i][2]; // 기둥 or 보
            int installOrRemove = build_frame[i][3];

            if (installOrRemove == 0) { // 제거
                if (construction == 0) { // 기둥
                    removePillar(x, y, n);
                } else { // 보
                    removeFloor(x, y, n);
                }
            } else { // 설치
                if (construction == 0) { // 기둥
                    installPillar(x, y);
                } else { // 보
                    installFloor(x, y, n);
                }
            }
        }

        return getResult(n);
    }

    private void installFloor(int x, int y, int n) { // 보 설치
        if (checkInstallFloor(x, y, n)) {
            floor[x][y] = 1;
        }

    }

    private void installPillar(int x, int y) { // 기둥 설치
        if (checkInstallPillar(x, y)) {
            pillar[x][y] = 1;
        }
    }

    private void removeFloor(int x, int y, int n) { // 보 삭제

        floor[x][y] = 0;

        if (!isStructorValid(n)) { // 구조물 유효성 검사
            floor[x][y] = 1;
            return;
        }
    }

    private void removePillar(int x, int y, int n) { // 기둥 삭제
        pillar[x][y] = 0;

        if (!isStructorValid(n)) { // 구조물 유효성 검사
            pillar[x][y] = 1;
            return;
        }
    }

    private boolean isStructorValid(int n) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y] == 1 && !checkInstallPillar(x, y)) { // 기둥에 대한 유효성이 옳은가?
                    return false;
                }

                if (floor[x][y] == 1 && !checkInstallFloor(x, y, n)) { // 보에 대한 유효성이 옳은가?
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkInstallPillar(int x, int y) {
        return y == 0 || // 바닥 위
                floor[x][y] == 1 || // 보의 한쪽 끝 부분
                (x > 0 && floor[x - 1][y] == 1) || // 보의 한쪽 끝 부분
                pillar[x][y - 1] == 1; // 다른 기둥 위
    }

    private boolean checkInstallFloor(int x, int y, int n) {
        return (y > 0 && pillar[x][y - 1] == 1) || // 기둥이 왼쪽에 존재
                (y > 0 && x <= n - 1 && pillar[x + 1][y - 1] == 1) || // 기둥이 오른쪽에 존재
                (x > 0 && x <= n - 1 && floor[x - 1][y] == 1 && floor[x + 1][y] == 1); // 양쪽 끝 부분이 보와 연결
    }

    private int[][] getResult(int n) {
        List<int[]> result = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y] == 1) {
                    result.add(new int[]{x, y, 0});
                }
                if (floor[x][y] == 1) {
                    result.add(new int[]{x, y, 1});
                }
            }
        }

        result.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0]; // x좌표 오름차순
            }
            if (a[1] != b[1]) {
                return a[1] - b[1]; // y좌표 오름차순
            }

            return a[2] - b[2]; // 기둥 먼저
        });

        return result.toArray(new int[result.size()][]);
    }
}
