package chap15;

import static chap15.Functions.f;
import static chap15.Functions.g;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        int x = 1000;

        Result result = new Result();

        Thread t1 = new Thread(() -> {
            result.left = f(x);             // 2000
        });

        Thread t2 = new Thread(() -> {
            result.right = g(x);            // 1001
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(result.left + result.right);     // 3001
    }

    private static class Result {

        private int left;
        private int right;
    }
}
