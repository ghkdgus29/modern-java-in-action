package chap9.strategy;

public class StrategyMain {

    public static void main(String[] args) {
        // old school
        Validator numericValidator = new Validator(new IsNumeric());
        System.out.println(numericValidator.validate("aaaa"));      // false

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        System.out.println(lowerCaseValidator.validate("aaaa"));    // true

        // with lambdas
        Validator numericValidator2 = new Validator((s) -> s.matches("\\d+"));
        System.out.println(numericValidator2.validate("aaaa"));     // false

        Validator lowerCaseValidator2 = new Validator((s) -> s.matches("[a-z]+"));
        System.out.println(lowerCaseValidator2.validate("aaaa"));   // true
    }
}
