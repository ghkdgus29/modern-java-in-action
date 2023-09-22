package chap2;

public class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return (Color.RED == apple.getColor()) && (apple.getWeight() > 150);
    }
}
