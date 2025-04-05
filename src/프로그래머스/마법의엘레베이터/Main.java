package 프로그래머스.마법의엘레베이터;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(16));
    }

    public static int solution(int storey) {
        // storey를 문자열로 변환하여 자릿수를 구함.
        String s = Integer.toString(storey);
        int n = s.length();

        // 각 자릿수를 일의 자리부터 배열에 저장
        // 예: storey = 4567이면 digits = {7, 6, 5, 4}
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = s.charAt(n - 1 - i) - '0';
        }

        // dp[i][carry] : i번째 자리부터 최상위 자리까지 처리할 때,
        // 현재 carry 상태(0 또는 1)를 고려한 최소 비용
        int[][] dp = new int[n + 1][2];

        // 베이스 케이스: 모든 자릿수를 처리한 후
        dp[n][0] = 0;  // carry가 없는 경우 추가 비용 없음
        dp[n][1] = 1;  // carry가 있는 경우, 추가적으로 올림 한 번(비용 1)이 필요

        // i번째 자리에서부터 최상위 자리까지, 낮은 자리부터 처리
        for (int i = n - 1; i >= 0; i--) {
            for (int carry = 0; carry < 2; carry++) {
                int cur = digits[i] + carry;  // 현재 자리 실제 값
                // 옵션 1: 내리기 -> cur 만큼 버튼(-1)을 눌러 0으로 만들기
                int costDown = cur + dp[i + 1][0];
                // 옵션 2: 올리기 -> (10 - cur) 만큼 버튼(+1)을 눌러 10으로 만들고 올림 발생
                int costUp = (10 - cur) + dp[i + 1][1];

                dp[i][carry] = Math.min(costDown, costUp);
            }
        }

        return dp[0][0];
    }
}
