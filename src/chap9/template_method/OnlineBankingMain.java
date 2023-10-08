package chap9.template_method;

public class OnlineBankingMain {

    public static void main(String[] args) {
        // old school
        HyunOnlineBanking hyunOnlineBanking = new HyunOnlineBanking();
        hyunOnlineBanking.processCustomer(1);       // yeon님, 현 은행에 오신걸 환영합니다

        YoonOnlineBanking yoonOnlineBanking = new YoonOnlineBanking();
        yoonOnlineBanking.processCustomer(1);       // yeon님, 윤 은행에 오신걸 환영합니다

        // labmda
        OnlineBankingLambda onlineBanking1 = new OnlineBankingLambda();
        onlineBanking1.processCustomer(1, (c) -> System.out.println(c.getName() + "님, 제1은행에 오신걸 환영합니다."));
        // yeon님, 제1은행에 오신걸 환영합니다.

        OnlineBankingLambda onlineBanking2 = new OnlineBankingLambda();
        onlineBanking2.processCustomer(1, (c) -> System.out.println(c.getName() + "님, 제2은행에 오신걸 환영합니다."));
        // yeon님, 제2은행에 오신걸 환영합니다.
    }
}
