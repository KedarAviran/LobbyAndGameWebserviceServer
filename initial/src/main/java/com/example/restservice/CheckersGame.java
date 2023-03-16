
package com.example.restservice;

public class CheckersGame extends Game
{
    class piece
    {
        State state;
        boolean isKing;
        public piece(State state,boolean isKing)
        {
            this.state=state;
            this.isKing=isKing;
        }
    }
    enum State
    {
        black,
        white,
        empty,
    }
    private piece [][] board;
    private boolean turn; // True = white || False = black
    public CheckersGame()
    {
        board = new piece[8][8];
        turn = true;
        setupBoard();
    }
    private void setupBoard()
    {
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board.length;j++)
            {
                board[i][j] = new piece(State.empty,false) ;
                if(i<3 && (j+i)%2==0)
                    board[i][j] = new piece(State.white,false);
                if(i>4 && (j+i)%2==0)
                    board[i][j] = new piece(State.black,false);
            }
    }
    private boolean isLegalMove(int fromX ,int fromY ,int toX,int toY)
    {
        if((fromX+fromY)%2==1|| (toX+toY)%2==1 || toX==fromX)
            return false;
        if(fromX<0 || fromX>7 ||fromY<0 || fromY>7 ||toX<0 || toX>7 ||toY<0 || toY>7)
            return false;
        if(board[fromX][fromY].state == State.empty || board[toX][toY].state != State.empty)
            return false;
        if(Math.abs(fromX-toX) != Math.abs(fromY-toY) || Math.abs(fromX-toX) >2)
            return false;
        if(Math.abs(fromX-toX) == 1 && board[fromX][fromY].isKing)
            return true;
        int dirY = fromY-toY / Math.abs(fromY-toY); // 1 is up -1 is down
        if(board[fromX][fromY].state == State.black && dirY == 1 && !board[fromX][fromY].isKing)
            return false;
        if(board[fromX][fromY].state == State.white && dirY == -1 && !board[fromX][fromY].isKing)
            return false;

        if(Math.abs(fromX-toX) == 1)
            return true;

        int midX = (toX + fromX) /2;
        int midY = (toY + fromY) /2;
        if(board[midX][midY].state == State.empty || board[midX][midY].state == board[fromX][fromY].state)
            return false;

        return true;

    }
    @Override
    public String getLastMove()
    {
        return lastMove;
    }

    @Override
    public boolean setMove(String move)
    {
        int fromX = move.charAt(0);
        int fromY = move.charAt(1);
        int toX = move.charAt(2);
        int toY = move.charAt(3);
        if((turn && board[fromX][fromY].state != State.white) || (!turn && board[fromX][fromY].state != State.black))
            return false;
        if(!isLegalMove(fromX,fromY,toX,toY))
            return false;
        turn= !turn;
        lastMove = move;
        int midX = (toX + fromX) /2;
        int midY = (toY + fromY) /2;
        if(Math.abs(fromX-toX) == 2)
            board[midX][midY].state = State.empty;
        return true;
    }
}
