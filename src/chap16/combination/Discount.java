package chap16.combination;

import static chap16.Util.delay;
import static chap16.Util.format;

public class Discount {

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.getPercentage()) / 100);
    }
}
