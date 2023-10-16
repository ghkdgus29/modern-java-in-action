package chap15.flow;

public class ArithmeticCell extends SimpleCell{

    private int left;
    private int right;

    public ArithmeticCell(String name) {
        super(name);
    }

    public void setLeft(int left) {
        this.left = left;
        onNext(left + this.right);      // 현재 셀 값을 갱신하고, 모든 구독자 값 역시 갱신
    }

    public void setRight(int right) {
        this.right = right;
        onNext(right + this.left);      // 현재 셀 값을 갱신하고, 모든 구독자 값 역시 갱신
    }
}
