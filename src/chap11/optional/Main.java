package chap11.optional;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//
//        Insurance insurance1 = new Insurance("황현자동차보험");
//        Car car1 = new Car();
//        car1.setInsurance(insurance1);
//        Person person1 = new Person();
//        person1.setCar(car1);
//
//        System.out.println(getCarInsuranceName(Optional.of(person1)));           // 황현자동차보험
//
//        Person person2 = new Person();
//
//        System.out.println(getCarInsuranceName(Optional.of(person2)));           // UnKnown
//
//        Insurance insurance2 = new Insurance("서연자동차보험");
//        Car car2 = new Car();
//        car2.setInsurance(insurance2);
//        Person person3 = new Person();
//        person3.setCar(car2);
//
//        Set<String> carInsuranceNames = getCarInsuranceNames(List.of(person1, person2, person3));
//        System.out.println(carInsuranceNames);      // [황현자동차보험, 서연자동차보험]


        Insurance insurance1 = new Insurance("황현자동차보험");
        Car car1 = new Car();
        car1.setInsurance(insurance1);
        Person person1 = new Person();
        person1.setCar(car1);
        person1.setAge(22);

        String person1CarInsuranceName = getCarInsuranceName(Optional.of(person1), 20);
        System.out.println(person1CarInsuranceName);        // 황현자동차보험


        Insurance insurance2 = new Insurance("서연자동차보험");
        Car car2 = new Car();
        car2.setInsurance(insurance2);
        Person person2 = new Person();
        person2.setCar(car1);
        person2.setAge(19);

        String person2CarInsuranceName = getCarInsuranceName(Optional.of(person2), 20);
        System.out.println(person2CarInsuranceName);        // UnKnown
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)       // Optional<Person> -> Optional<Car>
                .flatMap(Car::getInsurance)         // Optional<Car> -> Optional<Insurance>
                .map(Insurance::getName)            // Optional<Insurance> -> Optional<String>
                .orElse("UnKnown");           // 최종 Optional 객체의 내부가 비어있는 경우
    }

    public static Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)                                            // Stream<Person> -> Stream<Optional<Car>>
                .map(optCar -> optCar.flatMap(Car::getInsurance))               // Stream<Optional<Car>> -> Stream<Optional<Insurance>>
                .map(optInsurance -> optInsurance.map(Insurance::getName))      // Stream<Optional<Insurance>> -> Stream<Optional<String>>
                .filter(Optional::isPresent)                                    // Optional.empty 모두 제거
                .map(Optional::get)                                             // Optional unwrap, Stream<Optional<String>> -> Stream<String>
                .collect(Collectors.toSet());
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsuranceQuiz(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        Insurance cheapestCompany = new Insurance("가장싼자동차보험");
        return cheapestCompany;
    }

    public static String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(optPerson -> optPerson.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UnKnown");
    }
}
