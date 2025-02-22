import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] instructions;
    private static int[][][] dp;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        instructions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[5][5][instructions.length + 1];

        n = instructions.length;

        for (int i = 0; i < dp.length; i++) { // 최소 힘 0
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(solve(0, 0, 0));
    }

    private static int check(int from, int to) { // 두 지점 사이의 거리
        if (from == 0) {
            return 2; // 맨처음
        }
        if (from == to) {
            return 1; // 같은 위치인 경우
        }
        if (Math.abs(from - to) == 2) {
            return 4; // 반대편인 경우
        }
        return 3; // 인접한 지점으로 움직인 경우
    }

    public static int solve(int y, int x, int target) { // dp는 index가 많이 들어간다.
        if (target == n - 1) { // 마지막
            return 0;
        }

        if (dp[y][x][target] != -1) {
            return dp[y][x][target];
        }

        // 왼쪽을 이동한 경우 : 기존 왼쪽 좌표와 움직일 좌표 거리
        int left = solve(instructions[target], x, target + 1) + check(y, instructions[target]);
        // 오른쪽을 이동한 경우 : 기존 오른족 좌표와 움직일 좌표 거리
        int right = solve(y, instructions[target], target + 1) + check(x, instructions[target]);

        return dp[y][x][target] = Math.min(left, right); // 최솟값 return
    }
}