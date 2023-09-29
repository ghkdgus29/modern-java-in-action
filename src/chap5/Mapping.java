package chap5;

import chap4.Dish;
import chap4.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapping {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 400, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );
        Stream<Integer> mappedNameLength = menu.stream()
                .map(Dish::getName)
                .map(String::length);

        List<String> words = List.of("Hello", "world");

        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        List<String> wanted = words.stream()
                .map(word -> word.split(""))
                .flatMap(array -> Arrays.stream(array)) // 각 배열을 각 스트림으로 변환하고, 하나의 스트림으로 합친다.
                .distinct()
                .collect(Collectors.toList());

        System.out.println(wanted);

        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5);
        List<Integer> numbers2 = List.of(6, 7, 8);

        List<Stream<List<Integer>>> collect1 = numbers1.stream()
                .map(i -> numbers2.stream()
                        .map(j -> List.of(i, j))
                )
                .collect(Collectors.toList());

        collect1.forEach(s -> {
            s.forEach(System.out::print);
            System.out.println();
        });

        List<List<Integer>> collect2 = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> List.of(i, j))
                )
                .collect(Collectors.toList());

        collect2.forEach(System.out::print);
    }
}
