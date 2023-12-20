package a02b.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    private int size;
    private List<Pair<Integer,Integer>> selected = new LinkedList<>();

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean hit(int x, int y) {
        var position = new Pair<>(x, y);
        if (this.selected.contains(position)){
            this.selected.remove(position);
        } else {
            this.selected.add(position);
        }
        return this.selected.contains(position);
    }

    @Override
    public boolean checkDiagonal() {
        Deque<Pair<Integer, Integer>> triple = new LinkedList<>();
        Pair<Integer,Integer> actual;
        for (int i = 0; triple.size()==3; i++) {
            triple.add(selected.get(i));
        }
        actual = triple.pop();
        for (Pair<Integer,Integer> pair : triple) {
            if ((actual.getX() + 1 == pair.getX() || actual.getX() - 1 == pair.getX())
                    && (actual.getY() + 1 == pair.getY() || actual.getY() - 1 == pair.getY())) {
                        return true;
            }
        }
        return false;
    }

    

    
}
