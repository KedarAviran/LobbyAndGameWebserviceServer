package com.example.restservice;

public class Room
{
    private int roomID;
    private String player1;
    private String player2;
    private String gameType;
    private Game game;

    public Room(int roomID,String player1,String gameType)
    {
        this.roomID=roomID;
        this.player1=player1;
        this.gameType=gameType;
        //if(gameType.equals("Checkers"))
        game=new CheckersGame();
    }
    public boolean setMove(String move)
    {
        return game.setMove(move);
    }
    public String getLastMove()
    {
        return game.getLastMove();
    }
    public boolean isFull()
    {
        return player2!=null;
    }
    public boolean isEmpty()
    {
        return player2==null && player1==null;
    }
    public void playerLeave(String player)
    {
        if(player1.equals(player))
            setPlayer1(null);
        if(player2.equals(player))
            setPlayer2(null);
    }
    public void setPlayer1(String player1)
    {
        this.player1 = player1;
    }
    public void setPlayer2(String player2)
    {
        this.player2=player2;
    }
    public int getRoomID()
    {
        return roomID;
    }
    public String getPlayer1()
    {
        return player1;
    }
    public String getPlayer2()
    {
        return player2;
    }
    public String getGameType()
    {
        return gameType;
    }
    public String toString()
    {
        return roomID+","+player1+","+player2+","+gameType+"";
    }
}
