package chap16.combination;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Double> future = executor.submit(() -> doSomeLongComputation());     // 시간이 오래 걸리는 작업을 다른 스레드에서 비동기적으로 실행

        doSomethingElse();  // 현재 스레드에선 다른 작업을 수행한다.

        Double result = future.get(1, TimeUnit.SECONDS);   // 다른 스레드에서 수행한 비동기 작업의 결과를 가져온다.
                                                                  // 1초동안 블로킹된다. 1초가 지나면 TimeoutException 발생
    }

    public static double doSomeLongComputation() {
        System.out.println("ho");
        return 1.1;
    }

    public static void doSomethingElse() {
        System.out.println("do my job");
    }
}
