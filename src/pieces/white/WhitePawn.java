package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhitePawn extends Pieces implements Vision {
    public WhitePawn(int col, int row, String[][] board) {
        super(col, row, board);
    }


    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();

        if (rowLocation == 6){
            if (colLocation - targetCol == 0){
                if (rowLocation - targetRow == 1 || rowLocation - targetRow == 2){
                    return true;
                }
            }
        }
        return rowLocation - targetRow == 1;
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
