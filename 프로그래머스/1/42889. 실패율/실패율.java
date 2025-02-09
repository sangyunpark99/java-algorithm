import java.util.*;

class Solution {
    // N+1 마지막 스테이지까지 클리어 한 사용자
    // 실패율 같은 경우 작은 번호 스테이지 먼저
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
                
        int[] person = new int[N+2]; // 스테이지에 도달한 플레이어의 수
        int[] stay = new int[N+2]; // 스테이지에 도달했으나 아직 클리어하지 못한 플레이어수
        for(int stage : stages) {
            stay[stage]++;
            for(int i = 1; i <= stage; i++) {
                person[i]++;
            }
        }
        
        // System.out.println(Arrays.toString(person));
        // System.out.println(Arrays.toString(stay));
        
        // 1단계부터
        List<Node> fails = new ArrayList<>();
        for(int i = 1; i <= N; i++) { // 실패율
            double failv = 0;
            if(person[i] != 0) {
             failv = (double)stay[i] / person[i];   
            }
            fails.add(new Node(i,failv));           
        }
        
        Collections.sort(fails, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if(Double.compare(a.value,b.value) == 0) {
                    return a.index - b.index;
                }
                
                return Double.compare(b.value, a.value);
            }
        });
        
        for(int i = 0; i < N; i++) {
            answer[i] = fails.get(i).index;
        }
        
        return answer;
    }
    
    public class Node {
        int index;
        double value;
        
        public Node(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }
}