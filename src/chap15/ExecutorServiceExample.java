package chap15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static chap15.Functions.*;

public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(2);  // 스레드 풀 생성 (총 스레드는 2개)
        Future<Integer> task1 = executorService.submit(() -> f(x));
        Future<Integer> task2 = executorService.submit(() -> g(x));

        System.out.println(task1.get() + task2.get());

        executorService.shutdown();     // 스레드 풀 종료

        Future<Integer> task3 = ff(x);
        Future<Integer> task4 = gf(x);
        System.out.println(task3.get() + task4.get());
    }
}
