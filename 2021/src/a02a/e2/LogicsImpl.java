package a02a.e2;

import java.util.*;

public class LogicsImpl implements Logics{

    int size;
    private List<Pair<Integer, Integer>> open = new LinkedList<>();

    LogicsImpl(int size) {
        this.size = size;
    }
    
    @Override
    public boolean hit(int x, int y) {
        if (open.isEmpty()) {
            open.add(new Pair<Integer,Integer>(x, y));
            return true;
        }
        open.add(new Pair<Integer,Integer>(x, y--));
        return false;

    }

    @Override
    public boolean isOpened(int x, int y) {
        return open.contains(new Pair<>(x,y));
    }
   
    
}
