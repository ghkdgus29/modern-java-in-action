package chap5;

import chap4.Dish;
import chap4.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Slicing {
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

        Stream<Dish> slicedMenu1 = menu.stream()
                .takeWhile(dish -> dish.getCalories() < 320);

        Stream<Dish> slicedMenu2 = menu.stream()
                .dropWhile(dish -> dish.getCalories() < 320);

        Stream<Dish> limitedMenu = menu.stream()
                .limit(3);

        Stream<Dish> skippedMenu = menu.stream()
                .skip(3);
    }
}
