package pieces.black;

import pieces.Pieces;

public class BlackRook extends Pieces {
    boolean hasMoved= false;
    public BlackRook(int col, int row,  String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveBlack(targetCol, targetRow)) {
            hasMoved = true;
            return true;
        }
        return false;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }
}
