package chap19.lazy_list;

import java.util.function.Predicate;

public interface MyList<T> {
    T head();

    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    MyList<T> filter(Predicate<T> p);
}
