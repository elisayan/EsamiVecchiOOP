package a01a.e2;

import java.util.*;

public class LogicsImpl implements Logics{

    private final int size;
    private Set<Pair<Integer, Integer>> selected = new HashSet<>();
    private Pair<Integer, Integer> first;
    public LogicsImpl(int size) {
        this.size = size;
    }
    
    @Override
    public NumberButton press(int x, int y) {
        Pair<Integer, Integer> pair = new Pair<>(x, y);
        if (first == null) {
            first = pair;
            return NumberButton.FIRST;
        }
        range(first.getX(), x, first.getY(), y);
        first = null;
        return NumberButton.SECOND;
    }

    private void range(int x1, int x2, int y1, int y2) {
        for (int i = (x1 < x2 ? x1 : x2); i <= (x1 < x2 ? x2 : x1); i++) {
            for (int j = (y1 < y2 ? y1 : y2); j <= (y1 < y2 ? y2 : y1); j++) {
                selected.add(new Pair<Integer, Integer>(i, j));
            }
        }
    }

    @Override
    public boolean getPressed(int x, int y) {
        return selected.contains(new Pair<>(x, y));
    }

    @Override
    public boolean isOver() {
        return this.selected.size() == size * size;
    }   
}
