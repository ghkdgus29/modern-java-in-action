package chap13.ambiguous;

public class C implements A, B{

    @Override
    public void hello() {
        B.super.hello();
    }

    public static void main(String[] args) {
        new C().hello();        // Hello from B
    }
}
