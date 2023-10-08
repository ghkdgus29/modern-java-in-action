package chap9.template_method;

import java.util.Map;

public class Database {

    private static final Map<Integer, String> DB = Map.of(1, "yeon", 2, "ayaan");

    public static Customer getCustomerWithId(int id) {
        return new Customer(DB.get(id));
    }
}
