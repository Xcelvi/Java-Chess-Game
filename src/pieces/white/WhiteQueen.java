package pieces.white;

import pieces.Pieces;

public class WhiteQueen extends Pieces {
    public WhiteQueen(int col, int row) {
        super(col, row);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return true;
    }
}
