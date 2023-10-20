package chap16.sync_api.v3;

import chap16.sync_api.Shop;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Finder {

    private final List<Shop> shops = List.of(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"),
            new Shop("ShopEasy1"),
            new Shop("ShopEasy2"),
            new Shop("ShopEasy3"),
            new Shop("ShopEasy4")
    );

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);             // 프로그램 종료시 실행중인 스레드가 종료되도록 데몬스레드로 설정
        return thread;
    });

    public List<String> findPricesFuture(String product) {      // CompletableFuture 리스트로 만들고, CompletableFuture리스트의 모든 작업 결과를 가져와 반환한다.
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)),
                                executor                                                                            // 스레드 풀 설정
                        )
                )
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map((completableFuture) -> completableFuture.join())       // future.get() 과 유사하나, 예외를 발생시키지 않는다.
                .collect(Collectors.toList());
    }


}
