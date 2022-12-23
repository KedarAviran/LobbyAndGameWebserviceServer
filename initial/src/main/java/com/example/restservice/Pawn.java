package com.example.restservice;

public class Pawn extends ChessPiece
{
    public Pawn(String color)
    {
        this.color=color;
    }
    public boolean checkLegalMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
    {
        int dirY = color.equals("White") ? -1 :  1;
        if(fromX==toX && toY-fromY ==dirY && board[toY][toX]==null)
            return true;
        if(board[toY][toX]!=null)
            return Math.abs(fromX-fromY) ==1 && toY-fromY ==dirY;
        return false;
    }
}
