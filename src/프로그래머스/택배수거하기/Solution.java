package 프로그래머스.택배수거하기;

import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dIdx = n - 1;
        int pIdx = n - 1;

        while(true) {
            while(dIdx >= 0 && deliveries[dIdx] == 0) {
                dIdx--;
            }

            while(pIdx >= 0 && pickups[pIdx] == 0) {
                pIdx--;
            }

            if(dIdx < 0 && pIdx < 0) break;

            int farDistance = Math.max(dIdx, pIdx) + 1;
            answer += (long)farDistance * 2;

            int capRemain = cap;
            for(int i = dIdx; i >= 0; i--) {
                if(capRemain > 0 && deliveries[i] > 0) {
                    int take = Math.min(capRemain, deliveries[i]);
                    deliveries[i] -= take;
                    capRemain -= take;
                    if(capRemain == 0) {
                        break;
                    }
                }
            }

            int capRemainPickup = cap;

            for(int i = pIdx; i >= 0; i--) {
                if(capRemainPickup > 0 && pickups[i] > 0) {
                    int take = Math.min(capRemainPickup, pickups[i]);
                    pickups[i] -= take;
                    capRemainPickup -= take;
                    if(capRemainPickup == 0) {
                        break;
                    }
                }
            }
        }

        return answer;
    }
}

