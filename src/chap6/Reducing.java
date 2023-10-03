package chap6;

import chap4.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

import static chap4.Dish.*;

public class Reducing {

    public static void main(String[] args) {
        System.out.println("요리 총 개수: " + calculateTotalCount());

        System.out.println("통계: " + calculateMenuStatistics());
        // 통계: IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}

        System.out.println(getShortMenu());
        // porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon

        System.out.println(getShortMenuCommaSeparated());
        // pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
    }

    private static long calculateTotalCount() {
        return menu.stream().collect(Collectors.counting());
    }

    private static Optional<Dish> findMostCaloricDish() {
        return menu.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(Collectors.summingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu() {
        return menu.stream().map(Dish::getName).collect(Collectors.joining());
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
    }

}
