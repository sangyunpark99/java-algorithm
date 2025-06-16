package 프로그래머스.광물캐기;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int minFatigue = Integer.MAX_VALUE;
    private List<List<String>> groups = new ArrayList<>();

    public int solution(int[] picks, String[] minerals) {
        int totalPicks = (picks[0] + picks[1] + picks[2]) * 5; // 최대 채굴 가능한
        int len = Math.min(totalPicks, minerals.length); // 더 사용할 곡갱이가 없거나 모든 광물을 캘때까지

        for(int i = 0; i < len; i += 5) { // 5개 그룹 단위
            List<String> group = new ArrayList<>();
            for(int j = i; j < i + 5 && j < len; j++) {
                group.add(minerals[j]);
            }
            groups.add(group);
        }

        return dfs(0, picks);
    }

    private int dfs(int depth, int[] picks) { // 피로도

        if(depth == groups.size() || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++) {
            if(picks[i] > 0) {
                picks[i]--;
                int cost = getFatigue(i, groups.get(depth));
                min = Math.min(min, cost + dfs(depth + 1, picks));
                picks[i]++;
            }
        }

        return min;
    }

    private int getFatigue(int pick, List<String> group) {

        int sum = 0;

        for(String mineral : group) {
            if(pick == 0) sum += 1; // 다이아 곡갱이인 경우
            else if(pick == 1) { // 철 곡갱이인 경우
                if(mineral.equals("diamond")) sum += 5;
                else sum += 1;
            }else { // 돌 곡갱이인 경우
                if(mineral.equals("diamond")) sum += 25;
                else if(mineral.equals("iron")) sum += 5;
                else sum += 1;
            }
        }

        return sum;
    }
}
