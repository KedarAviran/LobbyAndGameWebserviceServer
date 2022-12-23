package com.example.restservice;

public class ChessGame extends Game {
    private ChessPiece[][] board = new ChessPiece[9][9];

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
        if (board[fromY][fromX] == null || fromX==toX&&fromY==toY)
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
