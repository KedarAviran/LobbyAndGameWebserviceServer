package com.example.restservice;

public class Bishop extends ChessPiece
{
    public Bishop(String color)
    {
        this.color=color;
    }
    public boolean checkLegalMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
    {
        if(Math.abs(fromX-toX) != Math.abs(fromY-toY) || Math.abs(fromX-toX) == 0)
            return false;
        int dirX = (fromX-toX)>0 ? -1 :  1;
        int dirY = (fromY-toY)>0 ? -1 :  1;
        int i=1;
        while (fromX+i*dirX != toX)
        {
            if(board[fromX+i*dirX][fromY+i*dirY]!=null)
                return false;
            i++;
        }
        return true;
    }
}