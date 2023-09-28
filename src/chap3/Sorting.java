package chap3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        inventory.sort(Comparator.comparing(apple -> apple.getWeight()));

        inventory.sort(Comparator.comparing(Apple::getWeight));

        // [Apple{color=GREEN, weight=80}, Apple{color=RED, weight=120}, Apple{color=GREEN, weight=155}]
        System.out.println(inventory);

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        // [Apple{color=GREEN, weight=155}, Apple{color=RED, weight=120}, Apple{color=GREEN, weight=80}]
        System.out.println(inventory);

//        inventory.sort(Comparator.comparing(Apple::getWeight)
//                .reversed()                                         // 무게를 내림차순으로 정렬
//                .thenComparing(Apple::getName));                    // 두 사과의 무게가 같으면 이름순으로 오름차순 정렬
    }
}
