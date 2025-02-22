import java.util.*;

class Solution {
    public int solution(String[] arr) {
        
        int n = arr.length / 2 + 1; // 숫자 갯수
        
        int[][] dp_max = new int[n][n];
        int[][] dp_min = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            dp_max[i][i] = Integer.parseInt(arr[i*2]);
            dp_min[i][i] = Integer.parseInt(arr[i*2]);
        }
        
        for(int oplen = 1; oplen < n; oplen++) { // 연산자 갯수
            for(int i = 0; i < n - oplen; i++) { // i번째(시작지점)에 올 수 있는 수
                
                int j = i + oplen; // 범위
                
                // i ~ j까지
                dp_max[i][j] = Integer.MIN_VALUE;
                dp_min[i][j] = Integer.MAX_VALUE;
                
                for(int k = i; k < j; k++) { // 정해준 구간부터
                    String op = arr[2 * k + 1];
                    
                    int max1 = calc(dp_max[i][k],dp_max[k+1][j],op);
                    int max2 = calc(dp_max[i][k],dp_min[k+1][j],op);
                    int max3 = calc(dp_min[i][k],dp_max[k+1][j],op);
                    int max4 = calc(dp_min[i][k],dp_min[k+1][j],op);
                    
                    dp_max[i][j] = Math.max(dp_max[i][j],Math.max(Math.max(max1,max2), Math.max(max3,max4)));
                    dp_min[i][j] = Math.min(dp_min[i][j],Math.min(Math.min(max1,max2), Math.min(max3,max4)));
                }
            }
        }
        
        return dp_max[0][n-1];
    }
    
    private int calc(int a, int b, String op) {
        return op.equals("+") ? a + b : a - b;
    }
}
