package pieces.white;

import pieces.Pieces;

public class WhiteKing extends Pieces {
    public WhiteKing(int col, int row) {
        super(col, row);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        return true;
    }
}
