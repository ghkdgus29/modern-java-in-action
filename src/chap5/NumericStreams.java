package chap5;

import chap4.Dish;
import chap4.Type;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {

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

//        menu.stream()
//                .map(Dish::getCalories)         // Stream<Integer>
//                .sum();                         // 컴파일 에러

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)    // Stream<Integer> -> IntStream
                .sum();

        OptionalInt min = menu.stream()
                .mapToInt(Dish::getCalories)
                .min();

        System.out.println(min);

        IntStream.range(1, 5)
                .forEach(System.out::print);    // 1234

        System.out.println();

        IntStream.rangeClosed(1, 5)
                .forEach(System.out::print);    // 12345

        System.out.println();

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.range(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


        Stream<int[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.range(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                )
                .map(array -> Arrays.stream(array).mapToInt(a -> (int) a).toArray());
        pythagoreanTriples2.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }
}
