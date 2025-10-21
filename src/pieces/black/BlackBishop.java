package pieces.black;

import pieces.Pieces;

public class BlackBishop extends Pieces {
    public BlackBishop(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol){

        return true;
    }

}
