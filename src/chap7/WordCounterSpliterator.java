package chap7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;        // 초기에 주어진 전체 문자열
    private int currentChar = 0;        // 현재 Spliterator 의 문자열 시작 위치

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) { // 순차적으로 요소를 소비하며 탐색할 요소가 남아있으면 true 반환 (Iterator 동작과 동일)
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {              // Spliterator 를 분할하여 두 번째 Spliterator 를 생성한다.
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;                // 문자열이 충분히 작으므로, 더 이상 쪼개지 않고 null 반환
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {    // splitPos 를 문자열의 중간으로 일단 이동한 뒤,
            if (Character.isWhitespace(string.charAt(splitPos))) {                                      // 공백을 만날때까지 한 칸씩 이동
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));   // 현재 Spliterator 의 시작 위치 부터 splitPos 까지를 담당하는 두 번째 Spliterator 생성
                currentChar = splitPos;     // 현재 Spliterator 의 시작 위치를 분할 위치로 변경
                return spliterator;
            }
        }
        return null;    // 단어의 길이가 문자열 중간부터 끝까지 인 경우 분할 x
    }

    @Override
    public long estimateSize() {                            // 탐색해야 할 요소 개수 정보를 제공
        return string.length() - currentChar;
    }

    /**
     * Spliterator 특성 집합 제공
     *
     * ORDERED: 요소에 정해진 순서가 있다.
     * DISTINCT: 다른 위치의 두 요소는 항상 같지 않다.
     * SORTED: 탐색된 요소는 정의된 정렬 순서를 따른다.
     * SIZED: 정확한 크기 값을 반환한다.
     * NONNULL: 탐색하는 모든 요소는 null 이 아니다.
     * IMMUTABLE: 소스는 불변으로, 요소 탐색 동안 요소를 추가, 삭제, 변경할 수 없다.
     * COUCURRENT: 소스를 여러 스레드에서 동시에 고칠 수 있다.
     * SUBSIZED: 현재 소스와 분할되는 모든 소스들은 SIZED 특성을 갖는다.
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
