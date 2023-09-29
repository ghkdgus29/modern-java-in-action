package chap5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class BuildingStreams {

    public static void main(String[] args) throws IOException {

        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<Object> empty = Stream.empty();

        int[] numbers = {2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.stream(numbers).sum());

        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::print);    // 02468

        System.out.println();

        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        Stream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::print);    // 11111

        System.out.println();

        long uniqueWords = Files.lines(Paths.get("./src/chap5/data.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();

        System.out.println("There are " + uniqueWords + " unique words in data.txt");
    }
}
