package board;

import pieces.Pieces;

public class Move {
    public int fromCol, fromRow;
    public int toCol, toRow;
    public Pieces movedPiece;
    public Pieces capturedPiece;
    private int heuristicValue;

    public Move(int fromCol, int fromRow, int toCol, int toRow, Pieces movedPiece, Pieces capturedPiece) {
        this.fromCol = fromCol;
        this.fromRow = fromRow;
        this.toCol = toCol;
        this.toRow = toRow;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    @Override
    public String toString() {
        return pieceToString(movedPiece) + " " + coordsToString(fromCol, fromRow) + " -> " + coordsToString(toCol, toRow)
                + (capturedPiece != null ? " x " + pieceToString(capturedPiece) : "");
    }

    private String pieceToString(Pieces piece) {
        if (piece == null) return "";
        String name = piece.getClass().getSimpleName();
        // Optional: shorten names
        if (name.contains("Pawn")) return "Pawn";
        if (name.contains("Knight")) return "Knight";
        if (name.contains("Bishop")) return "Bishop";
        if (name.contains("Rook")) return "Rook";
        if (name.contains("Queen")) return "Queen";
        if (name.contains("King")) return "King";
        return name;
    }

    private String coordsToString(int col, int row) {
        char colChar = (char) ('A' + col);
        int rowNum = 8 - row; // assuming row 0 is top of the board
        return "" + colChar + rowNum;
    }
    public int getHeuristicValue() {
        return heuristicValue;
    }
    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }
}

