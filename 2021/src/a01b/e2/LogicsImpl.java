package a01b.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private List<Pair<Integer, Integer>> selected = new LinkedList<>();
    private List<Pair<Integer, Integer>> myButtons = new LinkedList<>();
    private Pair<Integer, Integer> first;

    @Override
    public ButtonNumber hit(int x, int y) {
        if (myButtons.isEmpty()) {
            first = new Pair<Integer, Integer>(x, y);
        }

        System.out.println("x: " + x + ", y: " + y);
        
        switch (myButtons.size()) {
            case 0:
                myButtons.add(first);
                return ButtonNumber.FIRST;
            case 1:
                if (first.getX() == x || first.getY() == y) {
                    myButtons.add(new Pair<Integer, Integer>(x, y));
                    return ButtonNumber.SECOND;
                }
                break;
            case 2:
                if (((first.getX().equals(myButtons.get(1).getX())) && first.getY() == y && first.getX() != x)
                        || ((first.getY().equals(myButtons.get(1).getY())) && first.getX() == x && first.getY() != y)) {
                    myButtons.add(new Pair<Integer, Integer>(x, y));
                    setAngle();
                    return ButtonNumber.THIRD;
                }
                break;
            default:
                break;
        }
        return null;
    }

    private void setAngle() {
        for (int k = 1; k < myButtons.size(); k++) {
            if (first.getX() == myButtons.get(k).getX()) {
                for (int i = (myButtons.get(k).getY() < first.getY()) ? myButtons.get(k).getY()
                        : first.getY(); i < ((myButtons.get(k).getY() < first.getY()) ? first.getY()
                                : myButtons.get(k).getY()); i++) {
                    selected.add(new Pair<Integer, Integer>(first.getX(), i));
                }
            } else if (first.getY() == myButtons.get(k).getY()) {
                for (int i = (myButtons.get(k).getX() < first.getX()) ? myButtons.get(k).getX()
                        : first.getX(); i < ((myButtons.get(k).getX() < first.getX()) ? first.getX()
                                : myButtons.get(k).getX()); i++) {
                    selected.add(new Pair<Integer, Integer>(i, first.getY()));
                }
            }
        }
        for (Pair<Integer,Integer> button : myButtons) {
            selected.remove(button);
        }
    }

    @Override
    public boolean isHitted(int x, int y) {
        return selected.contains(new Pair<>(x, y));
    }
}
