package 프로그래머스.HIndex복습;

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        // 0 1 3 5 6 정렬이 된경우 이미 그 다음 값들은 이상이 된다.

        // citations[i] : 현재 논문의 갯수
        // citations.length - i : 현재 논문보다 인용횟수가 크거나 같은 논문의 편수
        for(int i = 0; i < citations.length; i++) {
            int value = Math.min(citations[i], citations.length - i);
            if(value >= answer){
                answer = value;
            }
        }

        return answer;
    }
}