package chap16.combination;

import java.util.Random;

import static chap16.Util.delay;
import static chap16.Util.randomDelay;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {            // 동기 메서드, 1초의 일정한 작업시간
        double price = calculatePrice(product);
        Code code = Code.values()[random.nextInt(Code.values().length)];

        return String.format("%s:%.2f:%s", name, price, code);
    }

    public String getPriceRandom(String product) {            // 동기 메서드, 0.5초 ~ 2.5초의 랜덤 작업시간
        double price = calculatePriceRandomTime(product);
        Code code = Code.values()[random.nextInt(Code.values().length)];

        return String.format("%s:%.2f:%s", name, price, code);
    }

    public String getName() {
        return name;
    }

    private double calculatePrice(String product) {
        delay();        // 외부 API 요청에 의해 걸리는 작업시간 1초
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private double calculatePriceRandomTime(String product) {
        randomDelay();        // 외부 API 요청에 의해 걸리는 작업시간 0.5초 ~ 2.5초
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
