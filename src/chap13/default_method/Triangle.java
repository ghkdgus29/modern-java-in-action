package chap13.default_method;

public class Triangle implements Resizable {

    @Override
    public void draw() {}

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWidth(int width) {}

    @Override
    public void setHeight(int height) {}

    @Override
    public void setAbsoluteSize(int width, int height) {}
}
