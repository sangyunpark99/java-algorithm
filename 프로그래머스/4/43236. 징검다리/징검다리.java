import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
    
        Arrays.sort(rocks);
        int start = 1;
        int end = distance;
        
        while(start <= end) {
            int mid = (start + end) / 2;
            
            // 돌 사이의 거리가 mid 이상으로 만들려면 몇개의 돌 제거?
            int removeRockCnt = 0;
            int prevRockPosition = 0;
            for(int rock: rocks) {
                if(rock - prevRockPosition < mid) {
                    removeRockCnt++;
                }else {
                    prevRockPosition = rock;
                }
            }
            
            if(distance - prevRockPosition < mid) {
                removeRockCnt++;
            }
            
            if(removeRockCnt <= n) { // 제거해야할 돌의 갯수가 더 작거나 같은경우
                answer = mid;
                start = mid + 1;
            }else { // 제거해야할 돌의 갯수가 더 많은 경우
                end = mid - 1;
            }
        }
        
        return answer;
    }
}