package chap17;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

public class Main {

    public static void main(String[] args) {
//        getTemperatures("New York").subscribe(new TempSubscriber());

        getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
    }

    private static Publisher<TempInfo> getTemperatures(String town) {
        return new Publisher<TempInfo>() {
            @Override
            public void subscribe(Subscriber<? super TempInfo> subscriber) {
                subscriber.onSubscribe(new TempSubscription(subscriber, town));
            }
        };
    }

    private static Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return new Publisher<TempInfo>() {
            @Override
            public void subscribe(Subscriber<? super TempInfo> subscriber) {
                TempProcessor processor = new TempProcessor();
                processor.subscribe(subscriber);
                processor.onSubscribe(new TempSubscription(processor, town));
            }
        };
    }
}
