package 프로그래머스.유사칸토어비트열;

class Solution {

    public int solution(int n, long l, long r) {
        int answer = 0;

        int totalSize = (int) Math.pow(5, n);
        int part = totalSize / 5;

        // 0-based

        return (int) dfs(n, l - 1, r - 1);
    }

    private long dfs(int n, long l, long r) {
        if(n == 0) {
            return 1;
        }

        long result = 0;
        long len = (long) Math.pow(5, n-1);

        for(int i = 0; i < 5; i++) {
            long start = len * i;
            long end = len * (i + 1) - 1;

            if(start > r || end < l) {
                continue;
            }

            if(i == 2) {
                continue;
            }

            long overlapL = Math.max(l, start) - start;
            long overlapR = Math.min(r, end) - start;
            result += dfs(n - 1, overlapL, overlapR);
        }

        return result;
    }
}