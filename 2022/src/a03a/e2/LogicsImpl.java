package a03a.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    enum Direction {
        NORD(0, -1), SUD(0, 1), EST(1, 0), OVEST(-1, 0);

        int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    private Pair<Integer, Integer> umanDestination;
    private Direction direction;

    @Override
    public boolean umanMove(int x, int y, Pair<Integer,Integer> uman) {
        this.umanDestination = new Pair<Integer,Integer>(x, y);
        System.out.println("destination: "+umanDestination);
        System.out.println("uman: "+uman);
        return umanDestination.getX().equals(uman.getX()) || umanDestination.getY().equals(uman.getY());
    }

    @Override
    public boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

    @Override
    public boolean umanOrigin(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'umanOrigin'");
    }
    
}
