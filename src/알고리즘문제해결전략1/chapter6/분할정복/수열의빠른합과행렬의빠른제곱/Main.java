package 알고리즘문제해결전략1.chapter6.분할정복.수열의빠른합과행렬의빠른제곱;

public class Main {
    public static void main(String[] args) {

        long startTime = System.nanoTime();
        System.out.println(recursive(10000));
        long endTime = System.nanoTime();
        System.out.println("recursive : " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        System.out.println(divideConquer(10000));
        endTime = System.nanoTime();
        System.out.println("divideConquer : " + (endTime - startTime) + " ns");
    }

    private static int recursive(int n) {
        if (n == 0) return 0;
        return recursive(n - 1) + n;
    }

    private static int divideConquer(int n) {
        if(n == 1) return 1;
        if(n % 2 == 1) return divideConquer(n-1) + n;
        return 2*divideConquer(n/2) + (n/2)*(n/2);
    }
}
