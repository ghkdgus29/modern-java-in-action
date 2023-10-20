package chap16.sync_api.v2;

import chap16.sync_api.Shop;

import java.util.List;
import java.util.stream.Collectors;

public class Finder {

    private final List<Shop> shops = List.of(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy")
    );

    public List<String> findPricesParallel(String product) {    // 5개의 shop을 병렬로 나눠 1초씩 걸리는 동기 메서드를 호출
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }
}
