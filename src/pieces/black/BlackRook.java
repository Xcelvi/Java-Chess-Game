package pieces.black;

import pieces.Pieces;

public class BlackRook extends Pieces {

    public BlackRook(int col, int row) {
        super(col, row);
    }



    @Override
    public boolean isValidMove(int targetRow, int targetCol){
        return true;
    }
}
