package chap11.optional;


import java.util.Optional;

public class Car {

    private Optional<Insurance> insurance = Optional.empty();          // 차가 보험이 있을 수도 있고, 보험이 없을 수도 있음을 명시하는 Optional

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = Optional.of(insurance);    // Optional 객체로 만든다.
    }
}
