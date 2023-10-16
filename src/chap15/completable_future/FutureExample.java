package chap15.completable_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static chap15.Functions.f;
import static chap15.Functions.g;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> task1 = executorService.submit(() -> f(x));
        Future<Integer> task2 = executorService.submit(() -> g(x));

        Integer result1 = task1.get();
        Integer result2 = task2.get();                                  // 선행작업이 모두 끝날때까지 블로킹

        Future<Integer> afterTask = executorService.submit(() -> result1 + result2);        // 선행작업이 끝나야 후행작업을 시작할 수 있다.

        System.out.println(afterTask.get());        // 3001

        executorService.shutdown();
    }
}
