package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackQueen extends Pieces implements Vision {
    public BlackQueen(int col, int row, String[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        if (colDiff == rowDiff)  return diagonalMoveBlack(targetCol, targetRow);
        return horizontalVerticalMoveBlack(targetCol, targetRow);
    }

    @Override
    public ArrayList<String> getPieceVision(int col, int row) {
        return null;
    }

    @Override
    public ArrayList<String> getPieceFullVision(int col, int row) {
        return null;
    }

}
