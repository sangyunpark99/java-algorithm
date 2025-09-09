import java.util.*;

class Solution {
    
    private int[] timeValue;
    private long answer = 0;
    
    public long solution(int n, int[] times) {
        
        timeValue = times.clone();
        binarySearch(n);
        
        return answer;
    }
    
    private void binarySearch(int n) { // O(logN)
        long left = 1;
        long right = (long) Arrays.stream(timeValue).min().getAsInt() * n;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            if(check(mid, n, timeValue)) { // 최소 범위로 줄인다.
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        
        answer = left;
    }
    
    private boolean check(long time, long n, int[] times) { // 시간 내에 가능여부 확인
        long value = 0;
        
        for(int i = 0; i < timeValue.length; i++) {
            value += time / timeValue[i];
        }
        
        return value >= n;
    }
}