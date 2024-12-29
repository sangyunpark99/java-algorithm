package 프로그래머스.lv2오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public String[] solution(String[] record) {

        HashMap<String, String> userNicknames = new HashMap<>();
        List<String[]> messages = new ArrayList<>(); // 순서 유지

        for (String log : record) { // userId를 변수 -> 변하는 수
            String[] values = log.split(" ");
            String command = values[0];
            String userId = values[1];

            // 변하는 문자열은 닉네임
            // 변하지 않는 문자열은 id
            // 변하지 않는 문자열과 기록을 저장해두고, 출력할때 최종 닉네임만

            if ("Enter".equals(command)) {
                String nickname = values[2];
                userNicknames.put(userId, nickname); // 닉네임 업데이트
                messages.add(new String[]{userId, "님이 들어왔습니다."});
            } else if ("Leave".equals(command)) {
                messages.add(new String[]{userId, "님이 나갔습니다."});
            } else if ("Change".equals(command)) {
                String nickname = values[2];
                userNicknames.put(userId, nickname); // 닉네임 변경
            }
        }

        String[] answer = new String[messages.size()];

        for (int i = 0; i < answer.length; i++) {
            String[] message = messages.get(i);
            String id = message[0];
            String action = message[1];

            answer[i] = userNicknames.get(id) + action;
        }

        return answer;
    }
}
