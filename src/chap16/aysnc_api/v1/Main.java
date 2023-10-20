package chap16.aysnc_api.v1;

import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");     // 작업 완료여부와 상관없이 비동기 메서드는 바로 future 를 반환한다.
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        doSomethingElse();      // 현재 스레드 작업

        try {
            double price = futurePrice.get();               // 작업이 완료되지 않았다면, 완료되어 결과를 반환하기까지 블로킹된다.
            System.out.printf("Price is %.2f\n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
        System.out.println("현재 스레드에서 다른 작업 중 ...");
    }
}
