import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        for(char c : name.toCharArray()) {
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }
        
        int length = name.length();
        int move = length - 1;
        
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