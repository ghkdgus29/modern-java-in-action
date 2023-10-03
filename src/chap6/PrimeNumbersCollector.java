package chap6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector
        implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
                        //  수집될 항목,      중간 결과 누적자,              최종 연산 결과

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {       // 비어있는 컨테이너 만들기
        return () -> new HashMap<>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {     // 컨테이너에 요소 추가하기
        return (accumulator, candidate) -> {
            List<Integer> primes = accumulator.get(true);
            accumulator.get(isPrime(primes, candidate))
                    .add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {      // 컨테이너들을 병합 (병렬 스트림의 경우 해당)
/*        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
*/      // 알고리즘이 순차적이기 때문에 실제 병렬로 사용할 수 없다.

        return null;
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {  // 누적자 컨테이너를 최종 결과 컨테이너로 변환
        return Function.identity();
    }

    /**
     * 스트림을 병렬로 리듀스할 수 있는지,
     * 스트림에 어떤 최적화를 할 수 있는지 힌트를 제공한다.
     *
     * UNORDERED: 리듀싱 결과는 스트림 요소의 방문 순서나 누적 순서에 영향을 받지 않는다.
     * CONCURRENT: 다중 스레드에서 accumulator 함수를 동시에 호출할 수 있으며, 병렬 리듀싱을 수행할 수 있다.
     * IDENTITY_FINISH: finisher 메서드가 반환하는 함수는 단순히 identity 적용이므로, 이를 생략할 수 있다.
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    private static boolean isPrime(List<Integer> primes, Integer candidate) {
        double candidateRoot = Math.sqrt(candidate);
        return primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
