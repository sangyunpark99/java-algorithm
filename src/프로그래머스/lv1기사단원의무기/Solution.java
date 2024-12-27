package 프로그래머스.lv1기사단원의무기;

public class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;

        // 철의 무게
        for (int i = 1; i <= number; i++) { // O(n)
            int attack = getAttack(i);

            if (attack > limit) {
                answer += power;
                continue;
            }

            answer += attack;
        }

        return answer;
    }

    public int getAttack(int num) {

        int attack = 0;

        for (int i = 1; i * i <= num; i++) {
            if (i * i == num) {
                attack++;
            } else if (num % i == 0) {
                attack += 2;
            }
        }

        return attack;
    }
}
