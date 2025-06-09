package 프로그래머스.양궁대회;

class Solution {
    private int[] info;
    private int[] answer;
    private int bestDiff;

    public int[] solution(int n, int[] info) {
        this.info = info;
        answer = new int[]{-1};
        bestDiff = 0;

        dfs(0, n, new int[11]);
        return answer;
    }

    private void dfs(int idx, int remain, int[] arrows) {
        if (idx == 10) {
            arrows[10] = remain;
            evaluate(arrows);
            return;
        }

        for (int cnt = 0; cnt <= remain; cnt++) { // 덮여씌우기 이므로 초기화 x
            arrows[idx] = cnt;
            dfs(idx + 1, remain - cnt, arrows);
        }
    }

    private void evaluate(int[] arrows) {
        int ryanScore = 0;
        int apeachScore = 0;
        for (int i = 0; i <= 10; i++) {
            if (arrows[i] > info[i]) {
                ryanScore += 10 - i;
            } else if (info[i] > 0) {
                apeachScore += 10 - i;
            }
        }
        int diff = ryanScore - apeachScore;
        if (diff <= 0) return;

        if (diff > bestDiff) { // 현재 차이가 더 큰 경우
            bestDiff = diff; // 최고 차이 갱신
            answer = arrows.clone(); // 정답 갱신
        } else if (diff == bestDiff) { // 현재 차이가 동일한 경우
            for (int i = 10; i >= 0; i--) { // index 뒤에서부터 탐색
                if (arrows[i] != answer[i]) { // 정답이랑 다른 경우
                    if (arrows[i] > answer[i]) { // 현재 배열이 더 낮은 점수(뒤쪽 인덱스)에서 화살을 더 쏜 경우
                        answer = arrows.clone(); // 정답 갱신
                    }
                    break;
                }
            }
        }
    }
}
