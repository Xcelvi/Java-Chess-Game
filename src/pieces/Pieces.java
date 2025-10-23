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

    //Initiates polymorphism for pieces
    public static Pieces getPiece(String pieceName, int col, int row, String[][] board) {
        return switch (pieceName){
            case "WhitePawn" -> new  WhitePawn(col, row);
            case "WhiteBishop" -> new WhiteBishop(col, row, board);
            case "WhiteRook" -> new WhiteRook(col, row, board);
            case "WhiteKing" -> new WhiteKing(col, row);
            case "WhiteQueen" -> new WhiteQueen(col, row, board);
            case "WhiteKnight" -> new WhiteKnight(col, row);
            case "BlackPawn" -> new BlackPawn(col, row);
            case "BlackBishop" -> new BlackBishop(col, row, board);
            case "BlackRook" -> new BlackRook(col, row, board);
            case "BlackKing" -> new BlackKing(col, row);
            case "BlackQueen" -> new BlackQueen(col, row, board);
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
    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveWhite(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff)  return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;

        while (colLocation != targetCol && rowLocation != targetRow ){
            if (!board[rowLocation][colLocation].contains("-")){
                System.out.println("Boardsky: " + board[rowLocation][colLocation]);
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("White");
    }
    //Implements Vertical and horizontal movements for rook/ queen of white
    public boolean horizontalVerticalMoveWhite(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0){
            colStep = -1;
        } else if (colLocation - targetCol < 0){
            colStep = 1;
        } else if (rowLocation - targetRow > 0){
            rowStep = -1;
        } else if (rowLocation - targetRow < 0){
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)){
            if (!board[rowLocation][colLocation].contains("-")){
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("White");
    }
    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveBlack(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff)  return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;

        while (colLocation != targetCol && rowLocation != targetRow ){
            if (!board[rowLocation][colLocation].contains("-")){
                System.out.println("Boardsky: " + board[rowLocation][colLocation]);
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("Black");
    }
    //Implements Vertical and horizontal movements for rook/ queen of Black
    public boolean horizontalVerticalMoveBlack(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0){
            colStep = -1;
        } else if (colLocation - targetCol < 0){
            colStep = 1;
        } else if (rowLocation - targetRow > 0){
            rowStep = -1;
        } else if (rowLocation - targetRow < 0){
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)){
            if (!board[rowLocation][colLocation].contains("-")){
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("Black");
    }
}


