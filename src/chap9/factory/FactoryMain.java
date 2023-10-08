package chap9.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryMain {

    public static final Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static void main(String[] args) {
        Product p = ProductFactory.createProduct("loan");
        System.out.println(p);      // chap9.factory.Loan@41629346

        Product p2 = ProductFactory.createProductLambda("loan");
        System.out.println(p2);     // chap9.factory.Loan@448139f0
    }
}
