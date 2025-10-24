package pieces.white;

import pieces.HavePiecesMoved;
import pieces.Pieces;

public class WhiteRookL extends Pieces {
    public WhiteRookL(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveWhite(targetCol, targetRow)){
            HavePiecesMoved.WHITEROOKL.setHasMoved(true);
            return true;
        }
        return false;
    }
}
