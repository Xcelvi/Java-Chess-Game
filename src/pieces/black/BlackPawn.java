package pieces.black;

import pieces.Pieces;

public class BlackPawn extends Pieces {
    public BlackPawn(int col, int row) {
        super(col, row);
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
}
