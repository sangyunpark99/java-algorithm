package 알고리즘문제해결전략1.chapter6.분할정복.쿼드트리뒤집기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0) {

            String[] arr = br.readLine().split("");
            idx = 0;
            System.out.println(dfs(arr));
        }
    }

    // index를 전역으로 처리
    private static String dfs(String[] arr) {

        String value = arr[idx++];

        if("w".equals(value) || "b".equals(value)) { // x가 아닌 경우 - 리프 노드
            return value;
        }

        // x인 경우
        String leftTop = dfs(arr);
        String rightTop = dfs(arr);
        String leftBottom = dfs(arr);
        String rightBottom = dfs(arr);

        return "x" + leftBottom + rightBottom + leftTop + rightTop;
    }
}
