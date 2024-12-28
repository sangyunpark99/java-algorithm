package 프로그래머스.lv1스킬트리;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        char[] link = skill.toCharArray(); // 인덱스 = 순서

        for (String value : skill_trees) {

            List<String> order = new ArrayList<>();

            for (char c : value.toCharArray()) {
                if (skill.contains(String.valueOf(c)) && !order.contains(String.valueOf(c))) {
                    order.add(String.valueOf(c));
                }
            }

            int checkIdx = 0;
            boolean check = true;
            for (int i = 0; i < order.size(); i++) {
                if (order.get(i).equals(String.valueOf(link[checkIdx]))) {
                    checkIdx++;
                    continue;
                }

                check = false;
                break;
            }

            if (check) {
                answer++;
            }
        }

        return answer;
    }
}
