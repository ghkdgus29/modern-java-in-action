package chap15.flow;

public class Main {

    public static void main(String[] args) {

        SimpleCell c1 = new SimpleCell("C1");
        SimpleCell c2 = new SimpleCell("C2");
        ArithmeticCell c3 = new ArithmeticCell("C3");

        SimpleCell c4 = new SimpleCell("C4");
        ArithmeticCell c5 = new ArithmeticCell("C5");

        c1.subscribe(i -> c3.setLeft(i));
        c2.subscribe(i  -> c3.setRight(i));

        c3.subscribe(i -> c5.setLeft(i));
        c4.subscribe(i -> c5.setRight(i));

        c1.onNext(10);
        c2.onNext(20);
        c1.onNext(15);
        c4.onNext(1);
        c4.onNext(3);



    }
}
