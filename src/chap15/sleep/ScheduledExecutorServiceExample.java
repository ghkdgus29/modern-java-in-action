package chap15.sleep;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {

//        work1();
//        Thread.sleep(10000);
//        work2();

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    work1();
    // work2 를 실행하기 위해 10초간의 대기 시간이 필요한 경우, 일단 스레드를 반납한다.
    scheduledExecutorService.schedule(() -> work2(), 10, TimeUnit.SECONDS);     // 10초 뒤에 스레드를 가져와 work2 작업을 수행한다.

    scheduledExecutorService.shutdown();
    }

    public static void work1() {
        System.out.println("work1 작업 중");
    }

    public static void work2() {
        System.out.println("work2 작업 중");
    }
}
