package com.example.restservice;

public class ChessGame extends Game
{
    private ChessPiece[][] board = new ChessPiece[9][9];

    public ChessGame()
    {
        setupBoard();
    }
    public void setupBoard()
    {
        for(int i=1;i<=8;i++)
            board[2][i]=new Pawn("Black");
        for(int i=1;i<=8;i++)
            board[7][i]=new Pawn("White");
        board[1][1]=new Rook("Black");
        board[1][8]=new Rook("Black");
        board[8][1]=new Rook("White");
        board[8][8]=new Rook("White");

        board[1][2]=new Knight("Black");
        board[1][7]=new Knight("Black");
        board[8][2]=new Knight("White");
        board[8][7]=new Knight("White");

        board[1][3]=new Bishop("Black");
        board[1][6]=new Bishop("Black");
        board[8][3]=new Bishop("White");
        board[8][6]=new Bishop("White");

        board[1][4]=new Queen("Black");
        board[8][4]=new Queen("White");

        board[1][5]=new King("Black");
        board[8][5]=new King("White");
    }
    public String getLastMove()
    {
        return lastMove;
    }

    public boolean setMove(String move)
    {
        int fromY = Character.getNumericValue(move.charAt(0));
        int fromX = Character.getNumericValue(move.charAt(1));
        int toY = Character.getNumericValue(move.charAt(2));
        int toX = Character.getNumericValue(move.charAt(3));
        if (board[fromY][fromX] == null || fromX == toX && fromY == toY)
            return false;
        if (board[toY][toX] != null)
            if (board[fromY][fromX].color.equals(board[toY][toX].color))
                return false;
        if (!board[fromY][fromX].checkLegalMove(fromY, fromX, toY, toX, board))
            return false;
        board[toY][toX] = board[fromY][fromX];
        board[fromY][fromX] = null;
        this.lastMove = move;
        return true;
    }
}
