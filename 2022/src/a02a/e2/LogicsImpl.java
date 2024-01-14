package a02a.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    int size;
    List<Pair<Integer, Integer>> cellsB = new LinkedList<>();
    Set<Pair<Integer, Integer>> diagonal = new HashSet<>();

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public void calculateDiagonal(int x, int y) {
        Pair<Integer, Integer> pair = new Pair<>(x, y);

        if (isOver() && cellsB.contains(pair)) {
            cellsB.clear();
            diagonal.clear();
        }

        cellsB.add(pair);

        calculateDiagonalDirection(x, y, 1, 1); // Alto a destra
        calculateDiagonalDirection(x, y, 1, -1); // Basso a destra
        calculateDiagonalDirection(x, y, -1, 1); // Alto a sinistra
        calculateDiagonalDirection(x, y, -1, -1); // Basso a sinistra

        diagonal.remove(pair);
    }

    private void calculateDiagonalDirection(int startX, int startY, int deltaX, int deltaY) {
        int x = startX;
        int y = startY;
    
        while (x >= 0 && x < size && y >= 0 && y < size) {
            diagonal.add(new Pair<>(x, y));
            x += deltaX;
            y += deltaY;
        }
    }

    @Override
    public boolean isDiagonal(int x, int y) {
        return diagonal.contains(new Pair<Integer, Integer>(x, y));
    }

    private boolean isOver() {
        System.out.println("diagonal size: " + diagonal.size() + " B size: " + cellsB.size());
        return diagonal.size() + cellsB.size() == size * size;
    }

    @Override
    public boolean isBishop(int x, int y) {
        return cellsB.contains(new Pair<>(x,y));
    }

}
