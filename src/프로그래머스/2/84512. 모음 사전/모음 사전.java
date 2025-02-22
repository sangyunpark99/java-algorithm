import java.util.*;

class Solution {
    
    private HashMap<String, Integer> indexs = new HashMap<>();
    private int order = 0;
    
    public int solution(String word) {
        dfs("");
        
        return indexs.get(word);
    }
    
    public void dfs(String cur) {
        
        if(cur.length() == 5) {
            return;
        }
        
        for(String next : new String[]{"A","E","I","O","U"}) {
            String nextValue = cur + next;
            if(!indexs.containsKey(nextValue)) {
                indexs.put(nextValue, ++order);
                dfs(nextValue);
            }
        }
    }
}