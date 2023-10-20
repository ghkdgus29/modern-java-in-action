package chap16.combination;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Finder finder = new Finder();

        benchmark("sequential", () -> finder.findPricesSequential("myPhone27S"));
        benchmark("composed CompletableFuture", () -> finder.findPrices("myPhone27S"));

//        long start = System.nanoTime();
//        System.out.println(finder.findPriceInUSD("myPhone27S").get());
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("future combines done in " + duration + " msecs");

        finder.findPricesAccept("myPhone27S");


    }

    private static void benchmark(String msg, Supplier<List<String>> supplier) {
        long start = System.nanoTime();

        System.out.println(supplier.get());

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
