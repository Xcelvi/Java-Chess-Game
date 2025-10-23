package pieces.black;

import pieces.Pieces;

public class BlackBishop extends Pieces {
    public BlackBishop(int col, int row, String[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return diagonalMoveBlack(targetCol, targetRow);
    }

}
