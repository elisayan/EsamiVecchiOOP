package a01a.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    private List<Pair<Integer, Integer>> asterischs = new LinkedList<>();
    private List<Pair<Integer, Integer>> history = new LinkedList<>();


    @Override
    public void hit(int x, int y) {
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(x, y);
        history.add(pair);
        if (asterischs.contains(pair)) {
            asterischs.remove(pair);
        } else {
            asterischs.add(pair);
        }
    }

    @Override
    public boolean isOver() {
        int lenght = history.size();
        return lenght >= 3
                && history.subList(lenght - 3, lenght)
                        .equals(asterischs.subList(asterischs.size() - 3, asterischs.size()))
                && isDiagonal(Set.of(history.subList(lenght - 3, lenght)));
    }

    private boolean isDiagonal(Set<List<Pair<Integer, Integer>>> lastThree) {
        List<Pair<Integer,Integer>> diagonal = new LinkedList<>();

        for (List<Pair<Integer,Integer>> list : lastThree) {
            for (Pair<Integer,Integer> pair : list) {
                diagonal.add(new Pair<Integer,Integer>(pair.getX()+1,pair.getY()+1));
                diagonal.add(new Pair<Integer,Integer>(pair.getX()+1,pair.getY()-1)); 
                diagonal.add(new Pair<Integer,Integer>(pair.getX()-1,pair.getY()+1)); 
                diagonal.add(new Pair<Integer,Integer>(pair.getX()-1,pair.getY()-1));   
            }
        }

        return lastThree.stream().anyMatch(p->diagonal.containsAll(p));
    }

    @Override
    public boolean isHitted(int x, int y) {
        return asterischs.contains(new Pair<>(x, y));
    }
}
