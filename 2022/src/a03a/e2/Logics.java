package a03a.e2;

public interface Logics {
    boolean humanMove(int x, int y, Pair<Integer, Integer> human);

    Pair<Integer, Integer> computerMove(Pair<Integer, Integer> computer);

    boolean computerWins(Pair<Integer, Integer> computer, Pair<Integer, Integer> human);

    boolean humanWins(Pair<Integer, Integer> computer);
}
