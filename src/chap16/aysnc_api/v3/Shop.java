package chap16.aysnc_api.v3;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static chap16.Util.delay;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));     // ForkJoinPool 의 Executor 중 하나가 작업을 수행한다.
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
