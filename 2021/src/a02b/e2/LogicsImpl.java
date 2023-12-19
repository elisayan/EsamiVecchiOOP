package a02b.e2;

import java.util.*;

public class LogicsImpl implements Logics{

    public static enum Direction {
        UP(0,-1), LEFT(-1,0), RIGHT(1,0);

        int x;
        int y;
        Direction (int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    int size;
    private List<Pair<Integer,Integer>> history = new LinkedList<>();
    private Map<Pair<Integer,Integer>, Direction> letters= new HashMap<>();
    private Pair<Integer,Integer> position;
    private Direction direction = Direction.UP;
    private Random random = new Random();
    
    public LogicsImpl(int size){
        this.size=size;
        position = new Pair<>(random.nextInt(this.size), size - 1);
        for (int i = 0; i < 20; i++) {
            letters.put(new Pair<Integer,Integer>(this.random.nextInt(this.size), this.random.nextInt(this.size)), Direction.values()[random.nextInt(2)]);
        }
    }

    @Override
    public Pair<Integer, Integer> move() {
        //if(history.isEmpty()){            
            
        //}
        return null;
    }

    @Override
    public void setLetters() {
        

    }
    
}
