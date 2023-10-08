package chap9.template_method;

public class YoonOnlineBanking extends OnlineBanking {

    @Override
    protected void makeCustomerHappy(Customer c) {
        System.out.println(c.getName() + "님, 윤 은행에 오신걸 환영합니다");
    }
}
