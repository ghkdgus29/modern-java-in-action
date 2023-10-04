package chap7;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
                                            // RecursiveTask<R> 을 상속하여 스레드 풀을 이용한다.
    private static final long THRESHOLD = 10_000;

    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {              // 서브 태스크가 작아지면, 더 이상 쪼개지 않고 순차 실행
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();                        // ForkJoinPool 의 다른 스레드로 비동기 실행

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();     // 현재 스레드로 동기 실행

        Long leftResult = leftTask.join();      // 왼쪽 서브 태스크의 결과를 읽어온다. (없으면 기다림)

        return leftResult + rightResult;        // 왼쪽, 오른쪽 서브 태스크의 결과를 합쳐서 반환한다.
    }

    private long computeSequentially() {        // 순차 실행
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
