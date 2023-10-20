package chap16.aysnc_api.v1;

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

    public double getPrice(String product) {            // 동기 메서드
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {       // 비동기 메서드
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread(() -> {      // 외부 스레드에서 작업 수행
            double price = calculatePrice(product);
            futurePrice.complete(price);            // 작업이 완료되면 CompletableFuture 의 작업을 완료시키고,
            // 작업의 결과물을 price로 설정한다.
        }).start();

        return futurePrice;     // 실제 결과값이 아니라 future 를 반환
    }

    private double calculatePrice(String product) {
        delay();        // 외부 API 요청에 의해 걸리는 작업시간 1초
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
