package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();

        List<Integer> a = List.of(1, 2, 3, 4, 5);
        List<Integer> b = List.of(2, 3, 4, 5, 6);

        list.add(a);
        list.add(b);
        System.out.println(list);
    }
}
