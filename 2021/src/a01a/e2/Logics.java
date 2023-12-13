package a01a.e2;

public interface Logics {
    enum NumberButton {
        FIRST, SECOND;
    }

    NumberButton press(int x, int y);
    boolean getPressed(int x, int y);
    boolean isOver();
}
