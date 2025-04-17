package 프로그래머스.지형편집;

import java.util.*;

// 지형 편집 : 각 칸의 제일 위에 블록 1개를 새로 추가 or 삭제
// 블록 추가 or 삭제시 게임 머니를 사용해야 한다.
// 울퉁불퉁한 지형의 모든 칸 높이가 같아지도록 만든다
// 추가 : P, 제거 : Q

// 높이에 따라 추가 및 제거해야 하는 블록의 갯수가 달라진다.
// 해당 높이를 기준으로 추가 및 제거 해야 하는 블록의 갯수를 알 수 있다면? 해당 값은 누적이 된다?
// ex) 1층 추가 갯수, 제거 갯수 | 2층 추가 갯수, 제거 갯수 ...
// 이분 탐색으로 선택

// 높이를 기준으로 이분탐색? ok
// 추가량과 제거량을 기준으로 추가비용 더 높은 경우 높이 낮추고, 제거량 비용이 더 많은 경우 높이를 높여주면서 조절한다.

public class Solution {

    private int[][] Land;
    private int n;
    private int p;
    private int q;
    private long answer = Long.MAX_VALUE;

    public long solution(int[][] land, int P, int Q) {

        int minHeight = 0;
        int maxHeight = 1000000000;

        Land = land;
        n = land.length;
        p = P;
        q = Q;

        while(minHeight <= maxHeight) {
            int mid = (minHeight + maxHeight) / 2;

            long costMid = getCost(mid); // 현재 mid
            long costMidPlus = getCost(mid + 1); // mid + 1

            answer = Math.min(answer, Math.min(costMid, costMidPlus));

            if(costMid < costMidPlus) { // 비용이 더 작은 쪽을 기준으로 가야한다?
                maxHeight = mid - 1;
            } else {
                minHeight = mid + 1;
            }
        }

        return answer;
    }

    private long getCost(int targetHeight) { // 비용 계산
        long add = 0;
        long remove = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int cur = Land[i][j];
                if(cur < targetHeight) {
                    add += targetHeight - cur;
                }else {
                    remove += cur - targetHeight;
                }
            }
        }

        return add * p + remove * q;
    }
}
