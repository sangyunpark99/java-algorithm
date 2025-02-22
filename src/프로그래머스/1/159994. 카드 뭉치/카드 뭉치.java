import java.util.*;

class Solution {
    
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";

        int cards1Idx = 0;
        int cards2Idx = 0;
        for (String word : goal) {
            
            if(!cards1[cards1Idx].equals(word) && !cards2[cards2Idx].equals(word)) {
                answer = "No";
                break;
            }
            
            if (cards1[cards1Idx].equals(word) && cards1Idx < cards1.length - 1) { // 1번 카드에 있는지 확인
                cards1Idx++;
                continue;
            }

            if (cards2[cards2Idx].equals(word) && cards2Idx < cards2.length - 1) { // 2번 카드에 있는지 확인
                cards2Idx++;
                continue;
            }
        }

        return answer;
    }
}