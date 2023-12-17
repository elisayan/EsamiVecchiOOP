package a01c.e2;

import java.util.*;

public class LogicImpl implements Logic {

    List<Pair<Integer, Integer>> press = new LinkedList<>();
    List<Pair<Integer, Integer>> history = new LinkedList<>();

    @Override
    public boolean hit(int x, int y) {
        history.add(new Pair<Integer, Integer>(x, y));
        // if (press.isEmpty()) {
        //     // press.add(new Pair<Integer, Integer>(x, y));
        //     return true;
        // } else {
            for (int c = 0; c < history.size(); c++) {
            if (c - 1 < 0) {
                // if (history.get(c).getX() == x) {
                //     for (int i = (y < history.get(c).getY()) ? y
                //             : history.get(c).getY(); i <= ((y < history.get(c).getY()) ? history.get(c).getY()
                //                     : y); i++) {
                //         press.add(new Pair<Integer, Integer>(x, i));
                //     }
                //     return true;
                // } else if (y == history.get(c).getY()) {
                //     for (int i = (x < history.get(1).getX()) ? x
                //             : history.get(c).getX(); i <= ((x < history.get(c).getX()) ? history.get(c - 1).getX()
                //                     : x); i++) {
                //         press.add(new Pair<Integer, Integer>(i, y));
                //     }
                // }
                press.add(new Pair<Integer,Integer>(x, y));
                return true;
            } else if (history.get(c - 1).getX() == x) {
                for (int i = (y < history.get(c - 1).getY()) ? y
                        : history.get(c - 1).getY(); i <= ((y < history.get(c - 1).getY())
                                ? history.get(c - 1).getY()
                                : y); i++) {
                    press.add(new Pair<Integer, Integer>(x, i));
                }
                return true;
            } else if (y == history.get(c - 1).getY()) {
                for (int i = (x < history.get(c - 1).getX()) ? x
                        : history.get(c - 1).getX(); i <= ((x < history.get(c - 1).getX())
                                ? history.get(c - 1).getX()
                                : x); i++) {
                    press.add(new Pair<Integer, Integer>(i, y));
                }
                return true;
            }
        }
        //}

        return false;
    }

    @Override
    public boolean isHitted(int x, int y) {
        return press.contains(new Pair<>(x, y));
    }

}
