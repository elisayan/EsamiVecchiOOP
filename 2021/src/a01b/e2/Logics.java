package a01b.e2;

public interface Logics {
    enum ButtonNumber {
        FIRST, SECOND, THIRD
    }
    
    ButtonNumber hit(int x, int y);
    boolean isHitted(int x, int y);
}