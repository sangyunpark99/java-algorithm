package 프로그래머스.몸짱트레이너의고민;

import java.util.*;

class Solution {

    public class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int solution(int n, int m, int[][] timetable) {

        int[] preSum = new int[721];

        for(int i = 0; i < m; i++) {
            preSum[timetable[i][0] - 600]++;
            preSum[timetable[i][1] - 600 + 1]--;
        }

        int sum = 0;
        int max = 0;

        for(int i = 0; i <= 720; i++) { // 가장 많이 겹치는 구간 찾기
            sum += preSum[i];
            preSum[i] = sum;
            max = Math.max(max, preSum[i]);
        }

        if(max <= 1) return 0;

        int left = 1; // 맨 처음 위치
        int right = 2 * (n - 1); // 대각선 길이
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(isPossible(mid, max, n)) {
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean isPossible(int dist, int go, int n) { // 현재 거리를 유지할 수 있는가?
        List<Point> list;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) { // 시작 지점
                list = new ArrayList<>();
                list.add(new Point(i,j));
                int cnt = 1; // 자기 자신
                for(int y = 0; y < n; y++) {
                    for(int x = 0; x < n; x++) {
                        if(y == i && x == j) continue;
                        boolean possible = true;
                        for(Point cur: list) { // 모든 지점을 기준으로 dist보다 크거나 같은 경우
                            if(Math.abs(cur.y - y) + Math.abs(cur.x - x) >= dist) continue;
                            possible = false;
                            break;
                        }

                        if(possible) {
                            if(++cnt == go) {
                                return true;
                            }
                            list.add(new Point(y, x));
                        }
                    }
                }
            }
        }

        return false;
    }
}