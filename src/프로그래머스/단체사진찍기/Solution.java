package 프로그래머스.단체사진찍기;

import java.util.*;

class Solution {
    // 순서 설정
    // 조건을 만족하는 순서의 경우의 수
    // 완전탐색 - 400만, 단순 순열 문제?
    private int cnt = 0;

    private HashMap<String, Integer> map = new HashMap<>();
    private String[] Data;

    public int solution(int n, String[] data) {

        Data = data.clone();
        List<String> names = List.of("A","C","F","J","M","N","R","T");

        int idx = 0;
        for(String name: names) {
            map.put(name, idx++);
        }

        return permu(0, new boolean[8], new int[8]);
    }

    private boolean operation(int first, int second, String oper, int diff) {

        int fsDiff = Math.abs(first - second) - 1;

        if("=".equals(oper) && Math.abs(fsDiff) == diff) {
            return true;
        } else if("<".equals(oper) && Math.abs(fsDiff) < diff) {
            return true;
        } else if(">".equals(oper) && Math.abs(fsDiff) > diff) {
            return true;
        }

        return false;
    }

    private int permu(int depth, boolean[] visited, int[] position) {

        int total = 0;

        if(depth == 8) {
            boolean isRight = true;
            for(String data: Data) {

                // split 사용 x ->
                String first = String.valueOf(data.charAt(0));
                String second = String.valueOf(data.charAt(2));
                String oper = String.valueOf(data.charAt(3));
                String diff = String.valueOf(data.charAt(4));

                // 병목 발생 부분 - indexOf 시간 복잡도가 너무 크다.
                int firstIdx = position[map.get(first)]; // O(1)
                int secondIdx = position[map.get(second)]; // O(1)

                if(!operation(firstIdx, secondIdx, oper, Integer.parseInt(diff))){
                    return 0;
                }
            }

            return 1;
        }

        for(int i = 0; i < 8; i++) {
            if(!visited[i]) {
                visited[i] = true;
                position[depth] = i;
                total += permu(depth + 1, visited, position);
                visited[i] = false;
                position[depth] = 0;
            }
        }

        return total;
    }
}