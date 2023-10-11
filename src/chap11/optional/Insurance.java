package chap11.optional;

public class Insurance {

    private String name;                // Optional 이 아니다. 따라서 보험회사는 반드시 이름이 있어야 한다.

    public Insurance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
