package 백준.양과늑대;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                new int[][]{
                        {0, 1},
                        {1, 2},
                        {1, 4},
                        {0, 8},
                        {8, 7},
                        {9, 10},
                        {9, 11},
                        {4, 3},
                        {6, 5},
                        {4, 6},
                        {8, 9}
                }
        ));
    }
}
