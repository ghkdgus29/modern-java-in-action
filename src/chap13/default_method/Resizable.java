package chap13.default_method;

public interface Resizable extends Drawable {

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);

    default void setRelativeSize(int wFactor, int hFactor) {    // default 메서드 추가
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    }
}
