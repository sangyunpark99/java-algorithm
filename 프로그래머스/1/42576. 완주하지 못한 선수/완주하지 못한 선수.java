import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        String answer = "";
        HashMap<String, Integer> runner = new HashMap<>();
        
         // 완주한 사람을 HashMap에 저장
        for (String c : completion) {
            runner.put(c, runner.getOrDefault(c, 0) + 1);
        }

        // 참가자를 검사하면서 완주하지 못한 사람 찾기
        for (String p : participant) {
            if (!runner.containsKey(p) || runner.get(p) == 0) {
                return p; // 완주하지 못한 사람 반환
            }
            runner.put(p, runner.get(p) - 1);
        }
        
        return answer;
    }
}