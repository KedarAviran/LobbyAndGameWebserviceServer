package com.example.restservice;

public abstract class Game
{
    protected String lastMove;
    protected String moveHistory;

    public abstract String getLastMove();

    public abstract boolean setMove(String move);

    public abstract boolean isGameOver();
    public abstract String getMoveHistory();
    public abstract boolean getTurn();
}
