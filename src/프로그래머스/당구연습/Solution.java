package 프로그래머스.당구연습;

public class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for(int i = 0; i < balls.length; i++) {
            int ballX = balls[i][0];
            int ballY = balls[i][1];
            int minDistance = Integer.MAX_VALUE;

            int[][] symmetries = new int[][] { // 대칭 이동
                    {-ballX, ballY}, // 왼
                    {2 * m - ballX, ballY}, // 오
                    {ballX, 2 * n - ballY}, // 위
                    {ballX, -ballY} // 아래
            };

            for(int[] symmetry: symmetries) {

                int mx = symmetry[0];
                int my = symmetry[1];

                // 공을 직접 맞추는 경우 제외
                if(startX == ballX && (startY > ballY && my < ballY)) continue;
                if(startX == ballX && (startY < ballY && my > ballY)) continue;
                if(startY == ballY && (startX > ballX && mx < ballX)) continue;
                if(startY == ballY && (startX < ballX && mx > ballX)) continue;

                int dist = getDist(startX, startY, mx, my);
                minDistance = Math.min(minDistance, dist);
            }

            answer[i] = minDistance;
        }

        return answer;
    }

    private int getDist(int ax, int ay, int bx, int by) {
        return (int) (Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
    }
}
