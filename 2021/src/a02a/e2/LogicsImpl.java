package a02a.e2;

import java.util.*;

public class LogicsImpl implements Logics{

    private static enum Direction{
        UP(0,-1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0);

        int x;
        int y;
        Direction(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    private int size;
    private List<Pair<Integer,Integer>> history = new LinkedList<>();
    private Random random = new Random();
    private Direction direction = Direction.UP;

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public Optional<Pair<Integer, Integer>> next() {
        if(history.isEmpty()){
            history.add(new Pair<>(random.nextInt(size), random.nextInt(size)));
            return Optional.of(history.get(0));
        }
        Pair<Integer, Integer> last = history.get(history.size() - 1);
        for (int i = 0; i < 4; i++) {
            Pair<Integer,Integer> next= new Pair<Integer,Integer>(last.getX() + direction.x, last.getY() + direction.y);
            if (!history.contains(next) && next.getX() >= 0 && next.getY() >= 0 && next.getX() < size
                    && next.getY() < size) {
                history.add(next);
                return Optional.of(next);
            }
            int randomIndex = new Random().nextInt(Direction.values().length);
            direction = Direction.values()[randomIndex];
        }

        return Optional.empty();
    }
    
}
