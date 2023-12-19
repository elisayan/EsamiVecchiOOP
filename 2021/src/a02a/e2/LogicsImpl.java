package a02a.e2;

import java.util.*;

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
        if (open.isEmpty()) {
            Random random = new Random();
            open.add(new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size)));
            return Optional.of(open.get(0));
        }
        Pair<Integer, Integer> last = open.get(open.size() - 1);
        for (int i = 0; i < 4; i++) {

            Pair<Integer, Integer> move = new Pair<Integer, Integer>(last.getX() + direction.x,
                    last.getY() + direction.y);
            if (!open.contains(move) && move.getX() >= 0 && move.getY() >= 0 && move.getX() < size
                    && move.getY() < size) {
                open.add(move);
                return Optional.of(move);
            }
            direction = direction.next();

        }
        return Optional.empty();
    }
}
