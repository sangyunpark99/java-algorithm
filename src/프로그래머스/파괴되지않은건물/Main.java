package 프로그래머스.파괴되지않은건물;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[][]{
                        {5, 5, 5, 5, 5},
                        {5, 5, 5, 5, 5},
                        {5, 5, 5, 5, 5},
                        {5, 5, 5, 5, 5}
                },
                new int[][]{
                        {1, 0, 0, 3, 4, 4},
                        {1, 2, 0, 2, 3, 2},
                        {2, 1, 0, 3, 1, 2},
                        {1, 0, 1, 3, 3, 1},
                }
        ));
    }
}
