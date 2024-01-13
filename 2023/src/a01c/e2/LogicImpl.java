package a01c.e2;

import java.util.*;

public class LogicImpl implements Logic {

    public enum NumberButton {
        FIRST, SECOND, THIRD, OTHER
    }

    private int size;
    private List<Pair<Integer, Integer>> clicked = new LinkedList<>();
    private List<Pair<Integer,Integer>> rectangle = new LinkedList<>();
    //private int count=0;
    private boolean over = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public NumberButton click(int x, int y) {
        clicked.add(new Pair<Integer, Integer>(x, y));

        switch (clicked.size()) {
            case 1:
                return NumberButton.FIRST;
            case 2:
                return NumberButton.SECOND;
            case 3:
                calculateRectangle(clicked.get(0), clicked.get(1));
                return NumberButton.THIRD;
            default:
                
                List<Pair<Integer, Integer>> l = rectangle;
                l.addAll(clicked.subList(0, 2));
                var f = l.stream().min((x1, x2) -> (x1.getX() - x2.getX()) + (x1.getY() - x2.getY()) ).get();  //min
                var s = l.stream().max((x1, x2) -> (x1.getX() - x2.getX()) + (x1.getY() - x2.getY()) ).get();

                var first = new Pair<Integer, Integer>(f.getX() - 1, f.getY() - 1);
                var second = new Pair<Integer, Integer>(s.getX() + 1, s.getY() + 1);

                if (first.getX() < 0 || first.getY() < 0 || second.getX() > size - 1 || second.getY() > size - 1) {
                    over = true;
                }
                
                calculateRectangle(first, second);

                // var first = clicked.get(0);
                // var second = clicked.get(1);

                //count++;

                // if (first.getX() < second.getX() && first.getY() < second.getY()) {
                //     calculateRectangle(new Pair<Integer, Integer>(first.getX() - count, first.getY() - count),
                //             new Pair<Integer, Integer>(second.getX() + count, second.getY() + count));
                // } else {
                //     calculateRectangle(new Pair<Integer, Integer>(first.getX() + count, first.getY() + count),
                //             new Pair<Integer, Integer>(second.getX() - count, second.getY() - count));
                // }
                return NumberButton.OTHER;
        }
    }

    private void calculateRectangle(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
        Pair<Integer, Integer> min = new Pair<>(Math.min(first.getX(), second.getX()), Math.min(first.getY(), second.getY()));
        Pair<Integer, Integer> max = new Pair<>(Math.max(first.getX(), second.getX()), Math.max(first.getY(), second.getY()));

        for (int i = min.getX(); i <= max.getX(); i++) {
            for (int j = min.getY(); j <= max.getY(); j++) {
                rectangle.add(new Pair<Integer,Integer>(i, j));
            }
        }
        rectangle.removeAll(clicked.subList(0, 2));
    }


    @Override
    public boolean isZero(int x, int y) {
        return rectangle.contains(new Pair<>(x, y));
    }

    @Override
    public boolean isOver() {
        return over;
    }
}
