package pieces.black;

import pieces.Pieces;

public class BlackKing extends Pieces {
    public BlackKing(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){

        return true;
    }

}
