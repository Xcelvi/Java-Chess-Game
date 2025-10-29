package pieces;

import board.BoardControl;
import board.ChessBoard;
import pieces.black.*;
import pieces.white.*;

import java.util.ArrayList;


public abstract class Pieces extends BoardControl {
    private int col;
    private int row;
    protected ArrayList<String> pieceVision = new ArrayList<>();
    protected boolean hasMoved;
    protected Pieces[][] board;


    public Pieces(int col, int row, Pieces[][] board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setPieceVision(ArrayList<String> pieceVision) {
        this.pieceVision = pieceVision;
    }
    public ArrayList<String> getPieceFullVision(int  targetCol, int targetRow) {
        return pieceVision;
    }

    public abstract boolean isValidMove(int targetCol, int targetRow);
    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveWhite(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        if (colDiff != rowDiff) return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;

        while (colLocation != targetCol && rowLocation != targetRow) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        if (board[rowLocation][colLocation] != null) {
            Pieces piece = board[rowLocation][colLocation];
            return !piece.getClass().getSimpleName().contains("White");
        }else return true;
    }
    //Implements Vertical and horizontal movements for rook/ queen of white
    public boolean horizontalVerticalMoveWhite(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        if (colDiff > 0 && rowDiff > 0) return false;
        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0) {
            colStep = -1;
        } else if (colLocation - targetCol < 0) {
            colStep = 1;
        } else if (rowLocation - targetRow > 0) {
            rowStep = -1;
        } else if (rowLocation - targetRow < 0) {
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        if (board[rowLocation][colLocation] != null) {
            Pieces piece = board[rowLocation][colLocation];
            return !piece.getClass().getSimpleName().contains("White");
        }else return true;
    }
    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveBlack(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff) return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;

        while (colLocation != targetCol && rowLocation != targetRow) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        if (board[rowLocation][colLocation] != null) {
            Pieces piece = board[rowLocation][colLocation];
            return !piece.getClass().getSimpleName().contains("Black");
        }else return true;
    }
    //Implements Vertical and horizontal movements for rook/ queen of Black
    public boolean horizontalVerticalMoveBlack(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0) {
            colStep = -1;
        } else if (colLocation - targetCol < 0) {
            colStep = 1;
        } else if (rowLocation - targetRow > 0) {
            rowStep = -1;
        } else if (rowLocation - targetRow < 0) {
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        if (board[rowLocation][colLocation] != null) {
            Pieces piece = board[rowLocation][colLocation];
            return !piece.getClass().getSimpleName().contains("Black");
        }else return true;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}