package chap6;

import chap4.Dish;
import chap4.Type;

import java.util.*;
import java.util.stream.Collectors;

import static chap4.Dish.menu;

public class Grouping {

    public static void main(String[] args) {
        System.out.println(groupDishesByType());

        System.out.println(groupCaloricDishesByTypeUsingFilter());
        // {OTHER=[french fries, pizza], MEAT=[pork, beef]}

        System.out.println(groupCaloricDishesByType());
        // {OTHER=[french fries, pizza], MEAT=[pork, beef], FISH=[]}

        System.out.println(groupDishNamesByType());

        System.out.println(groupDishTagsByType());
        // {OTHER=[salty, greasy, natural, light, tasty, fresh, fried],
        // MEAT=[salty, greasy, roasted, fried, crisp],
        // FISH=[roasted, tasty, fresh, delicious]}

        System.out.println(groupDishByTypeAndCaloricLevel());
        // {
        // OTHER={NORMAL=[french fries, pizza], DIET=[rice, season fruit]},
        // MEAT={NORMAL=[beef], DIET=[chicken], FAT=[pork]},
        // FISH={NORMAL=[salmon], DIET=[prawns]}
        // }

        System.out.println(countDishesInGroups());
        // {OTHER=4, MEAT=3, FISH=2}

        System.out.println(mostCaloricDishesByType());
        // {OTHER=Optional[pizza], MEAT=Optional[pork], FISH=Optional[salmon]}

        System.out.println(mostCaloricDishesByTypeWithoutOptionals());
        // {OTHER=pizza, MEAT=pork, FISH=salmon}

        System.out.println(sumCaloriesByType());
        // {OTHER=1550, MEAT=1900, FISH=850}

        System.out.println(caloricLevelsByType());
        // {OTHER=[NORMAL, DIET], MEAT=[NORMAL, FAT, DIET], FISH=[NORMAL, DIET]}

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        ArrayList<Integer> collect = numbers.stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Map<Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCalorie() {
        return menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
    }

    private static Map<Type, List<Dish>> groupCaloricDishesByTypeUsingFilter() {
        return menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(Collectors.groupingBy(Dish::getType));
    }

    private static Map<Type, List<Dish>> groupCaloricDishesByType() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())
        ));
    }

    private static Map<Type, List<String>> groupDishNamesByType() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.mapping(Dish::getName, Collectors.toList())  // List<Dish> -> List<String>
        ));
    }

    private static Map<Type, Set<String>> groupDishTagsByType() {
        Map<String, List<String>> dishTags = Map.of(
                "pork", List.of("greasy", "salty"),
                "beef", List.of("salty", "roasted"),
                "chicken", List.of("fried", "crisp"),
                "french fries", List.of("greasy", "fried"),
                "rice", List.of("light", "natural"),
                "season fruit", List.of("fresh", "natural"),
                "pizza", List.of("tasty", "salty"),
                "prawns", List.of("tasty", "roasted"),
                "salmon", List.of("delicious", "fresh"));

        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.flatMapping(dish -> dishTags.get(dish.getName()).stream(), Collectors.toSet())
        ));
    }

    private static Map<Type, Map<CaloricLevel, List<Dish>>> groupDishByTypeAndCaloricLevel() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,                                  // 첫 번째 수준의 분류 함수
                Collectors.groupingBy(dish -> {                 // 두 번재 수준의 분류 함수
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        ));
    }

    private static Map<Type, Long> countDishesInGroups() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.counting()
        ));
    }

    private static Map<Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
        ));
    }

    private static Map<Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.collectingAndThen(       // 컬렉터가 반환한 결과를 변환한다.
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get
                )
        ));
    }

    private static Map<Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.summingInt(Dish::getCalories)
        ));
    }

    private static Map<Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.mapping(dish -> {                            // Dish -> CaloricLevel 로 변환
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        },
                        Collectors.toSet())
        ));
    }
}
