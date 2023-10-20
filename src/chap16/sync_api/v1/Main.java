package chap16.sync_api.v1;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Finder finder = new Finder();

        long start = System.nanoTime();

        List<String> result = finder.findPricesSequential("myPhone27S");

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("sequential done in " + duration + " msecs");    // sequential done in 5053 msecs

        System.out.println(result);
        // [BestPrice price is 123.26, LetsSaveBig price is 169.47, MyFavoriteShop price is 214.13, BuyItAll price is 184.74]
    }
}
