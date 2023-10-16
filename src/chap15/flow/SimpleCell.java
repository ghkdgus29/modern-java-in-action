package chap15.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.function.Consumer;

import static java.util.concurrent.Flow.Subscription;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {

    private int value = 0;
    private String name;
    private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();       // subscriber 들 관리

    public SimpleCell(String name) {
        this.name = name;
    }

    // Publisher 인터페이스 구현

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {     // subscriber 등록
        subscribers.add(subscriber);
    }

    // Subscriber 인터페이스 구현

    @Override
    public void onSubscribe(Subscription subscription) {}

    @Override
    public void onNext(Integer newValue) {      // publisher 에게 받은 데이터를 처리한다.
        this.value = newValue;
        System.out.println(this.name + ":" + this.value);

        // subscriber 들의 value 값을 전부 publisher 와 똑같게 변경
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {}

    // 커스텀 메서드

    public void subscribe(Consumer<? super Integer> onNext) {
        subscribers.add(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription subscription) {
            }

            @Override
            public void onNext(Integer value) {
                onNext.accept(value);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
