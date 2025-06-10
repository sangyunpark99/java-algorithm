package 프로그래머스.순위검색;

import java.util.*;

class Solution {

    private HashMap<String,List<Integer>> find = new HashMap<>();

    public int[] solution(String[] infos, String[] queries) {

        for(String info: infos) {
            String[] values = info.split(" ");
            String[] cond = Arrays.copyOfRange(values,0,4);
            int score = Integer.parseInt(values[4]);
            dfs(cond, 0, "", score);
        }

        for(List<Integer> scores: find.values()) {
            Collections.sort(scores);
        }

        int[] answer = new int[queries.length];
        int idx = 0;

        for(String query: queries) {

            String[] parts = query.replaceAll(" and ", " ").split(" ");
            String key = String.join("", Arrays.copyOfRange(parts,0,4));
            int findScore = Integer.parseInt(parts[4]);

            List<Integer> numbers = find.getOrDefault(key, new ArrayList<>());

            int left = 0;
            int right = numbers.size();

            while(left < right) {
                int mid = (left + right) / 2;
                if(numbers.get(mid) < findScore) {
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }

            answer[idx++] = numbers.size() - left;
        }

        return answer;
    }

    public void dfs(String[] field, int depth, String key, int score) {
        if(depth == field.length) {
            find.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }

        dfs(field, depth + 1, key + field[depth], score);
        dfs(field, depth + 1, key + "-", score);
    }
}
