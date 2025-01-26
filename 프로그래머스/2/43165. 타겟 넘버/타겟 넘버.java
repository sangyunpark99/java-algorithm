class Solution {
    
    static int[] Numbers;
    static int Target;
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        Numbers = numbers;
        Target = target;
        
        // 더하거나 빼거나
        
        dfs(0, 0);
        
        return answer;
    }
    
    private void dfs(int curValue, int curIdx) {
        
        if(curIdx == Numbers.length) {
            
            if(curValue == Target) {
                answer++;        
            }
            
            return;
        }
        
        dfs(curValue + Numbers[curIdx], curIdx+1);
        dfs(curValue - Numbers[curIdx], curIdx+1);
    }
}