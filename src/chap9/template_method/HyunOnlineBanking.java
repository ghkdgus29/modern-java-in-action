package chap9.template_method;

public class HyunOnlineBanking extends OnlineBanking {

    @Override
    protected void makeCustomerHappy(Customer c) {
        System.out.println(c.getName() + "님, 현 은행에 오신걸 환영합니다");
    }
}
