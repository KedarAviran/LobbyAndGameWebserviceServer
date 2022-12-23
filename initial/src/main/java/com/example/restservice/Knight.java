package com.example.restservice;

public class Knight extends ChessPiece
{
    public Knight(String color)
    {
        this.color=color;
    }
    public boolean checkLegalMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
    {
        return (Math.abs(fromY-toY)==2&&Math.abs(fromX-toX)==1 || Math.abs(fromY-toY)==1&&Math.abs(fromX-toX)==2);
    }
}
