package 프로그래머스.테이블해시함수;

import java.util.*;

class Solution {

    private List<List<Integer>> list = new ArrayList<>();

    public int solution(int[][] data, int col, int row_begin, int row_end) {

        for(int i = 0; i < data.length; i++) { // O(2500)
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < data[i].length; j++) { // O(500)
                tmp.add(data[i][j]);
            }
            list.add(tmp);
        }

        Collections.sort(list,(o1, o2) -> { // O(nlogn)
            if(o1.get(col - 1).equals(o2.get(col - 1))) {
                return Integer.compare(o2.get(0),o1.get(0));
            }

            return Integer.compare(o1.get(col - 1),o2.get(col - 1));
        });


        List<Integer> values = new ArrayList<>();

        for(int i = row_begin; i <= row_end; i++) { // O(2500)
            List<Integer> rows = list.get(i - 1);
            int sum = 0;
            for(int j = 0; j < rows.size(); j++) { // O(500)
                sum += rows.get(j) % i;
            }
            values.add(sum);
        }

        // XOR 연산
        int answer = 0;
        for(int i = 0; i < values.size(); i++) { // O(500)
            answer ^= values.get(i);
        }

        return answer;
    }
}