package a03a.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private Pair<Integer, Integer> humanDestination;
    private Pair<Integer, Integer> computerDestination;
    private int size;
    private Random random = new Random();

    public LogicsImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean humanMove(int x, int y, Pair<Integer, Integer> human) {
        this.humanDestination = new Pair<Integer, Integer>(x, y);
        return humanDestination.getX().equals(human.getX()) || humanDestination.getY().equals(human.getY());
    }

    @Override
    public Pair<Integer, Integer> computerMove(Pair<Integer, Integer> computer) {
        computerDestination = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));

        while (computerDestination.equals(computer) && (!computerDestination.getX().equals(computer.getX())
                || !computerDestination.getY().equals(computer.getY()))) {
            computerDestination = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
        }

        return computerDestination;
    }

    @Override
    public boolean computerWins(Pair<Integer, Integer> computer, Pair<Integer, Integer> human) {
        if (computer.getX().equals(human.getX()) || computer.getY().equals(human.getY())) {
            this.computerDestination = human;
            System.out.println("computer wins");
            return true;
        }

        return false;
    }

    @Override
    public boolean humanWins(Pair<Integer, Integer> computer) {
        if (humanDestination.equals(computer)) {
            System.out.println("human wins");
            return true;
        }
        return false;
    }
}
