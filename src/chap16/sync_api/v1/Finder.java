package chap16.sync_api.v1;

import chap16.sync_api.Shop;

import java.util.List;
import java.util.stream.Collectors;

public class Finder {

    private final List<Shop> shops = List.of(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );

    public List<String> findPricesSequential(String product) {      // 4개의 shop 에 대해서 1초씩 걸리는 동기 메서드 호출
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }
}
