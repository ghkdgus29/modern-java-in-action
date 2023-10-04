package chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumRunner {

    public static void main(String[] args) {

        long result = forkJoinSum(1000_0000L);
        System.out.println(result);
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);     // 새로운 ForkJoinPool 을 만들고, invoke 메서드로 task를 전달
    }
}
