package chap3;

import java.util.function.Supplier;

public class VariableExample {

    int instanceVariable = 10000;

//    public Supplier<Integer> incrementer(int start) {
//        return () -> start++;                             // 컴파일 에러
//    }

    public Supplier<Integer> incrementer() {
        return () -> instanceVariable++;
    }
}
