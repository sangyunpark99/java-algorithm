class Solution {
    
    static int[] Numbers;
    static int Target;
    
    public int solution(int[] numbers, int target) {
        
        Numbers = numbers;
        Target = target;

        return dfs(0, 0);
    }
    
    private int dfs(int curValue, int curIdx) {
        
        if(curIdx == Numbers.length) {
            
            if(curValue == Target) {
                return 1;        
            }
            
            return 0;
        }
        
        return dfs(curValue + Numbers[curIdx], curIdx+1) + dfs(curValue - Numbers[curIdx], curIdx+1);
    }
}