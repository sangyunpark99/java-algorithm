package algorithm_basic;

public class BruteForce {

    private static int[] numbers = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
        bruteForceWithRecur(0);
    }

    public static void bruteForceWithFor() {
        for(int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

    public static void bruteForceWithWhile() {

        int i = 0;

        while(i < 10) {
            System.out.print(numbers[i] + " ");
            i++;
        }
    }

    public static void bruteForceWithRecur(int i) {
        if(i == numbers.length) {
            return;
        }

        System.out.print(numbers[i] + " ");
        bruteForceWithRecur(++i);
    }
}
