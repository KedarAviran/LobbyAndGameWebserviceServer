package com.example.restservice;

public class King extends ChessPiece
{
    public King(String color)
    {
        this.color=color;
    }
    public boolean checkLegalMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board)
    {
        return (Math.abs(fromY-toY)<=1&&Math.abs(fromX-toX)<=1);
    }
}
