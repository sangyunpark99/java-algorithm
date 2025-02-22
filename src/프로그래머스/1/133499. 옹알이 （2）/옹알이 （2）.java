import java.util.*;

class Solution { // 중복조합
    
    private Set<String> combi = new HashSet<>();
    private final String[] words = {"aya","ye","woo","ma"};
    
    public int solution(String[] babbling) {
        int answer = 0;
        
        combi("", 0);
        
        for (String bub : babbling) {
            if (combi.contains(bub)) {
                answer++;
            }
        }
        
        //System.out.println(combi);
        
        return answer;
    }
    

    private void combi(String current, int depth) {
        if (depth >= 1) {
            combi.add(current);
        }
        
        if (depth == 10) {
            return;
        }

        for (String word : words) {
            if (!current.endsWith(word)) {
               combi(current + word, depth + 1);
            }
        }
    }
}
