package chap9;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Peek {

    public static void main(String[] args) {
        List<Integer> result = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .peek(i -> System.out.println("origin stream: " + i))           // 소스의 처음 요소 출력
                .map(i -> i + 20)
                .peek(i -> System.out.println("after map: " + i))               // map 동작 후 요소 출력
                .filter(i -> i % 2 == 0)
                .peek(i -> System.out.println("after filter: " + i))            // filter 동작 후 요소 출력
                .limit(3)
                .peek(i -> System.out.println("after limit: " + i))             // limit 동작 후 요소 출력
                .collect(Collectors.toList());

        System.out.println(result);     // [22, 24, 26]
    }
}
