package chap5;

import chap4.Dish;
import chap4.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {
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

        Stream<Dish> vegetarianMenuStream = menu.stream()
                .filter(Dish::isVegetarian);
        vegetarianMenuStream.forEach(System.out::println);

        List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 4);
        Stream<Integer> distinctStream = numbers.stream()
                .distinct();
    }
}
