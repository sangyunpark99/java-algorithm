class Solution {
    
    static int[] Numbers;
    static int Target;
    //static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        Numbers = numbers;
        Target = target;
        
        // 더하거나 빼거나
        
        return dfs(0, 0);
    }
    
    private int dfs(int curValue, int curIdx) {
        
        int answer = 0;
        
        if(curIdx == Numbers.length) {
            
            if(curValue == Target) {
                answer++;        
            }
            
            return answer;
        }
        
        answer = dfs(curValue + Numbers[curIdx], curIdx+1) + dfs(curValue - Numbers[curIdx], curIdx+1);
        
        return answer;
    }
}