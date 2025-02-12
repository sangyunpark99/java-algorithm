import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        
        // 1. 알파벳 변경 최소 횟수 계산
        for (char ch : name.toCharArray()) {
            answer += Math.min(ch - 'A', 'Z' - ch + 1);
        }
        
        // 2. 커서 이동 최소 횟수 계산
        int move = len - 1; // 기본적으로 오른쪽으로 쭉 가는 경우
        for (int i = 0; i < len; i++) {
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }
            
            // 현재 위치(i)에서 되돌아가는 경우 고려
            move = Math.min(move, i + len - next + Math.min(i, len - next));
        }
        
        answer += move;
        return answer;
    }
}