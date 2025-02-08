class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        // m구역에 최대한 많은 section이 존재하면 된다.
        
        // 시작 지점은 무조건 포함
        
        int start = section[0];
        int end = start + m;
        answer = 1;
        for(int i = 1; i < section.length; i++){
            if(section[i] <= end - 1) {
                continue;
            }
            
            answer++;
            start = section[i];
            end = start + m;
        }
        
        return answer;
    }
}