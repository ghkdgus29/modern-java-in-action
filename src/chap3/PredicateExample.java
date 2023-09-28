package chap3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {

    public static void main(String[] args) {
        List<String> strings = List.of("", "a", "b", "", "c");

        List<String> nonEmptyStrings = filter(strings, s -> !s.isEmpty());
        System.out.println(nonEmptyStrings);            // "a", "b", "c"
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {                // 전달받은 람다를 실행
                results.add(t);
            }
        }
        return results;
    }
}
