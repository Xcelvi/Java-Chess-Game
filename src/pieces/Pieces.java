package pieces;

import board.ChessBoard;
import pieces.black.*;
import pieces.white.*;

public abstract class Pieces extends ChessBoard {
    private int col;
    private int row;
    protected String[][] board;
    public Pieces(int col, int row, String[][] board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }
    public Pieces(int col, int row) {
        this.col = col;
        this.row = row;
    }


    public static Pieces getPiece(String pieceName, int col, int row, String[][] board) {
        return switch (pieceName){
            case "WhitePawn" -> new  WhitePawn(col, row);
            case "WhiteBishop" -> new WhiteBishop(col, row, board);
            case "WhiteRook" -> new WhiteRook(col, row);
            case "WhiteKing" -> new WhiteKing(col, row);
            case "WhiteQueen" -> new WhiteQueen(col, row);
            case "WhiteKnight" -> new WhiteKnight(col, row);
            case "BlackPawn" -> new BlackPawn(col, row);
            case "BlackBishop" -> new BlackBishop(col, row);
            case "BlackRook" -> new BlackRook(col, row);
            case "BlackKing" -> new BlackKing(col, row);
            case "BlackQueen" -> new BlackQueen(col, row);
            case "BlackKnight" -> new BlackKnight(col, row);
            default -> throw new IllegalStateException("Unexpected value: " + pieceName);
        };
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public abstract boolean isValidMove(int targetCol, int targetRow);
}

