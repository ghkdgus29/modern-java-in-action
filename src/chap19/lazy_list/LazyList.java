package chap19.lazy_list;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {

    private final T head;
    private final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this :
                p.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(p)) :
                        tail().filter(p);
    }
}
