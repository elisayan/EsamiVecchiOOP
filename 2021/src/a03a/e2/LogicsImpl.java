package a03a.e2;

import java.util.*;

public class LogicsImpl implements Logics{

    int size;
    List<Pair<Integer,Integer>> selected = new LinkedList<>();
    Pair<Integer,Integer> lastSelected;

    LogicsImpl (int size){
        this.size=size;
    }

    @Override
    public void hit(int x, int y) {
        lastSelected = new Pair<Integer, Integer>(x, y);
        Pair<Integer,Integer> diagonal = new Pair<Integer,Integer>((size-1)-x, (size-1)-y);
        for (int i = x; i < diagonal.getY(); i++) {
            selected.add(new Pair<Integer,Integer>(x, i));
        }
        for (int i = y; i < diagonal.getX(); i++) {
            selected.add(new Pair<Integer,Integer>(i, y));
        }
        for (int i = y; i < diagonal.getY(); i++) {
            selected.add(new Pair<Integer,Integer>(diagonal.getX(), i));
        }
        for (int i = x; i < diagonal.getX(); i++) {
            selected.add(new Pair<Integer,Integer>(i, diagonal.getY()));
        }
        selected.add(diagonal);
    }

    @Override
    public boolean isHitted(int x, int y) {
        return selected.contains(new Pair<Integer,Integer>(x,y));
    }
    
    @Override
    public boolean isOver(){
        return lastSelected.getX()>= size/2-1 || lastSelected.getX()>= size/2-1;
    }
}
