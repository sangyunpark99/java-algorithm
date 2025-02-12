import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        
        // 1. 알파벳 변경 최소 횟수 계산
        for (char ch : name.toCharArray()) { // A부터 or Z부터
            answer += Math.min(ch - 'A', 'Z' - ch + 1);
        }
        
        // 2. 커서 이동 최소 횟수 계산
        int move = name.length() - 1;

        for(int i = 0; i < length; i++) {
            int next = i + 1;
            
            while(next < length && name.charAt(next) == 'A') {
                next++;
            }
            
            move = Math.min(move, i + length - next + Math.min(i, length - next));
        }
        
        answer += move;
        return answer;
    }
}