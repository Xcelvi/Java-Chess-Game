package pieces.white;

import pieces.Pieces;

public class WhiteBishop extends Pieces {
    public WhiteBishop(int col, int row) {
        super(col, row);
    }



    @Override
    public boolean isValidMove(int targetRow, int targetCol){
        return true;
    }
}
