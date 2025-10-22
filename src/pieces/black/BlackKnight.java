package pieces.black;

import pieces.Pieces;

public class BlackKnight extends Pieces {
    public BlackKnight(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol){

        return true;
    }

}
