package pieces.white;

import pieces.Pieces;

public class WhiteBishop extends Pieces {
    private void moveWhiteBishop(){

    }

    @Override
    public boolean isValidMove(int pieceRow, int pieceCol, int targetRow, int targetCol, String pieceType){
        return true;
    }
}
