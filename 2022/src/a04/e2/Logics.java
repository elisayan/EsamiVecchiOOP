package a04.e2;

import a04.e2.LogicsImpl.Player;

public interface Logics {
    boolean moveRook(int x, int y);

    void moveKing();

    void reset();

    Pair<Integer, Integer> getPosition(Player p);

    boolean isRookWin(int x, int y);

    boolean isKingWin();
}
