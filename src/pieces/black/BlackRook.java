package pieces.black;

import pieces.Pieces;

public class BlackRook extends Pieces {
    public BlackRook(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return horizontalVerticalMoveBlack(targetCol, targetRow);
    }

}
