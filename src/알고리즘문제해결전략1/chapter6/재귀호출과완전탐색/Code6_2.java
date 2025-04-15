package 알고리즘문제해결전략1.chapter6.재귀호출과완전탐색;

import java.util.ArrayList;
import java.util.List;

public class Code6_2 {
    public static void main(String[] args) {
        System.out.println(sum(10));
        System.out.println(dfs(10));
        four(7);
        combi(0, 7, new ArrayList<>(), 4);
    }

    private static int sum(int n) {
        int ret = 0;
        for(int i = 1; i <= n; i++) {
            ret += i;
        }
        return ret;
    }

    private static int dfs(int n) {
        if(n == 1) return 1; // 기저 사례
        return n + dfs(n - 1); // 쪼개기
    }

    private static void four(int n) { // n개의 원소 중 4개를 고르는 모든 경우
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    for(int m = k + 1; m < n; m++) {
                        System.out.println("["+i+j+k+m+"]");
                    }
                }
            }
        }
        // 중첩 for문은 골라야 할 원소의 수가 늘어날수록 코드가 길고 복잡해지고, 골라야할 원소의 수가 입력에 따라 달라지는 경우엔 사용 x
        // 재귀 호출은 이를 간단하게 해준다.
    }


    // 반복문을 재귀 함수로 표현
    // 중첩 for문과 달리 n개의 원소 중에서 몇 개를 고르든지 사용할 수 있다는 장점이 존재합니다.
    // 재귀 호출을 이용하면 특정 조건을 만족하는 조합을 모두 생성하는 코드를 쉽게 작성할 수 있습니다.
    // 따라서, 재귀 호출은 완전 탐색을 구현할때 아주 유용한 도구입니다.
    private static void combi(int start, int n, List<Integer> list, int m) {
        if(list.size() == m) {
            System.out.println(list);
            return;
        }

        for(int i = start; i < n; i++) {
            list.add(i); // 마지막에 원소 추가
            combi(i + 1, n, list, m);
            list.remove(list.size() - 1); // 마지막에 추가한 원소 제거
        }
    }
}
