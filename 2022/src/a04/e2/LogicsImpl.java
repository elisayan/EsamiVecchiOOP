package a04.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    public enum Player {
        ROOK, KING
    }

    int size;
    Random random = new Random();
    Map<Player, Pair<Integer, Integer>> player = new HashMap<>();
    boolean kingWin = false;

    public LogicsImpl(int size) {
        this.size = size;
        reset();
    }

    private Pair<Integer, Integer> setRandomPosition() {
        return new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
    }

    private boolean attacks() {
        return !player.get(Player.KING).equals(player.get(Player.ROOK))
                && (player.get(Player.ROOK).getX() == player.get(Player.KING).getX()
                        || player.get(Player.ROOK).getY() == player.get(Player.KING).getY());
    }

    @Override
    public void reset() {
        player.put(Player.ROOK, setRandomPosition());
        do {
            player.put(Player.KING, setRandomPosition());
        } while (attacks());
    }

    @Override
    public boolean moveRook(int x, int y) {
        var rookOrigin = player.get(Player.ROOK);
        if ((rookOrigin.getX() == x || rookOrigin.getY() == y)) {
            if (!isOverKing(x, y, rookOrigin)) {
                player.replace(Player.ROOK, new Pair<Integer, Integer>(x, y));
                return true;
            }

        }

        return false;
    }

    private boolean isOverKing(int x, int y, Pair<Integer, Integer> origin) {
        var king = player.get(Player.KING);
        return (x == origin.getX() && ((king.getY() < origin.getY() && y < king.getY())
                || (king.getY() > origin.getY() && y > king.getY())))
                || (y == origin.getY() && ((king.getX() > origin.getX() && x > king.getX())
                        || (king.getX() < origin.getX() && x < king.getX())));
    }

    @Override
    public void moveKing() {
        var originKing = player.get(Player.KING);
        var originRook = player.get(Player.ROOK);

        if (originKing.getX().equals(originRook.getX()) || originKing.getY().equals(originRook.getY())) {
            player.replace(Player.KING, originRook);
            kingWin = true;
        } else {
            do {
                player.replace(Player.KING, setRandomPosition());
            } while (attacks());
        }
    }

    @Override
    public Pair<Integer, Integer> getPosition(Player p) {
        return player.get(p);
    }

    @Override
    public boolean isRookWin(int x, int y) {
        return player.get(Player.KING).equals(new Pair<>(x, y));
    }

    @Override
    public boolean isKingWin() {
        return kingWin;
    }
}
