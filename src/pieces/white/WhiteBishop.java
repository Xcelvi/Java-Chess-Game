package pieces.white;

import pieces.Pieces;

public class WhiteBishop extends Pieces {
    int row;
    int col;

    public WhiteBishop(int row, int col) {
        super(row, col);
    }



    @Override
    public boolean isValidMove(int targetRow, int targetCol){
        return true;
    }
}
