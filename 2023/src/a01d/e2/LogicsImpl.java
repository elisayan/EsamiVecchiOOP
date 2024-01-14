package a01d.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    public enum Direction {
        ALTO(0, -1), BASSO(0, 1), SINISTRA(-1, 0), DESTRA(1, 0);

        private int x;
        private int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int size;
    private List<Pair<Integer, Integer>> rectangle = new LinkedList<>();

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public void hit(int x, int y) {
        if (rectangle.isEmpty()) {
            for (int i = x - 2; i <= x + 2; i++) {
                for (int j = y - 2; j <= y + 2; j++) {
                    rectangle.add(new Pair<Integer, Integer>(i, j));
                }
            }
        } else {
            calculateRectangle(x, y);
        }
    }

    private void calculateRectangle(int x, int y) { 
        Optional<Direction> direction = Optional.empty();

        if (x==0) {
            direction = Optional.of(Direction.SINISTRA);
        } else if (y==0) {
            direction=Optional.of(Direction.ALTO);
        } else if (x==size-1) {
            direction=Optional.of(Direction.DESTRA);
        } else if (y==size-1) {
            direction=Optional.of(Direction.BASSO);
        }

        if (direction.isPresent()) {
            Direction dir = direction.get();
            this.rectangle = this.rectangle.stream().map(p -> new Pair<Integer, Integer>(p.getX() + dir.x, p.getY() + dir.y)).toList();
        }
    }

    @Override
    public boolean isOver() {
        return rectangle.stream()
                .anyMatch(p -> p.getX() > size - 1 || p.getY() > size - 1 || p.getX() < 0 || p.getY() < 0);
    }

    @Override
    public boolean isSelected(int x, int y) {
        return rectangle.contains(new Pair<Integer, Integer>(x, y));
    }

}
