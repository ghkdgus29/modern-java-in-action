package chap19.lazy_list;

public class Main {

    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);

        numbers = from(2);
        int prime_two = primes(numbers).head();
        int prime_three = primes(numbers).tail().head();
        int prime_four = primes(numbers).tail().tail().head();
        System.out.println(prime_two + " " + prime_three + " " + prime_four);

    }

    private static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    private static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail()
                                .filter(n -> n % numbers.head() != 0)

                )
        );
    }
}
