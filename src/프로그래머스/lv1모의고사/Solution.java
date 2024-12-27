package 프로그래머스.lv1모의고사;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};

        int[] first = new int[]{
                1, 2, 3, 4, 5
        };

        int firstAnswer = 0;

        int[] second = new int[]{
                2, 1, 2, 3, 2, 4, 2, 5
        };

        int secondAnswer = 0;

        int[] third = new int[]{
                3, 3, 1, 1, 2, 2, 4, 4, 5, 5
        };

        int thirdAnswer = 0;

        for (int i = 0; i < answers.length; i++) {
            if (first[i % first.length] == answers[i]) { // 길이를 넘는 경우
                firstAnswer++;
            }

            if (second[i % second.length] == answers[i]) {
                secondAnswer++;
            }

            if (third[i % third.length] == answers[i]) {
                thirdAnswer++;
            }
        }

        // 가장 문제를 많이 맞힌 사람
        int maxAnswer = Math.max(firstAnswer, Math.max(secondAnswer, thirdAnswer));

        List<Integer> result = new ArrayList<>();

        int idx = 1;
        for (int value : new int[]{firstAnswer, secondAnswer, thirdAnswer}) {
            if (maxAnswer == value) {
                result.add(idx);
            }

            idx++;
        }

        answer = result.stream().mapToInt(o -> o).toArray();

        return answer;
    }
}
