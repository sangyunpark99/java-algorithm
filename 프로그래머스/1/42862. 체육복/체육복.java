import java.util.*;

class Solution {
    // 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있다.
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        // 1. 학생이 갖고 있는 체육복 갯수를 알아야 한다.
        
        int[] cloths = new int[n + 1]; // 6
        
        Arrays.fill(cloths,1); // 1개
        cloths[0] = 0;// 1 ~ n
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        int j = 0;
        int k = 0;
        for(int i = 1; i <= n; i++) { // O(30)
            if(k < reserve.length && i == reserve[k]) {
                cloths[i]++;
                k++;
            }
        }
        
        for(int i = 1; i <= n; i++) {
            if(j < lost.length && i == lost[j]) {
                cloths[i]--;
                j++;
            }
        }
        
        for(int i = 1; i <= n; i++) {
            if(cloths[i] == 0) {
                if(cloths[i-1] == 2) {
                    cloths[i]++;
                    cloths[i-1]--;
                }else if(i < n && cloths[i+1] == 2) {
                    cloths[i]++;
                    cloths[i+1]--;
                }
            }
            
            if(cloths[i] >= 1) {
              answer++;   
            }
        }
        
        System.out.println(Arrays.toString(cloths));
        return answer;
    }
}