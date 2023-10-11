package chap11.optional;

import java.util.Optional;

public class Person {

    private Optional<Car> car = Optional.empty();          // 사람이 차를 소유했을 수도 있고, 소유하지 않았을 수도 있음을 명시하는 Optional

    private int age;

    public Optional<Car> getCar() {
        return car;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCar(Car car) {
        this.car = Optional.of(car);    // Optional 객체로 만든다.
    }
}
