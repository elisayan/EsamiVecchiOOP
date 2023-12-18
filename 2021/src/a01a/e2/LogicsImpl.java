package a01a.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private int size;
    private Set<Pair<Integer, Integer>> selected = new HashSet<>();
    private Pair<Integer, Integer> first;

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public buttonType hit(int x, int y) {
        if (first == null) {
            first = new Pair<Integer, Integer>(x, y);
            selected.add(first);
            return buttonType.FIRST;
        }
        area(first.getX(), first.getY(), x, y);
        first = null;
        return buttonType.SECOND;
    }

    private void area(int x1, int y1, int x2, int y2) {
        for (int i = (x1 < x2) ? x1 : x2; i <= ((x1 < x2) ? x2 : x1); i++) {
            for (int j = (y1 < y2) ? y1 : y2; j <= ((y1 < y2) ? y2 : y1); j++) {
                selected.add(new Pair<Integer, Integer>(i, j));
            }
        }
    }

    @Override
    public boolean isHitted(int x, int y) {
        return selected.contains(new Pair<>(x, y));
    }

    @Override
    public boolean isOver() {
        return this.selected.size() == size * size;
    }
}
