package a01c.e2;

import a01c.e2.LogicImpl.NumberButton;

public interface Logic {
    NumberButton click(int x, int y);
    boolean isZero(int x, int y);
    boolean isOver();
}
