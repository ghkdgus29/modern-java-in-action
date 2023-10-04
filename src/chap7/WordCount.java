package chap7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCount {

    private static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita "
                    + "mi  ritrovai in una  selva oscura"
                    + " che la  dritta via era   smarrita ";

    public static void main(String[] args) {
        System.out.println(countWordsIteratively(SENTENCE));        // 19

        System.out.println(countWords(SENTENCE));                   // 19

        System.out.println(countWordsParallel(SENTENCE));           // 25

        System.out.println(countWordsParallelUsingSpliterator(SENTENCE));   // 19
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = Character.isWhitespace(c);
            }
        }
        return counter;
    }

    public static int countWords(String s) {
        Stream<Character> stream = IntStream.range(0, s.length())
                .mapToObj(i -> s.charAt(i));

        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),   // identity(초기값)
                WordCounter::accumulate,                                                    // accumulator
                WordCounter::combine);                                                      // combiner

        return wordCounter.getCounter();
    }

    public static int countWordsParallel(String s) {
        Stream<Character> stream = IntStream.range(0, s.length())
                .mapToObj(i -> s.charAt(i));

        WordCounter wordCounter = stream.parallel()
                .reduce(new WordCounter(0, true),   // identity(초기값)
                        WordCounter::accumulate,                                                    // accumulator
                        WordCounter::combine);                                                      // combiner

        return wordCounter.getCounter();
    }

    public static int countWordsParallelUsingSpliterator(String s) {
        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);

        return wordCounter.getCounter();
    }
}
