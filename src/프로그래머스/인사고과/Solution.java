package 프로그래머스.인사고과;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    private List<int[]> members = new ArrayList<>();

    // 완호인지 아닌지 확인

    public int solution(int[][] scores) {
        int answer = 0;

        // 사원 추가
        for (int i = 0; i < scores.length; i++) {
            members.add(scores[i]);
        }

        // 1. 인센티브를 받을 수 있는 사원 선별
        // 근무 태도, 동료 평가 비교
        // O(n2) 은 불가능 -> O(n)으로 판단?
        // 두 점수가 모두 낮은 경우가 한개라도 존재하는 경우

        // nlogn = 100,000 * log(100,000)
        Collections.sort(members, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        // O(n) = 100,000
        int max = Integer.MIN_VALUE;
        int grade = members.get(members.size() - 1)[0]; // 제일 큰 값
        for (int i = members.size() - 1; i >= 0; i--) {
            if (i != members.size() - 1) {
                if (max > members.get(i)[1] && members.get(i)[0] < grade) {
                    if (members.get(i)[0] == scores[0][0] && members.get(i)[1] == scores[0][1]) { // 완호인경우
                        return -1;
                    }
                    members.remove(i);
                }
            }

            max = Math.max(max, members.get(i)[1]);
        }

        // 2. 두 점수의 합 순서대로 석차 매기기
        // O(n) = log(100,000)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 최댓값 정렬

        // O(n) = 100,000
        for (int i = 0; i < members.size(); i++) {
            pq.offer(members.get(i)[0] + members.get(i)[1]);
        }

        int wanHoSum = scores[0][0] + scores[0][1];

        int order = 0;
        int score = 200_001; // 최대 20만
        int a = 1;
        // O(n) = 100,000
        while (!pq.isEmpty()) {
            int curScore = pq.poll();

            if (curScore < score) {
                order += a;
                a = 1;
            } else if (curScore == score) {
                a++;
            }

            score = curScore;

            if (wanHoSum == curScore) {
                answer = order;
                break;
            }
        }

        // 동석차인 경우 고려해서 석차
        return answer;
    }
}