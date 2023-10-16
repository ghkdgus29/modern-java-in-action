package chap15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ThreadReview {

    public static void main(String[] args) {
        int[] stats = IntStream.rangeClosed(1, 100_0000)
                .toArray();

        benchmark((s) -> sumIterative(s), stats);

        benchmark((s) -> sumParallel(s), stats);

        benchmark(ThreadReview::sumParallelStream, stats);
    }


    public static long sumIterative(int[] stats) {
        long sum = 0;
        for (int i = 0; i < 100_0000; i++) {
            sum += stats[i];
        }
        return sum;
    }

    public static long sumParallel(int[] stats) {
        ArrayList<Long> sum = new ArrayList<>();
        sum.add(0L);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 25_0000; i++) {
                sum.set(0, sum.get(0) + stats[i]);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 25_0000; i < 50_0000; i++) {
                sum.set(0, sum.get(0) + stats[i]);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 50_0000; i < 75_0000; i++) {
                sum.set(0, sum.get(0) + stats[i]);
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 75_0000; i < 100_0000; i++) {
                sum.set(0, sum.get(0) + stats[i]);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return sum.get(0);
    }

    public static long sumParallelStream(int[] stats) {
        return Arrays.stream(stats)
                .parallel()
                .mapToLong(i -> i)
                .sum();
    }

    private static void benchmark(Function<int[], Long> function, int[] stats) {
        long fastest = Long.MAX_VALUE;
        Long result = 0L;
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            result = function.apply(stats);
            long duration = (System.nanoTime() - start) / 1_000;
            if (duration < fastest) fastest = duration;
        }

        System.out.println("fastest time: " + fastest + "µs, result: " + result);
    }
}
