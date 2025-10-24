package pieces.black;

import pieces.HavePiecesMoved;
import pieces.Pieces;

public class BlackRookR extends Pieces {
    public BlackRookR(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveBlack(targetCol, targetRow)) {
            HavePiecesMoved.BLACKROOKR.setHasMoved(true);
            return true;
        }
        return false;
    }
}
