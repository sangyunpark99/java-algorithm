import java.util.*;

class Solution {
    
    List<int[]> incentives = new ArrayList<>();
    
    public int solution(int[][] scores) {
        int answer = -1;
        
        int rootA = scores[0][0];
        int rootB = scores[0][1];
        
        Arrays.sort(scores, (a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1]; // 오름차순 정렬
            }
            
            return b[0] - a[0];
        });
        
        int bfValue = Integer.MIN_VALUE;
        for(int i = 0; i < scores.length; i++) {
            int value = scores[i][1];
            if(bfValue > value) {
                continue;
            }
            bfValue = value;
            incentives.add(new int[]{scores[i][0], scores[i][1]});
        }
        
        Collections.sort(incentives, (a, b) -> {
            return (b[0] + b[1]) - (a[0] + a[1]);
        });
        
        int grade = 1;
        int score = incentives.get(0)[0] + incentives.get(0)[1];
        int gap = 0;
        for(int i = 0; i < incentives.size(); i++) {
            int[] incentive = incentives.get(i);
            int nextScore = incentive[0] + incentive[1];
            if(score > nextScore) { // 
                grade += gap; // gap 인원 더하기
                score = nextScore; // 다음 점수로 초기화
                gap = 1; // gap 인원 초기ㅗ하
            }else if(score == nextScore) {
                gap++;
            }
            
            if(incentive[0] == rootA && incentive[1] == rootB) {
                return grade;
            }
        }
        
        return answer;
    }
}