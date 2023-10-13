package chap13.ambiguous;

public interface B{

    default void hello() {
        System.out.println("Hello from B");
    }
}
