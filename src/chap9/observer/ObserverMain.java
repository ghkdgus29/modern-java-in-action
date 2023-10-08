package chap9.observer;

public class ObserverMain {

    public static void main(String[] args) {
        // old school
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());

        feed.notifyObservers("The queen said her favourite book is Java 8 & 9 in Action!");     // Guardian Observer 가 반응
        feed.notifyObservers("Give me money!!!");                                               // NYTimes Observer 가 반응
        feed.notifyObservers("boola boola foo");                                                // 아무도 반응 x
        feed.notifyObservers("The queen likes wine~");                                          // LeMonde Observer 와 Guardian Observer 가 반응

        System.out.println();

        // lambda
        Feed feedLambda = new Feed();

        feedLambda.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });

        feedLambda.registerObserver(tweet -> {
            if (tweet != null & tweet.contains("queen")) {
                System.out.println("Yet more new from London..." + tweet);
            }
        });

        feedLambda.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });

        feedLambda.notifyObservers("The queen said her favourite book is Java 8 & 9 in Action!");
        feedLambda.notifyObservers("Give me money!!!");
        feedLambda.notifyObservers("boola boola foo");
        feedLambda.notifyObservers("The queen likes wine~");
    }
}
