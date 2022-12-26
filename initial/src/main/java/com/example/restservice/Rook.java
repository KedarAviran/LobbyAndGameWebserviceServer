package com.example.restservice;

public class Rook extends ChessPiece
{
    public Rook(String color)
    {
        this.color=color;
    }

    public boolean checkLegalMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
    {
        if(fromY != toY && fromX!=toX)
            return false;
        if(fromY == toY)
        {
            int dirX = (fromX-toX)>0 ? 1 :  -1;
            int i=1;
            while (fromX+i*dirX != toX)
            {
                if (board[toY][fromX + i * dirX] != null)
                    return false;
                i++;
            }
        }
        if(fromX==toX)
        {
            int dirY = (fromY-toY)>0 ? 1 :  -1;
            int i=1;
            while (fromY+i*dirY != toY)
            {
                if (board[fromY + i * dirY][toX] != null)
                    return false;
                i++;
            }
        }
        return true;
    }
}
