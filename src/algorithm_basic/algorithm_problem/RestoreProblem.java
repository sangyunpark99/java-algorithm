//package algorithm_basic.algorithm_problem;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RestoreProblem {
//
//    private static int[][] map = {
//            {10,20,21},
//            {70,90,12},
//            {80,110,120}
//    };
//
//    private static int[][] visited = new int[3][3];
//
//    private static int[] dy = {-1,0,1,0};
//    private static int[] dx = {0,1,0,-1};
//
//    public static void main(String[] args) {
//
//        visited[0][0] = 1;
//        dfs(0,0, new ArrayList<>(List.of(map[0][0])));
//    }
//
//    private static void dfs(int y, int x, List<Integer> path) {
//
//        if(y == 2 && x == 2) {
//            System.out.println(path);
//            return;
//        }
//
//        for(int i = 0; i < 4; i++) {
//            int ny = y + dy[i];
//            int nx = x + dx[i];
//
//            if(ny < 0 || ny >= 3 || nx < 0 || nx >= 3) continue;
//            if(visited[ny][nx] == 0) {
//                visited[ny][nx] = 1;
//                path.add(map[ny][nx]);
//                dfs(ny, nx, path);
//                path.removeLast();
//                visited[ny][nx] = 0;
//            }
//        }
//    }
//}
