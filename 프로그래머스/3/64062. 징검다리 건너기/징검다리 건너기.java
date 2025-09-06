import java.util.*;

class Solution {
    
    private int[] stonesCopy;
    private int K;
    private int answer;
    
    public int solution(int[] stones, int k) {
        
        stonesCopy = stones;
        K = k;
        binarySearch(); // O(nlogn)
        
        return answer;
    }
    
    private void binarySearch() { // O(logn)
        
        int left = 1;
        int right = 200_000_000;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(check(mid)) {
                left = mid + 1;
                answer = mid;
            }else {
                right = mid - 1;
            }
        }
    }
    
    private boolean check(int value) { // 현재 인원 통과 가능 한가?
        
        // 밟을 수 없는 징검다리의 갯수가 몇개인가?
        int cnt = 0;
        for(int stone : stonesCopy) {
            if(stone < value) { // 밟을 수 없는 징검다리
                cnt++;
                if(cnt >= K) return false;
            }else {
                cnt = 0;
            }
        }
        
        return true;
    } 
}