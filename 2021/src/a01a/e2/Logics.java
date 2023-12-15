package a01a.e2;

public interface Logics{
    enum buttonType {
        FIRST, SECOND
    }

    buttonType hit(int x, int y);
    boolean isHitted(int x, int y);
    boolean isOver();
    
}
