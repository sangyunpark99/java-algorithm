import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k, t, a, b;
    static int[] count = new int[301]; // 각 시간대의 사람 수 기록
    static int[][] dp; // DP 배열
    static List<Pair> v = new ArrayList<>(); // (구간 길이, 해당 구간의 인원 수)

    static int go(int currentIndex, int remaining, int previousCost) {
        if (currentIndex == v.size()) {
            return 0; // 모든 구간을 탐색했을 경우 종료
        }
        if (dp[currentIndex][remaining] != Integer.MIN_VALUE) {
            return dp[currentIndex][remaining]; // 메모이제이션 적용
        }

        int cost = Math.max(0, t - v.get(currentIndex).second); // 현재 구간에서 부족한 인원
        int realCost = Math.max(0, cost - previousCost); // 이전 구간에서 부족한 인원 보충 고려

        // 친구를 투입하지 않는 경우
        int result = go(currentIndex + 1, remaining, cost);

        // 친구를 투입할 수 있는 경우
        if (remaining - realCost >= 0) {
            result = Math.max(result, go(currentIndex + 1, remaining - realCost, cost) + v.get(currentIndex).first);
        }

        return dp[currentIndex][remaining] = result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 파티 총 시간
        m = Integer.parseInt(st.nextToken()); // 현재 파티 인원 수
        k = Integer.parseInt(st.nextToken()); // 영선이가 투입할 수 있는 친구 수
        t = Integer.parseInt(st.nextToken()); // 욱제가 필요한 최소 인원 수

        // 각 시간대별 사람 수 기록
        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            for (int i = a; i < b; i++) {
                count[i] = Math.min(t, ++count[i]); // 최대 T명까지만 가능
            }
        }

        // 연속된 같은 인원 그룹화
        int currentCount = count[1];
        int segmentCount = 1;
        for (int i = 2; i <= n; i++) {
            if (currentCount != count[i]) {
                v.add(new Pair(segmentCount, currentCount));
                segmentCount = 1; // ✅ **여기에서 1로 초기화 (0으로 하면 첫 번째 값이 손실될 수 있음)**
                currentCount = count[i];
            } else {
                segmentCount++;
            }
        }
        v.add(new Pair(segmentCount, currentCount)); // 마지막 구간 추가

        // DP 배열 초기화
        dp = new int[v.size()][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        // 최적의 결과 출력
        System.out.println(go(0, k, 0));
    }

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
