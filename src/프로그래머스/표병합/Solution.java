package 프로그래머스.표병합;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private final String[][] map = new String[50][50];
    private final int[] parent = new int[2500];
    private List<String> answers = new ArrayList<>();

    public String[] solution(String[] commands) {

        for(int i = 0; i < 2500; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < 50; i++) {
            Arrays.fill(map[i], "EMPTY");
        }

        for(String command: commands) {

            String[] split = command.split(" ");
            String order = split[0];

            if(order.equals("UPDATE")) {
                if(split.length == 3) { // UPDATE A B
                    for(int i = 0; i < 50; i++) {
                        for(int j = 0; j < 50; j++) {
                            if(map[i][j].equals(split[1])) {
                                map[i][j] = split[2];
                            }
                        }
                    }
                }else { // UPDATE r c value -> union find
                    int r = Integer.parseInt(split[1]) - 1;
                    int c = Integer.parseInt(split[2]) - 1;

                    String value = split[3];
                    int root = find(r * 50 + c);

                    for(int i = 0; i < 2500; i++) {
                        if(root == parent[i]) {
                            int y = i / 50;
                            int x = i % 50;
                            map[y][x] = value;
                        }
                    }
                }
            }else if(order.equals("MERGE")) {
                int r1 = Integer.parseInt(split[1]) - 1;
                int c1 = Integer.parseInt(split[2]) - 1;
                int r2 = Integer.parseInt(split[3]) - 1;
                int c2 = Integer.parseInt(split[4]) - 1;

                int root1 = find(r1 * 50 + c1); // 하나의 그룹
                int root2 = find(r2 * 50 + c2); // 또다른 그룹

                union(root1, root2);
                int root = find(root1); // 묶어준 그룹 번호

                String value = "EMPTY"; // 기본값 중요
                if(map[r1][c1].equals("EMPTY") && !map[r2][c2].equals("EMPTY")) { // 둘 중 하나 값을 갖고 있는 경우
                    value = map[r2][c2]; // r2를 먼저 비교한 이유가 있었다!
                }else {
                    value = map[r1][c1];
                }

                // 둘다 값이 존재하는 경우
                for(int i = 0; i < 2500; i++) {
                    if(parent[i] == root1 || parent[i] == root2 || parent[i] == root) {
                        parent[i] = root;
                        int y = i / 50;
                        int x = i % 50;
                        map[y][x] = value; // r,c
                    }
                }
            }else if(order.equals("UNMERGE")) {
                int r = Integer.parseInt(split[1]) - 1;
                int c = Integer.parseInt(split[2]) - 1;
                String value = map[r][c];
                int root = find(r * 50 + c);
                for(int i = 0; i < 2500; i++) {
                    if(parent[i] == root) {
                        parent[i] = i;
                        int y = i / 50;
                        int x = i % 50;
                        map[y][x] = "EMPTY";
                    }
                }
                map[r][c] = value; // 마지막에
            }else{
                int r = Integer.parseInt(split[1]) - 1;
                int c = Integer.parseInt(split[2]) - 1;
                answers.add(map[r][c]);
            }
        }

        return answers.toArray(new String[0]);
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            if(x > y) {
                parent[y] = x;
            }else {
                parent[x] = y;
            }
        }
    }

    private int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}