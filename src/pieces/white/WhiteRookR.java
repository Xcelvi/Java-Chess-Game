package pieces.white;

import pieces.HavePiecesMoved;
import pieces.Pieces;

public class WhiteRookR extends Pieces {
    public WhiteRookR(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveWhite(targetCol, targetRow)){
            HavePiecesMoved.WHITEROOKR.setHasMoved(true);
            return true;
        }
        return false;
    }
}
