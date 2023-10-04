package chap7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class ParallelStreams {

    private static final long N = 10_000_000L;

    public static void main(String[] args) {
//        benchmark(ParallelStreams::iterativeSum);       // 2960 msecs
//        benchmark(ParallelStreams::sequentialSum);      // 79371 msecs
//        benchmark(ParallelStreams::parallelSum);        // 135078 msecs
//
//        benchmark(ParallelStreams::rangedSum);          // 2966 msecs
//        benchmark(ParallelStreams::parallelRangedSum);  // 990 msecs
//
//        benchmark(ParallelStreams::sideEffectSum);      // 2967 msecs
//        System.out.println(sideEffectSum(N));           // 50000005000000
//
//        benchmark(ParallelStreams::sideEffectParallelSum);  // 787 msecs
//        System.out.println(sideEffectParallelSum(N));       // 17020621348761

        benchmark(ParallelStreams::findFirst);        // 67 msec
        System.out.println(findFirst(N));             // 1

        benchmark(ParallelStreams::findAny);          // 15 msec
        System.out.println(findAny(N));               // 6250001

        benchmark(ParallelStreams::findUnorderedFirst);     // 18 msec
        System.out.println(findUnorderedFirst(N));          // 6250001
    }

    public static void benchmark(Function<Long, Long> sumFunction) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            sumFunction.apply(N);
            long duration = (System.nanoTime() - start) / 1_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println(fastest + " msecs");
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, (sum, add) -> sum + add);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, (sum, add) -> sum + add);
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, (sum, add) -> sum + add);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, (sum, add) -> sum + add);
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .forEach(accumulator::add);
        return accumulator.getTotal();
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);
        return accumulator.getTotal();
    }

    public static long findFirst(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .findFirst()
                .getAsLong();
    }

    public static long findAny(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .findAny()
                .getAsLong();
    }

    public static long findUnorderedFirst(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .unordered()
                .findFirst()
                .getAsLong();
    }
}
