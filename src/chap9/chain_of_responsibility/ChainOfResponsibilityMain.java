package chap9.chain_of_responsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainOfResponsibilityMain {

    public static void main(String[] args) {
        // old school
        HeaderTextProcessing headerTextProcessing = new HeaderTextProcessing();
        SpellCheckerProcessing spellCheckerProcessing = new SpellCheckerProcessing();

        headerTextProcessing.setSuccessor(spellCheckerProcessing);  // headerTextProcessing 객체의 후임자로 spellCheckerProcessing 객체를 설정한다.

        String result = headerTextProcessing.handle("Aren't labdas really sexy?!");
        System.out.println(result);             // From Hyun: Aren't lambdas really sexy?!

        // lambda
        UnaryOperator<String> headerProcessing = text -> "From Hyun: " + text;
        UnaryOperator<String> spellCheckerProcessingLambda = text -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessingLambda);

        String result2 = pipeline.apply("Aren't labdas really sexy?!");
        System.out.println(result2);             // From Hyun: Aren't lambdas really sexy?!
    }
}
