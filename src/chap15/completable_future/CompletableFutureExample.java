package chap15.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static chap15.Functions.f;
import static chap15.Functions.g;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        int x = 1000;

        CompletableFuture<Integer> task1 = new CompletableFuture<>();
        CompletableFuture<Integer> task2 = new CompletableFuture<>();
        CompletableFuture<Integer> afterTask = task1.thenCombine(task2, (result1, result2) -> result1 + result2);   // task1, task2 선행작업이 모두 끝나면 스레드를 사용해 afterTask 작업을 수행한다.

        executorService.submit(() -> task1.complete(f(x)));     // task1 의 작업 결과물이 f(x)
        executorService.submit(() -> task2.complete(g(x)));     // task2 의 작업 결과물이 g(x)

        System.out.println(afterTask.get());        // 메인 스레드는 task1, task2 의 작업시간동안 대기할 필요 없이, afterTask 의 작업이 끝나기만을 기다리면 된다.

        executorService.shutdown();
    }
}
