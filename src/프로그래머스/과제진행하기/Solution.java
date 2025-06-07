package 프로그래머스.과제진행하기;
import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0;

        Arrays.sort(plans, (o1, o2) -> o1[1].compareTo(o2[1]));

        Stack<Plan> stack = new Stack<>();

        for(int i = 0; i < plans.length; i++) {
            Plan now = new Plan(plans[i]);

            if(i < plans.length - 1) {
                Plan next = new Plan(plans[i + 1]);
                int availableTime = next.startTime - now.startTime;
                if(availableTime < now.playTime) {
                    now.playTime -= availableTime;
                    stack.push(now);
                }else {
                    answer[idx++] = now.name;
                    int remainingTime = availableTime - now.playTime;

                    while(!stack.isEmpty() && remainingTime > 0) {
                        Plan prev = stack.pop();
                        if(prev.playTime > remainingTime) {
                            prev.playTime -= remainingTime;
                            stack.push(prev);
                            break;
                        } else {
                            remainingTime -= prev.playTime;
                            answer[idx++] = prev.name;
                        }
                    }
                }
            } else {
                answer[idx++] = now.name;
            }
        }

        while(!stack.isEmpty()) {
            Plan prev = stack.pop();
            answer[idx++] = prev.name;
        }

        return answer;
    }

    public class Plan {

        String name;
        int startTime;
        int playTime;

        public Plan(String[] cur) {
            this.name = cur[0];
            this.startTime = getMinutes(cur[1]);
            this.playTime = Integer.parseInt(cur[2]);
        }
    }

    public int getMinutes(String time) {
        String[] splitValue = time.split(":");
        int hour = Integer.parseInt(splitValue[0]);
        int minute = Integer.parseInt(splitValue[1]);
        return 60 * hour + minute;
    }
}
