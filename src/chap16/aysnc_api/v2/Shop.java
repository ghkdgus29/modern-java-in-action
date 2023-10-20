package chap16.aysnc_api.v2;

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
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);      // 작업 도중 문제가 발생하면, 발생한 에러를 포함시켜 Future 를 종료
            }
        }).start();

        return futurePrice;
    }

    private double calculatePrice(String product) {
        delay();
        throw new RuntimeException("존재하지 않는 상품입니다.");       // 작업 도중 예외를 발생시키도록 코드 작성
//        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
