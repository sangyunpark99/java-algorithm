import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int maxValue = Integer.MIN_VALUE;
        
        for(int i = 1; i < triangle.length; i++) {  // O(n) 500
            for(int j = 0; j < triangle[i].length; j++) { // O(n) 500
                if(j == 0) {
                    triangle[i][j] = triangle[i][j] + triangle[i-1][j];
                }else if(j == triangle[i].length - 1) {
                    triangle[i][j] = triangle[i][j] + triangle[i-1][j-1];
                }else {
                    triangle[i][j] = triangle[i][j] + Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
                
                maxValue = Math.max(maxValue, triangle[i][j]);
            }
        }
        
        return maxValue;
    }
}