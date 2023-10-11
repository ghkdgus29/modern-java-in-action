package chap11.oldschool;

public class Main {

    public static void main(String[] args) {
        Insurance insurance1 = new Insurance("황현자동차보험");
        Car car1 = new Car();
        car1.setInsurance(insurance1);
        Person person1 = new Person();
        person1.setCar(car1);

        System.out.println(getCarInsuranceName(person1));           // 황현자동차보험

        Person person2 = new Person();

        System.out.println(getCarInsuranceName(person2));           // UnKnown
    }

    public static String getCarInsuranceName(Person person) {
        if (person == null) {
            return "UnKnown";
        }

        Car car = person.getCar();
        if (car == null) {
            return "UnKnown";
        }

        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "UnKnown";
        }

        return insurance.getName();
    }
}
