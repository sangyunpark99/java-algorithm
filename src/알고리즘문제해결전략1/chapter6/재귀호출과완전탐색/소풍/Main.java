package 알고리즘문제해결전략1.chapter6.재귀호출과완전탐색.소풍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 항상 서로 친구인 학생들끼리만 짝을 지어준다.
    // 해시맵으로 서로 친구인 경우 확인 O(1)
    // 둘씩 짝짓는 것은 완전 탐색으로 구현 - 조합

    // 아직 짝을 찾지 못한 학생들의 명단이 주어진 경우 둘씩 짝을 짓는 경우를 계산하라
    private static int n;
    private static int m;

    private static boolean[][] isFriend;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int c = Integer.parseInt(br.readLine());

        while(c-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            isFriend = new boolean[n][n];
            int[] friends = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int i = 0; i < friends.length; i+=2) { // 서로 친구 관계
                isFriend[friends[i]][friends[i+1]] = true; // 숫자 순서로
                isFriend[friends[i+1]][friends[i]] = true;
            }

            System.out.println(pair(new boolean[n]));
        }
    }

    // 짝을 찾지 못한 학생을 기준으로 짝을 찾아준다.
    // 짝을 찾은 경우엔, boolean으로 true 처리를 해준다.

    // 모든 사람이 전부 친구인 경우 9 x 7 x 5 x 3 x 1
    public static int pair(boolean[] taken) {

        int free = -1;

        for(int i = 0; i < n; i++) { // 짝을 차지 못한 학생
            if(!taken[i]) {
                free = i;
                break;
            }
        }

        if(free == -1) return 1; // 모든 학생이 짝을 찾은 경우

        int ret = 0; // 누적 정답

        for(int pairWith = free + 1; pairWith < n; pairWith++) {
            if(!taken[pairWith] && isFriend[free][pairWith]) { // 짝인 경우
                taken[free] = true;
                taken[pairWith] = true;
                ret += pair(taken);
                taken[free] = false; // 원상 복구
                taken[pairWith] = false;
            }
        }

        return ret;
    }
}