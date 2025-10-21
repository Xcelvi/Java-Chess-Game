package pieces.black;

import pieces.Pieces;

public class BlackPawn extends Pieces {
    public BlackPawn(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol) {
        return true;
    }
}
