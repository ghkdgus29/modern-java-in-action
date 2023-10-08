package chap9.template_method;

public abstract class OnlineBanking {

    public void processCustomer(int id) {               // 알고리즘 템플릿
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);                               // 유연하게 고치고 싶은 부분
    }

    protected abstract void makeCustomerHappy(Customer c);  // 유연하게 고치고 싶은 부분
}
