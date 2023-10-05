package chap8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CreatingCollections {

    public static void main(String[] args) {

        List<Integer> ele = new ArrayList<>();
        ele.add(1);
        ele.add(2);
        List<List<Integer>> list = List.of(ele);

        System.out.println(list);

        ele.add(3);

        System.out.println(list);

        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        System.out.println(integers);

        Map<Integer, Integer> integerIntegerMap = Map.of(1, 1, 2, 2, 3, 1);
        System.out.println(integerIntegerMap);
    }
}
