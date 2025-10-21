package pieces.white;

import pieces.Pieces;

public class WhitePawn extends Pieces {
    public WhitePawn(int col, int row) {
        super(col, row);
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
        }if (rowLocation - targetRow == 1){
                    return true;
                } else {
            return false;
        }
    }
}
