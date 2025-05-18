package 프로그래머스.거리두기확인하기;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> solution(String[][] places) {
        List<Boolean> check = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();

        for (String[] place : places) {
            char[][] map = new char[5][5];

            for (int i = 0; i < place.length; i++) {
                map[i] = place[i].toCharArray();
            }

            boolean valid = validate(map);
            check.add(valid);
        }

        for (Boolean b : check) {
            if (!b) {
                answer.add(0);
                continue;
            }

            answer.add(1);
        }

        return answer;
    }

    public boolean validate(char[][] map) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') {

                    int[] dy = new int[]{-1, 0, 1, 0};
                    int[] dx = new int[]{0, 1, 0, -1};

                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                            continue;
                        }

                        if (map[ny][nx] == 'P') {
                            return false;
                        }
                    }

                    dy = new int[]{-2, 0, 2, 0};
                    dx = new int[]{0, 2, 0, -2};

                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                            continue;
                        }

                        if (map[ny][nx] == 'P' && map[(i + ny) / 2][(j + nx) / 2] == 'O') {
                            return false;
                        }
                    }

                    dy = new int[]{-1, 1, 1, -1};
                    dx = new int[]{1, 1, -1, -1};

                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                            continue;
                        }

                        if (map[ny][nx] == 'P' && (map[i][nx] == 'O' || map[ny][j] == 'O')) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}

