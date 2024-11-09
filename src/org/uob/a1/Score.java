package org.uob.a1;

public class Score {
    private final int PUZZLE_VALUE = 10;
    private double currentScore;
    private int startingScore;
    private int noRoomsVisited;
    private int noPuzzlesSolves;

    public Score(int startingScore) {
        this.startingScore = startingScore;
    }

    public void visitRoom() {
        noRoomsVisited++;
    }

    public void solvePuzzle() {
        noPuzzlesSolves++;
    }

    public double getScore() {
        currentScore = startingScore - noRoomsVisited + (PUZZLE_VALUE * noPuzzlesSolves);
        return currentScore;
    }

}