package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteQueen extends Pieces implements Vision {
    public WhiteQueen(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        System.out.println("In Queen");
        if (colDiff == rowDiff)  return diagonalMoveWhite(targetCol, targetRow);
        System.out.println("In Queen rook");
        return horizontalVerticalMoveWhite(targetCol, targetRow);
    }

    @Override
    public ArrayList<String> getPieceVision(int col, int row) {
        return null;
    }

    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        return null;
    }
}
