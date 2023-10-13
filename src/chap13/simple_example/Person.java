package chap13.simple_example;

public interface Person {

    default void foo() {
        System.out.println("foo!");
    }
}
