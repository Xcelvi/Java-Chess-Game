package pieces.white;

import pieces.Pieces;

public class WhiteRook extends Pieces {
    boolean hasMoved = false;
    public WhiteRook(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveWhite(targetCol, targetRow)){
            hasMoved = true;
            return true;
        }
        return false;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }
}
