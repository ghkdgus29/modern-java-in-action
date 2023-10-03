package chap6;

import chap4.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static chap4.Dish.menu;

public class Partitioning {

    public static void main(String[] args) {
        System.out.println(partitionByVegetarian());
        // {false=[pork, beef, chicken, prawns, salmon],
        // true=[french fries, rice, season fruit, pizza]}

        System.out.println(mostCaloricPartitionedByVegetarian());
        // {false=pork, true=pizza}
    }

    private static Map<Boolean, List<Dish>> partitionByVegetarian() {
        return menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(Collectors.partitioningBy(
                Dish::isVegetarian,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get
                )
        ));
    }
}
