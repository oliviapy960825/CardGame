package edu.udel.dianali.cardgame;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import edu.udel.dianali.gameframework.Game;
import edu.udel.dianali.gameframework.Position;

public class CardGame extends Game {
    private static final int SIZE=6;
    private Card[][] board;
    private int score;
    private float timer;
    private Position initPosition;
    public static final float WORLD_WIDTH=6;
    public static float WORLD_HEIGHT=6;

    /*Constructor

     */
    public CardGame(Card[][] board, int score, int timer) {
        this.board = board;
        this.score = score;
        this.timer = timer;
    }

    /**
     * Check if one row/col have suits/types are the same.
     * Return a number which represent how many pairs are the same
     * (this number will be used in the function updateScore)
     * <p>
     * Iterate over the board
     *
     * @return
     */
    public Card[][] getCurrentBoard() {
        return board;
    }

    public Position getInitPosition() {
        return initPosition;
    }

    public void setInitPosition(Position p) {
        initPosition = p;
    }

    public int rowColor(int row) {
        int count = 0;
        int max = 0;
        for (int col = 0; col < 5; col++) {
            if (board[row][col].sameColor(board[row][col + 1])) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }
        return max + 1;
    }

    public int colColor(int col) {
        int count = 0;
        int max = 0;
        for (int row = 0; row < 5; row++) {
            if (board[row][col].sameColor(board[row + 1][col])) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }
        return max + 1;
    }

    public int rowNumber(int row) {
        int count = 0;
        int max = 0;
        for (int col = 0; col < 5; col++) {
            if (board[row][col].sameNumber(board[row][col + 1])) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }
        return max + 1;
    }

    public int colNumber(int col) {
        int count = 0;
        int max = 0;
        for (int row = 0; row < 5; row++) {
            if (board[row][col].sameNumber(board[row + 1][col])) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }
        return max + 1;
    }


    /**
     * Create a new game board with all cards filled
     */
    public void newGameBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Card();
            }
        }
    }

    public void updateScore() {

        for (int startRow = 0; startRow < board.length; startRow++) {
            if (this.rowColor(startRow) == 3) {
                score += 20;
            }
            if (this.rowColor(startRow) > 3) {
                score += 50;
            }
            if (this.rowNumber(startRow) == 3) {
                score += 50;
            }
            if (this.rowNumber(startRow) > 3) {
                score += 200;
            } else {
                score += 0;
            }
        }
        for (int startCol = 0; startCol < board[0].length; startCol++) {
            if (this.colColor(startCol) == 3) {
                score += 20;
            }
            if (this.colColor(startCol) > 3) {
                score += 50;
            }
            if (this.colNumber(startCol) == 3) {
                score += 50;
            }
            if (this.colNumber(startCol) > 3) {
                score += 200;
            } else {
                score += 0;
            }
        }
    }

    public void updateBoard() {
   /* replace cards that are in a suit or of the same number with new cards*/
        int col = 0;
        int row = 0;
        for (int startRow = 0; startRow < 6; startRow++) {
            if (rowColor(startRow) >= 3) {
                int number = rowColor(startRow);
                for (int startCol = 0; startCol < 6 - number; startCol++) {
                    for (int endCol = startCol; endCol < startCol + number - 1; endCol++) {
                        int count = 1;
                        if (board[startRow][endCol].sameColor(board[startRow][endCol + 1])) {
                            count++;
                        }
                        if (count == number) {
                            col = endCol - number;
                        }
                    }
                }
                for (int startCol = col; startCol < col + number; startCol++) {
                    board[startRow][startCol].newRandomCard();
                }
            }
            if (rowNumber(startRow) >= 3) {
                int number = rowNumber(startRow);
                for (int startCol = 0; startCol < 6 - number; startCol++) {
                    for (int endCol = startCol; endCol < startCol + number - 1; endCol++) {
                        int count = 1;
                        if (board[startRow][endCol].sameColor(board[startRow][endCol + 1])) {
                            count++;
                        }
                        if (count == number) {
                            col = endCol - number;
                        }

                    }
                }
                for (int startCol = col; startCol < col + number; startCol++) {
                    board[startRow][startCol].newRandomCard();
                }
            }

        }
        for (int startCol = 0; startCol < 6; startCol++) {
            if (colColor(startCol) >= 3) {
                int number = colColor(startCol);
                for (int startRow = 0; startRow < 6 - number; startRow++) {
                    for (int endRow = startRow; endRow < startRow + number - 1; endRow++) {
                        int count = 1;
                        if (board[endRow][startCol].sameColor(board[endRow + 1][startCol])) {
                            count++;
                        }
                        if (count == number) {
                            row = endRow - number;
                        }

                    }
                }
                for (int startRow = row; startRow < row + number; startRow++) {
                    board[startRow][startCol].newRandomCard();
                }
            }
            if (colNumber(startCol) >= 3) {
                int number = colNumber(startCol);
                for (int startRow = 0; startRow < 6 - number; startRow++) {
                    for (int endRow = startRow; endRow < startRow + number - 1; endRow++) {
                        int count = 1;
                        if (board[endRow][startCol].sameColor(board[endRow + 1][startCol])) {
                            count++;
                        }
                        if (count == number) {
                            row = endRow - number;
                        }
                    }
                }
                for (int startRow = row; startRow < row + number; startRow++) {
                    board[startRow][startCol].newRandomCard();
                }
            }
        }
    }

    public int getScore() {
        return score;
    }

    /**
     * Returns a "visual" representation of a CharBoardGame board
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                buffer.append(board[i][j].toString());
            }
            buffer.append("\n");
        }
        return buffer.toString() + "\n" + score + "\n" + timer;
    }

    public void exchangePosition (Position p1, Position p2){
        Card c1=getCard(p1);
        Card c2=getCard(p2);
        int suitC1 = c1.getSuit();
        int numC1 = c1.getNumber();
        c1.setSuit(c2.getSuit());
        c1.setNumber(c2.getNumber());
        c2.setSuit(suitC1);
        c2.setNumber(numC1);
    }

    public void checkExchangeCard(Position p1, Position p2) {
        int initScore = getScore();
        exchangePosition(p1, p2);
        updateBoard();
        updateScore();
        if (getScore() == initScore) // score does not change
        {
            exchangePosition(p2, p1);  // switch back
        }
    }

    @Override
    protected void onUpdate(float time) {
        super.onUpdate(time);
        updateBoard();
        timer = timer - time;
        if(timer <= 0){
            end();
        }

    }

    public Card getCard(Position position) {
        return board[position.getRow()][position.getColumn()];
    }//position.getRow is the row number of the board
    // how to get board row fro position row


    public static CardGame makeDefaultStartGame()
    {
        Card[][] board = new Card[SIZE][SIZE];
        for(int i=0; i<SIZE; i++)
        {
            for(int j=0; j<SIZE; j++)
            {
                board[i][j] = new Card();
            }
        }
        return makeStartGame(board);
    }

    public static CardGame makeStartGame(Card[][] b)
    {
        return new CardGame(b, 0,30);
    }

    public String getStatus() {
        if (timer > 0) {
            return "Your time left is: " + (int)timer + " Score: " + getScore();
        } else if (score >= 1200) {
            return "You did great";
        }

        return "You lose";
    }
}
