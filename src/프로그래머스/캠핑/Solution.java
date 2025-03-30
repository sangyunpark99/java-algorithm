package 프로그래머스.캠핑;

import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {

        // 1. 정렬
        Arrays.sort(data,(o1,o2)->{
            if(o1[0]==o2[0]) return o1[1]-o2[1];
            return o1[0]-o2[0];
        });

        int ans = 0;
        //2. 사용할수 있는 쐐기 구하기
        for(int i=0; i<data.length; i++) {

            for(int j=i+1; j<data.length; j++) {
                if(data[i][0] == data[j][0] || data[i][1]==data[j][1]) continue;

                boolean check = true;
                for(int k=i+1; k<j; k++) {
                    if((data[i][0]<data[k][0] &&
                            data[k][0] <data[j][0]) &&
                            Math.min(data[i][1], data[j][1])< data[k][1] &&
                            Math.max(data[i][1], data[j][1]) > data[k][1]) {
                        check= false;
                        break;
                    }
                }

                if(check) {
                    ans++;
                }

            }
        }
        return ans;
    }
}
