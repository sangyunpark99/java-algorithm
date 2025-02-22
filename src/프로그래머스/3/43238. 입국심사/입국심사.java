import java.util.*;

class Solution {

    public long solution(int n, int[] times) {
        long answer = 0;
        long start = 1;
        long end = 100_000L * 1_000_000_00;
        
        Arrays.sort(times);
        
        while(start <= end) { 
            long mid = (start + end) / 2;
            
            if(check(mid, times, n)) { // 크기 감소
                answer = mid;
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }

        return answer;
    }
        
    boolean check(long checkTime, int[] times, int n) { // O(n) = 100,000
        long cnt = 0;
        long cntTime = 0;
        
        for(int time: times) {
            cnt += checkTime / (long) time;
            
            if(cnt >= n) {
                return true;
            }
        }
        
        return false;
    }
}