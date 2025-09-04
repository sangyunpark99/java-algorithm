import java.util.*;

class Solution {
    
    private HashMap<String, Integer> map = new HashMap<>();
    private HashSet<String> kinds = new HashSet<>();
    private List<int[]> answers = new ArrayList<>();
    
    public int[] solution(String[] gems) {
        int[] answer = new int[]{0, gems.length - 1};
        answers.add(answer);
        // gems 배열의 크기는 1이상 -> 1일 수 있다.
        
        int left = 0;
        int right = 0;
        
        // 상품 
        for(String gem: gems) {
            kinds.add(gem);
        }
    
        while(left <= right && right < gems.length) { // O(n)
            
            if(map.size() != kinds.size()) { // 종류별로 존재하지 않는 경우
                
                String value = gems[right];
                
                if(map.containsKey(value)) { // 이미 포함한 경우
                    map.put(value, map.get(value) + 1);
                } else {
                     map.put(value,1);
                }
                
                right++;
                
            } else { // 종류별로 존재하는 경우
                
                answers.add(new int[]{left, right - 1});
                
                String value = gems[left];
                // 제거 하고 범위 줄이기
                if(map.get(value) == 1) {
                    map.remove(value);
                }else {
                    map.put(value, map.get(value) - 1);
                }
                
                left++;
            }
            
        }
        
        while(map.size() == kinds.size()) {
            answers.add(new int[]{left, right - 1});
            String value = gems[left];
            if(map.get(value) == 1) {
                map.remove(value);
            }else {
                map.put(value, map.get(value) - 1);
            }
                
            left++;
        }
        
        Collections.sort(answers, (a, b) -> { // O(nlogn)
           int gapA = a[1] - a[0];
           int gapB = b[1] - b[0];
           if(gapA == gapB) {
               return Integer.compare(a[0], b[0]);
           }
            
            return Integer.compare(gapA, gapB);
        });
        
        int[] result = answers.get(0);
        
        return new int[]{result[0] + 1, result[1] + 1};
    }
}