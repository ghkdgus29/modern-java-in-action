package chap9;

public class Runner {

    public static void main(String[] args) {
        Container container = new Container();
        container.start();


    }

    public static void foo(Mock mock) {
        mock.mocking();
    }
}
