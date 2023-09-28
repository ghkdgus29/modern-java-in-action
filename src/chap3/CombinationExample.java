package chap3;

import java.util.function.Function;
import java.util.function.Predicate;

public class CombinationExample {

    public static void main(String[] args) {
        Predicate<Apple> redApple = apple -> apple.getColor() == Color.RED;

        Predicate<Apple> notRedApple = redApple.negate();

        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);

        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150)
                .or(apple -> apple.getColor() == Color.GREEN);


        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        Function<Integer, Integer> h = f.andThen(g);    // f -> g
        System.out.println(h.apply(1));             // 4

        Function<Integer, Integer> i = f.compose(g);    // f(g(x)), g -> f
        System.out.println(i.apply(1));             // 3

    }
}
