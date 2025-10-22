package pieces.white;

import pieces.Pieces;

public class WhiteKnight extends Pieces {
    public WhiteKnight(int col, int row) {
        super(col, row);
    }



    @Override
    public boolean isValidMove(int targetRow, int targetCol){
        return true;
    }
}
