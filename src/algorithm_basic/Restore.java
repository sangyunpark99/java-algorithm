//package algorithm_basic;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Restore {
//
//    private static List<List<Integer>> graph = new ArrayList<>();
//    private static boolean[] visited = new boolean[4];
//    private static String[] alphabet = {"A","C","D","E"};
//
//    public static void main(String[] args) {
//
//        for(int i = 0; i < 4; i++){
//            graph.add(new ArrayList<>());
//        }
//
//        graph.get(0).add(1);
//        graph.get(1).addAll(List.of(2,3));
//
//        visited[0] = true;
//        dfs(0, new ArrayList<>(List.of(0)));
//    }
//
//    public static void dfs(int curNode, List<Integer> list) {
//
//        if(list.size() == 3) {
//            convert(list);
//            System.out.println();
//            return;
//        }
//
//        for(int i = 0; i < graph.get(curNode).size(); i++) {
//            int nextNode = graph.get(curNode).get(i);
//            if(!visited[nextNode]) {
//                visited[nextNode] = true;
//                list.add(nextNode);
//                dfs(nextNode, list);
//                list.removeLast();
//                visited[nextNode] = false;
//            }
//        }
//    }
//
//    private static void convert(List<Integer> list) {
//        for(int i = 0 ; i < list.size(); i++) {
//            System.out.print(alphabet[list.get(i)] + " ");
//        }
//    }
//}