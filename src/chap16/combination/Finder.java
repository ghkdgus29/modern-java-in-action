package chap16.combination;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Finder {

    private final List<Shop> shops = List.of(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy")
    );

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);             // 프로그램 종료시 실행중인 스레드가 종료되도록 데몬스레드로 설정
        return thread;
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))            // shop 객체 -> 가격과 상품정보 (문자열), 1초가 걸리는 작업
                .map(priceStr -> Quote.parse(priceStr))         // 가격 문자열 -> quote 객체
                .map(quote -> Discount.applyDiscount(quote))    // quote 객체 -> 가격 (double), 1초가 걸리는 작업
                .collect(Collectors.toList());
    }

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(         // shop 객체 -> 가격과 상품정보 (문자열)을 계산하는 작업들 생성
                                () -> shop.getPrice(product),
                                executor
                        )
                )
                .map(future -> future.thenApply(priceStr -> Quote.parse(priceStr))) // 가격과 상품정보 (문자열) 작업이 완료되면 -> 결과물로 quote 객체 생성
                .map(future -> future.thenCompose(quote ->                          // quote 객체 결과물 생성이 완료되면 -> 결과물을 가지고 할인가격 (double)
                                CompletableFuture.supplyAsync(                      //                                  을 계산하는 작업들 생성
                                        () -> Discount.applyDiscount(quote),
                                        executor
                                )
                        )
                )
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public void findPricesAccept(String product) {
        long start = System.nanoTime();

        CompletableFuture[] futures = findPricesStream(product)
                .map(future -> future.thenAccept(
                        priceTag -> System.out.println(priceTag + " done in " + (System.nanoTime() - start) / 1_000_000 + " msecs")
                ))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();

        System.out.println("All shops have now responded in " + (System.nanoTime() - start) / 1_000_000 + " msecs");
    }

    public Future<Double> findPriceInUSD(String product) {
        chap16.aysnc_api.v3.Shop shop = new chap16.aysnc_api.v3.Shop("BestMart");

        // 가격 계산, 1초 소요
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> shop.getPrice(product));

        // 환율 계산, 1초 소요
        CompletableFuture<Double> futureRate = CompletableFuture.supplyAsync(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD));

        // 가격 계산 작업과 환율 계산 작업이 끝나면 두 결과물을 가지고 작업 수행
        return futurePrice.thenCombine(futureRate, (price, rate) -> price * rate);
    }

    private Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(         // shop 객체 -> 가격과 상품정보 (문자열)을 계산하는 작업들 생성
                                () -> shop.getPriceRandom(product),
                                executor
                        )
                )
                .map(future -> future.thenApply(priceStr -> Quote.parse(priceStr))) // 가격과 상품정보 (문자열) 작업이 완료되면 -> 결과물로 quote 객체 생성
                .map(future -> future.thenCompose(quote ->                          // quote 객체 결과물 생성이 완료되면 -> 결과물을 가지고 할인가격 (double)
                                CompletableFuture.supplyAsync(                      //                                  을 계산하는 작업들 생성
                                        () -> Discount.applyDiscount(quote),
                                        executor
                                )
                        )
                );
    }
}
