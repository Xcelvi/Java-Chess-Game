package pieces.black;

import pieces.Pieces;

public class BlackQueen extends Pieces {
    public BlackQueen(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol){

        return true;
    }

}
