package com.example.restservice;

public class Queen extends ChessPiece
{
    public Queen(String color)
    {
        this.color=color;
    }
    public boolean isLegalRookMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
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
    public boolean isLegalBishopMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
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
    public boolean checkLegalMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
    {
        return isLegalRookMove(fromY,fromX,toY,toX,board) || isLegalBishopMove(fromY,fromX,toY,toX,board);
    }
}
