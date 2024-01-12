package a01a.e2;

import java.util.*;
import java.util.stream.*;;

public class LogicsImpl implements Logics {

    private final int size;
    private List<Pair<Integer, Integer>> selected = new LinkedList<>();
    private boolean moving = false;

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean hit(int x, int y) {
        Pair<Integer, Integer> position = new Pair<>(x, y);
        System.out.println("pair: "+position);
        if (isOver()) {
            return false;
        }
        if (moving || startMove(position)) {
            moving = true;
            this.selected = this.selected.stream()
                    .map(p -> new Pair<>(p.getX() + 1, p.getY() - 1))
                    .collect(Collectors.toCollection(LinkedList::new));
            System.out.println("mi muovo!! "+selected);
            return true;
        }
        System.out.println("aggiungo");
        selected.add(position);
        System.out.println("selected: "+selected);
        return true;
    }

    private boolean adjacent(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        return Math.abs(a.getX() - b.getX()) <= 1 || Math.abs(a.getY() - b.getY()) <= 1;
    }

    private boolean startMove(Pair<Integer, Integer> position) {
        return selected.stream().anyMatch(p -> adjacent(p, position));
    }

    @Override
    public boolean isOver() {
        return this.selected.stream().anyMatch(b -> b.getX() == this.size || b.getY() == -1);
    }

    @Override
    public boolean isHitted(int x, int y) {
        return selected.contains(new Pair<Integer, Integer>(x, y));
    }
}
