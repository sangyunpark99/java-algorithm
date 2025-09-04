import java.util.*;

class Solution {
    
    private List<List<String>> values = new ArrayList<>();
    private int bannedSize = 0;
    private Set<String> answer = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        bannedSize = banned_id.length;
        
        for(int i = 0; i < banned_id.length; i++) { // O(8)
            values.add(new ArrayList<>());
            
            for(int j = 0; j < user_id.length; j++) { // O(8)
                if(check(banned_id[i], user_id[j])) { // 불량 사용자라면
                    values.get(i).add(user_id[j]);
                }
            }
        }
        
        combi(0, new ArrayList<>());
        
        return answer.size();
    }
    
    private void combi(int depth, List<String> check) {
        if(depth == bannedSize) {
            Collections.sort(check); // 정렬 해서 문자열을 만드는게 핵심
            String joinValue = "";
            for(int i = 0; i < check.size(); i++) {
                String value = check.get(i);
                joinValue += value;
            }
            answer.add(joinValue);            
            return;
        }
        
        for(int i = 0; i < values.get(depth).size(); i++) {
            String value = values.get(depth).get(i);
            if(check.contains(value)) continue;
            check.add(value);
            combi(depth + 1, check);
            check.remove(value);
        }
    }
    
    private boolean check(String banned, String user) { // O(64)
        
        char[] bannedList = banned.toCharArray();
        char[] userList = user.toCharArray();
        
        if(bannedList.length != userList.length) return false;
        
        for(int i = 0; i < bannedList.length; i++) {
            if(bannedList[i] == '*') continue;
            if(bannedList[i] != userList[i]) { // 일치하지 않는 경우
                return false;
            }
        }
        
        return true;
    }
}