package chap9.factory;

import java.util.function.Supplier;

import static chap9.factory.FactoryMain.*;

public class ProductFactory {

    public static Product createProduct(String name) {
        switch (name) {
            case "loan" :
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }

    public static Product createProductLambda(String name) {
        Supplier<Product> p = map.get(name);
        if (p != null) {
            return p.get();
        }
        throw new RuntimeException("No such product " + name);
    }
}
