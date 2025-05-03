package 알고리즘문제해결전략1.chapter6.부분합.크리스마스인형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    // 어린이들에게 나눠주기 위해선 K의 배수여야 한다.
    // 0 1 2 3 4 5 -> 6/4
    // 0 0 1 2 3 4 -> 5/4
    // 0 0 0 1 2 3 -> 4/4
    // 구간합으로 K의 배수인지 확인

    private static int testCase;
    private static int[] dolls;
    private static int N;
    private static int K;
    private static int[] prefixSum;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 인형 상자
            K = Integer.parseInt(st.nextToken()); // 어린이의 수
            dolls = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(first());

            dp = new int[N + 1];
            Arrays.fill(dp, - 1);

            System.out.println(second(1));
        }
    }

    /***
     * (prefixSum[T] - prefixSum[H-1]) % K == 0
     * prefixSum[T] % K == prefixSum[H-1] % K
     * 누적합의 모든 경우를 K로 나눌때, 같은 같이 나오는 쌍을 찾는 것이 핵심
     * HashMap에 나머지별로 저장을하고, value로 조합의 갯수 nC2 계산
     */
    private static int first() { // O(N)으로 해결
        prefixSum = new int[N + 2];
        prefixSum[0] = 0;
        Map<Integer, Integer> modCount = new HashMap<>();

        modCount.put(0,1);

        for(int i = 1; i <= N; i++) { // O(N)
            prefixSum[i] = prefixSum[i - 1] + dolls[i - 1];
            int mod = prefixSum[i] % K;
            modCount.put(mod, modCount.getOrDefault(mod, 0) + 1);
        }

        int answer = 0;

        for(int cnt : modCount.values()) { // O(N)
            answer += (cnt * (cnt - 1)) / 2;
        }

        return answer;
    }

    /***
     * 서로 겹치지 않는 K의 배수인 구간합을 최대한 많이 선택하라
     * 매개변수 i : 현재 인덱스 i에서 시작해서 끝까지 탐색했을 때의 최대 주문 횟수
     * @return
     */
    private static int second(int i) {

        if(i > N) return 0;

        if(dp[i] != -1) return dp[i];

        int result = 0;

        result = second(i + 1); // 선택을 안한 경우

        for(int  j = i; j <= N; j++) { // 선택을 한경우
            int sum = prefixSum[j] - prefixSum[i - 1];
            if(sum % K == 0) { // 나눠떨어지는 경우
                result = Math.max(result, 1 + second(j + 1));// 1개 더하고 다음 범위 탐색
            }
        }

        return dp[i] = result;
    }
}