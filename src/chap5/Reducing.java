package chap5;

import chap4.Dish;
import chap4.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {

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

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Integer total = numbers.stream()
                .reduce(0, (sum, cur) -> sum + cur);

        System.out.println(total);

        Optional<Integer> maximum = numbers.stream()
                .reduce((max, cur) -> Integer.max(max, cur));

        Optional<Integer> minimum = numbers.stream()
                .reduce((min, cur) -> Integer.min(min, cur));


        List<Integer> empty = List.of();
        Optional<Integer> reduce = empty.stream()
                .reduce(Integer::max);

        System.out.println(maximum);
        System.out.println(minimum);
        System.out.println(reduce);
    }
}
