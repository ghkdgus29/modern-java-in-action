package chap5;

import chap4.Dish;
import chap4.Type;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class Searching {

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

        Optional<Dish> any = menu.stream()
                .findAny();

        Optional<Dish> first = menu.stream()
                .findFirst();

    }
}
