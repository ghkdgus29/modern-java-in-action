package chap16;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static chap16.Util.delay;

public class TimeOutExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println("엄청 오래걸리는 작업 중...");
                delay();
                delay();
                delay();
                return "작업 성공적 완료";
            })
            .completeOnTimeout("작업 부분적 완료 ㅠ", 500, TimeUnit.MILLISECONDS); // 결과를 기다리는 시간이 0.5초가 지나면 기본값 반환

    System.out.println(completableFuture.get());

    }
}
