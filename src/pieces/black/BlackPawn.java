package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackPawn extends Pieces implements Vision {
    public BlackPawn(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();

        if (rowLocation == 1){
            if (colLocation - targetCol == 0){
                if (rowLocation - targetRow == -1 || rowLocation - targetRow == -2){
                    return true;
                }
            }
        }
        return rowLocation - targetRow == -1;
    }


    @Override
    public ArrayList<String> getPieceFullVision(int col, int row) {
        return null;
    }
}
