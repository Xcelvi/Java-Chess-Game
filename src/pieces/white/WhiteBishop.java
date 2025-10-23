package pieces.white;

import pieces.Pieces;


public class WhiteBishop extends Pieces {
    public WhiteBishop(int col, int row, String[][] board) {
        super(col, row, board);
    }

    //Fix this
    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return diagnolMoveWhite(targetCol, targetRow);
    }
}
