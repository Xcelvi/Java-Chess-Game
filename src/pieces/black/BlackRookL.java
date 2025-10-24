package pieces.black;

import pieces.HavePiecesMoved;
import pieces.Pieces;

public class BlackRookL extends Pieces {
    public BlackRookL(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveBlack(targetCol, targetRow)) {
            HavePiecesMoved.BLACKROOKL.setHasMoved(true);
            return true;
        }
        return false;
    }

}
