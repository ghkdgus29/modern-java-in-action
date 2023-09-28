package chap3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {
        List<Integer> mapped = map(
                List.of("lambdas", "in", "actions"),
                s -> s.length()
        );

        System.out.println(mapped);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));             // 전달받은 람다를 실행
        }
        return result;
    }
}
