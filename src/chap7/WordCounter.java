package chap7;

public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :                                      // 공백이 연속되는 경우
                    new WordCounter(counter, true);     // 단어 다음 공백인 경우, 공백 상태로 변경
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) :       // 공백 다음 단어가 시작되는 경우, 단어 개수 증가
                    this;                                                       // 단어가 계속 유지되는 경우
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, false);   // 두 WordCounter의 counter (단어 개수) 를 더한다.
                                                                                        // 합치기만 수행하므로, lastSpace는 아무값이나 상관없다.
    }

    public int getCounter() {
        return counter;
    }
}
