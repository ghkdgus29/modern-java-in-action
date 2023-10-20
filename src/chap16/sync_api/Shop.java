package chap16.sync_api;

import java.util.Random;

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

    public String getName() {
        return name;
    }

    private double calculatePrice(String product) {
        delay();        // 외부 API 요청에 의해 걸리는 작업시간 1초
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
