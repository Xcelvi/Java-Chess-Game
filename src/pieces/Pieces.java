package pieces;

import pieces.white.WhiteBishop;
import pieces.white.WhitePawn;

public abstract class Pieces {
    private int col;
    private int row;
    public Pieces(int col, int row) {
        this.col = col;
        this.row = row;
    }


    public static Pieces getPiece(String pieceName, int col, int row) {
        return switch (pieceName){
            case "WhitePawn" -> new  WhitePawn(col, row);
            case "WhiteBishop" -> new WhiteBishop(col, row);
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

