import java.util.*;

class Solution {
    // 양수, 음수, 양수 -> 부호가 번갈아
    
    private int[] first;
    private int[] second;
    
    public long solution(int[] sequence) {
        long answer = 0;
        
        first = sequence.clone(); // O(n)
        second = sequence.clone(); // O(n)
        
        boolean check = false;
        long[] firstValue = new long[sequence.length];
        long firstMaxValue = Integer.MIN_VALUE;
        long[] secondValue = new long[sequence.length];
        long secondMaxValue = Integer.MIN_VALUE;
        for(int i = 0; i < first.length; i++) { // O(n)
            first[i] *= check ? 1: -1; // -1 먼저
            second[i] *= !check ? 1 : -1; // 1 먼저
            check = !check;
            if(i == 0) {
                firstValue[i] = first[i];
                secondValue[i] = second[i];
            }
            if(i >= 1) {
                firstValue[i] = Math.max(first[i], firstValue[i - 1] + first[i]);
                secondValue[i] = Math.max(second[i], secondValue[i - 1] + second[i]);    
            }
            
            firstMaxValue = Math.max(firstMaxValue, firstValue[i]);
            secondMaxValue = Math.max(secondMaxValue, secondValue[i]);
        }
        
        return Math.max(firstMaxValue, secondMaxValue);
    }
}