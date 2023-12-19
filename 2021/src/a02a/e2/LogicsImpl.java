package a02a.e2;

import java.util.*;
import java.util.random.*;

public class LogicsImpl implements Logics{

    private static enum Direction {
		UP(0,-1), RIGHT(1,0), DOWN(0,1), LEFT(-1,0);
		
		int x;
		int y;
		Direction(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		Direction next() {
			return Direction.values()[(this.ordinal()+1) % Direction.values().length];
		}
	}

    private int size;
    
    private List<Pair<Integer, Integer>> open = new LinkedList<>();
    private Direction direction = Direction.UP;

    LogicsImpl(int size) {
        this.size = size;
    }
    
    @Override
    public Optional<Pair<Integer, Integer>> next() {
        int i = 0;
        Pair<Integer, Integer> previous;
        if (open.isEmpty()) {
            Random random = new Random();
            open.add(new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size)));
            previous = open.get(i++);
            return Optional.of(previous);
        }
        previous = open.get(i++);
        return Optional.of(new Pair<Integer, Integer>(previous.getX() + direction.x, previous.getY() + direction.y));
    }
}
