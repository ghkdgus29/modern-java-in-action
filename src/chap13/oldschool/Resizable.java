package chap13.oldschool;

public interface Resizable extends Drawable{

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);

//    void setRelativeSize(int wFactor, int hFactor);     // 메서드 추가
}
