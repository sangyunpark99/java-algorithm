package 알고리즘설명;

public class FenwickTree {
    private static int[] tree;
    private static int size;

    public static void update(int index, int value) {
        while (index <= size) {
            tree[index] += value;  // 현재 위치에 값 추가
            index += index & -index; // LSB(가장 낮은 비트) 만큼 이동
        }
    }

    public static int sum(int index) {
        int result = 0;
        while (index > 0) {
            result += tree[index];
            index -= index & -index;
        }
        return result;
    }

    public static int rangeQuery(int left, int right) {
        return sum(right) - sum(left - 1);
    }

    public static void main(String[] args) {
        int[] data = {3, 4, 10, 11}; // 1-based index 사용
        int n = data.length;

        tree = new int[n + 1];
        size = n;

        for (int i = 0; i < n; i++) {
            update(i + 1, data[i]);
        }

        System.out.println("1 ~ 4 sum: " + rangeQuery(1, 4));
        System.out.println("2 ~ 3 sum: " +rangeQuery(2, 3));

        update(2, 5); // 2번 인덱스에 값 5더하기

        System.out.println("1 ~ 4 sum: " + rangeQuery(1, 4)); // 3 + 9 + 10 + 11 = 33
        System.out.println("2 ~ 3 sum: " + rangeQuery(2, 3)); // 9 + 10 = 19
    }
}
