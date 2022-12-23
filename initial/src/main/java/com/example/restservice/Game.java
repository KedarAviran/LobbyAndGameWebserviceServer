package com.example.restservice;

public abstract class Game
{
    protected String lastMove;
    public abstract String getLastMove();
    public abstract boolean setMove(String move);
}
