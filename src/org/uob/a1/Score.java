package org.uob.a1;

public class Score {
    private int startingScore;
    private double currentScore;
    private int noRoomsVisited;
    private int noPuzzlesSolves;
    private final int PUZZLE_VALUE = 10;

    public Score(int startingScore) {
        this.startingScore = startingScore;
    }
    public void visitRoom(){
        noRoomsVisited++;
    }
    public void solvePuzzle(){
        noPuzzlesSolves++;
    }
    public double getScore() {
        currentScore = startingScore - noRoomsVisited + (PUZZLE_VALUE * noPuzzlesSolves);
        return currentScore;
    }

}