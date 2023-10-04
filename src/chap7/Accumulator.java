package chap7;

public class Accumulator {

    private long total = 0;

    public void add(long value) {
        total += value;
    }

    public long getTotal() {
        return total;
    }
}
