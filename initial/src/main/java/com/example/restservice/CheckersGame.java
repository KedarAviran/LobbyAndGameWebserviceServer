
package com.example.restservice;

public class CheckersGame extends Game
{
    class piece
    {
        State state;
        boolean isKing;

        public piece(State state, boolean isKing)
        {
            this.state = state;
            this.isKing = isKing;
        }
    }

    enum State
    {
        black,
        white,
        empty,
    }

    private piece[][] board;
    private boolean turn; // True = white || False = black
    private int whitePieces = 12;
    private int blackPieces = 12;

    public CheckersGame()
    {
        board = new piece[8][8];
        turn = true;
        setupBoard();
    }
    public boolean getTurn()
    {
        return turn;
    }
    private void setupBoard()
    {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
            {
                board[i][j] = new piece(State.empty, false);
                if (i < 3 && (j + i) % 2 == 1)
                    board[i][j] = new piece(State.black, false);
                if (i > 4 && (j + i) % 2 == 1)
                    board[i][j] = new piece(State.white, false);
            }
    }

    private boolean isLegalMove(int fromX, int fromY, int toX, int toY)
    {
        if ((fromX + fromY) % 2 == 0 || (toX + toY) % 2 == 0 || toX == fromX)
            return false;
        if (fromX < 0 || fromX > 7 || fromY < 0 || fromY > 7 || toX < 0 || toX > 7 || toY < 0 || toY > 7)
            return false;
        if (board[fromY][fromX].state == State.empty || board[toY][toX].state != State.empty)
            return false;
        if (Math.abs(fromX - toX) != Math.abs(fromY - toY) || Math.abs(fromX - toX) > 2)
            return false;
        if (Math.abs(fromX - toX) == 1 && board[fromY][fromX].isKing)
            return true;
        int dirY = (fromY - toY) / Math.abs(fromY - toY); // 1 is up -1 is down
        if (board[fromY][fromX].state == State.black && dirY == 1 && !board[fromY][fromX].isKing)
            return false;
        if (board[fromY][fromX].state == State.white && dirY == -1 && !board[fromY][fromX].isKing)
            return false;

        if (Math.abs(fromX - toX) == 1)
            return true;

        int midX = (toX + fromX) / 2;
        int midY = (toY + fromY) / 2;
        if (board[midY][midX].state == State.empty || board[midY][midX].state == board[fromY][fromX].state)
            return false;
        if (board[midY][midX].state == State.black)
            blackPieces--;
        else
            whitePieces--;
        return true;

    }

    @Override
    public String getLastMove()
    {
        if (isGameOver())
            return "true";
        return lastMove;
    }
    public String getMoveHistory()
    {
        return moveHistory;
    }
    @Override
    public boolean setMove(String move)
    {
        int fromY = Integer.parseInt(move.charAt(0) + "");
        int fromX = Integer.parseInt(move.charAt(1) + "");
        int toY = Integer.parseInt(move.charAt(2) + "");
        int toX = Integer.parseInt(move.charAt(3) + "");
        if ((turn && board[fromY][fromX].state != State.white) || (!turn && board[fromY][fromX].state != State.black))
            return false;
        if (!isLegalMove(fromX, fromY, toX, toY))
            return false;
        if(moveHistory==null || moveHistory.equals(""))
            moveHistory=move;
        else
            moveHistory=moveHistory+"."+move;
        turn = !turn;
        lastMove = move;
        int midX = (toX + fromX) / 2;
        int midY = (toY + fromY) / 2;
        if (Math.abs(fromX - toX) == 2)
            board[midY][midX].state = State.empty;
        board[toY][toX].state = board[fromY][fromX].state;
        board[fromY][fromX].state = State.empty;
        return true;
    }

    public boolean isGameOver()
    {
        return (blackPieces == 0 || whitePieces == 0);
    }
}
