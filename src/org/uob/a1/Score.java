package org.uob.a1;

public class Score {
    private final int PUZZLE_VALUE = 10;
    private double currentScore;
    private int startingScore;
    private int noRoomsVisited = 0;
    private int noPuzzlesSolves = 0;

    //Constructor, sets the starting score
    public Score(int startingScore) {
        this.startingScore = startingScore;
    }

    public void visitRoom() {
        noRoomsVisited++;
    }

    public void solvePuzzle() {
        noPuzzlesSolves++;
    }

    //Calculate the score as per the document specification
    public double getScore() {
        currentScore = startingScore - (noRoomsVisited + (PUZZLE_VALUE * noPuzzlesSolves));
        return currentScore;
    }

}