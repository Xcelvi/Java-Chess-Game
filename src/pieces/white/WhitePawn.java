package pieces.white;

import pieces.Pieces;

public class WhitePawn extends Pieces {
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
}
