package chap13.ambiguous;

public class Diamond {

    public static void main(String... args) {
        new D().hello();
    }

    interface A {

        default void hello() {
            System.out.println("Hello from A");
        }

    }

    interface B extends A {}

    interface C extends A {
        void hello();
    }

    static class D implements B, C {
        @Override
        public void hello() {
            System.out.println("Hello from D");
        }
    }
}
