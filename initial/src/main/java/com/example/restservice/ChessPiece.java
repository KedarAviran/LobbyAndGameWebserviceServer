package com.example.restservice;

public abstract class ChessPiece
{
    protected String color;
    public abstract boolean checkLegalMove(int fromY,int fromX,int toY,int toX,ChessPiece[][] board);
}
