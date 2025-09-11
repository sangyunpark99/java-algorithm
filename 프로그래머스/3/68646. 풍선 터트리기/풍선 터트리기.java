import java.util.*;

class Solution {
    
    private int[] prefixMin;
    private int[] suffixMin;
    
    // 최솟값 비교
    public int solution(int[] a) {
        
        int answer = 0;
        
        int n = a.length;
        
        prefixMin = new int[n];
        suffixMin = new int[n];
        
        prefixMin[0] = a[0];
        for(int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(a[i], prefixMin[i - 1]);
        }
        
        suffixMin[n - 1] = a[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(a[i], suffixMin[i + 1]);
        }
        
        for(int i = 0; i < a.length; i++) {
            if(i == 0 || i == n - 1) { // 양쪽 끝 무조건 가능
                answer++;
                continue;
            }
            
            // 왼쪽 이라면 최소인 경우 가능 -> 
            if(a[i] <= prefixMin[i - 1] || a[i] <= suffixMin[i + 1]) {
                answer++;
            }
        }
        
        return answer;
    }
}