import java.util.*;

class Solution {
    
    private HashMap<String, Node> persons = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        persons.put("minho", new Node()); // 루트
        
        for(int i = 0; i < enroll.length; i++) { // O(n)
            String person = enroll[i];
            persons.put(person, new Node());
        }
        
        for(int i = 0; i < referral.length; i++) {
            String name = enroll[i]; // 자기 자신
            String refer = referral[i]; // 부모
            
            Node child = persons.get(name);
            
            if("-".equals(refer)) {
                Node root = persons.get("minho");
                root.children.add(persons.get(name));
                child.parent = root;
                continue;
            }
            
            Node parent = persons.get(refer);
            parent.children.add(persons.get(name));
            child.parent = parent;
        }
        
        for(int i = 0; i < seller.length; i++) {
            String person = seller[i];
            int money = amount[i] * 100;
            Node start = persons.get(person);
            
            while(start.parent != null) {
                int tenPercentMoney = money / 10; // 10% -> 어차피 0원이 된다.
                start.money += money - tenPercentMoney; // 10% 제외한 금액 +
                start = start.parent; // 부모 노드로 이동
                money = tenPercentMoney; // 10% 제외한 금액
            }
            // 부모 찾아 올라가기
        }
        
        for(int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            int money = persons.get(name).money;
            answer.add(money);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static class Node {
        public Node parent;
        public List<Node> children;
        public int money;
        
        public Node() {
            this.money = 0;
            this.children = new ArrayList<>();
        }
        
        @Override
        public String toString() {
        return "Node{" +
                "money=" + money +
                ", childrenCount=" + (children != null ? children.size() : 0) +
                '}';
        }
    }
}