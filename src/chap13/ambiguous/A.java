package chap13.ambiguous;

public interface A {

    default void hello() {
        System.out.println("Hello from A");
    }
}
