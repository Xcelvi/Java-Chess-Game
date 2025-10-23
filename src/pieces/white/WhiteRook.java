package pieces.white;

import pieces.Pieces;

public class WhiteRook extends Pieces {
    public WhiteRook(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return horizontalVerticalMoveWhite(targetCol, targetRow);
    }
}
