package chap3;

public class TargetTypingExample {

    public static void main(String[] args) {
//        execute(() -> System.out.println("ho"));     // java: reference to execute is ambiguous, 컴파일 불가

        execute((Walker) () -> System.out.println("ho"));       // 컴파일 가능

        execute((Runner) () -> System.out.println("ho"));       // 컴파일 가능
    }

    public static void execute(Walker walker) {
        walker.walk();
    }

    public static void execute(Runner runner) {
        runner.run();
    }
}

@FunctionalInterface
interface Walker {
    void walk();
}

@FunctionalInterface
interface Runner {
    void run();
}
