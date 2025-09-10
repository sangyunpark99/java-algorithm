class Solution
{
    
    private int answer = 1;
    
    public int solution(String s)
    {
        char[] arr = s.toCharArray();
        
        int n = s.length();
        if(n == 1) return 1;
        
        for(int i = 0; i < arr.length; i++) { // 기준
            
            int acnt = 1;      
            int left = i - 1;
            int right = i + 1;
            while(left >= 0 && right < arr.length && arr[left] == arr[right]) {
                acnt += 2;
                left--;
                right++;
            }
            
            answer = Math.max(answer, acnt);
            
            int bcnt = 0;
            left = i;
            right = i + 1;
            while(left >= 0 && right < arr.length && arr[left] == arr[right]) {
                bcnt += 2;
                left--;
                right++;
            }
            
            answer = Math.max(answer, bcnt);
            // 짝수개
        }

        return answer;
    }
}