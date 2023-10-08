package chap9;

public class Container {

    public void start() {
        int a = 10;
        foo((Mock) () -> System.out.println("ho"));        // 콘텍스트 오버로딩에 의한 모호함 -> 컴파일 에러 발생 -> 명시적 형변환을 통해 해결

        foo(new Mock() {
            @Override
            public void mocking() {
                System.out.println("ho");           // 실행 잘됨
            }
        });
    }
    public void foo(Mock mock) {
        mock.mocking();
    }

    public void foo(Runnable r) {
        r.run();
    }
}
