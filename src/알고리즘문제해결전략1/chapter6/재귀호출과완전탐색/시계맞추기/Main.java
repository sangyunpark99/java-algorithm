package 알고리즘문제해결전략1.chapter6.재귀호출과완전탐색.시계맞추기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    // 연관된 시계를 짝지어 놓고
    // 어떤 스위치를 먼저 돌려야 할까? x 스위치를 누르는 순서는 중요하지 않다. 어떤 스위치를 몇번 누르는지만 중요하다.
    // 스위치는 3번이 최대 [0,1,2,3] 4번 스위치 10개 4^10이 최대 경우의 수

    static int[] clocks;
    static List[] switchs;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        switchs = new List[] {
                List.of(0,1,2),
                List.of(3,7,9,11),
                List.of(4,10,14,15),
                List.of(0,4,5,6,7),
                List.of(6,7,8,9,10,12),
                List.of(0,2,14,15),
                List.of(3,14,15),
                List.of(4,5,7,14,15),
                List.of(1,2,3,4,5),
                List.of(3,4,5,9,13),
        };

        while(t-- > 0) {
            clocks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = clocks.length;

            int answer = dfs(0,0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }

    // 스위치를 기준으로
    private static int dfs(int cur, int pushCnt) {
        if (cur == 10) { // 스위치는 총 10개이므로 인덱스 10이면 종료
            return isAll12() ? pushCnt : Integer.MAX_VALUE;
        }

        int ret = Integer.MAX_VALUE;

        for(int i = 0; i < 4; i++) { // 스위치는 각각 최대 3번까지 가능하다.
           turn(cur, i);// 시계 돌리기
           ret = Math.min(ret,dfs(cur + 1, pushCnt + i));
           turnOff(cur,i);// 돌렸던 시계 원상 복구하기
        }

        return ret;
    }

    private static boolean isAll12() {
        for(int i = 0; i < m; i++) {
            if(clocks[i] != 12) {
                return false;
            }
        }
        return true;
    }

    private static void turn(int num, int count) {
        List<Integer> switchList = switchs[num]; // 연관된 스위치
        for(int j = 0; j < switchList.size(); j++) { // 연관된 시계 시간 돌리기
            int idx = switchList.get(j);
            clocks[idx] = (clocks[idx] + 3 * count) % 12;
            if(clocks[idx] == 0) clocks[idx] = 12;
        }
    }

    private static void turnOff(int num, int count) {
        List<Integer> switchList = switchs[num]; // 연관된 스위치
        for(int j = 0; j < switchList.size(); j++) { // 연관된 시계 시간 돌리기
            int idx = switchList.get(j);
            clocks[idx] = (clocks[idx] - 3 * count + 12) % 12;
            if(clocks[idx] == 0) clocks[idx] = 12;
        }
    }
}
