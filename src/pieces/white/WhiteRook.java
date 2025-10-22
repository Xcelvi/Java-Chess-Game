package pieces.white;

import pieces.Pieces;

public class WhiteRook extends Pieces {
    public WhiteRook(int col, int row) {
        super(col, row);
    }



    @Override
    public boolean isValidMove(int targetRow, int targetCol){
        return true;
    }
}
