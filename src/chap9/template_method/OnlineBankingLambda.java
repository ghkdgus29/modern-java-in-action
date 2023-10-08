package chap9.template_method;

import java.util.function.Consumer;

public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {   // 알고리즘 템플릿
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);                               // 유연하게 고치고 싶은 부분
    }
}
