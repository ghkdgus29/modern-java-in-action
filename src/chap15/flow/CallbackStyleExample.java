package chap15.flow;

import chap15.Functions;

import java.util.function.IntConsumer;

public class CallbackStyleExample {

    public static void main(String[] args) {

        int x = 1000;
        Result result = new Result();

        f(x, i -> {
            result.left = i;
            System.out.println(result.left + result.right);
        });

        g(x, i -> {
            result.right = i;
            System.out.println(result.left + result.right);
        });

    }

    private static class Result {

        private int left;
        private int right;
    }

    private static void f(int x, IntConsumer dealWithResult) {
        dealWithResult.accept(Functions.f(x));
    }

    private static void g(int x, IntConsumer dealWithResult) {
        dealWithResult.accept(Functions.g(x));
    }
}
