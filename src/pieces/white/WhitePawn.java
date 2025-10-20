package pieces.white;

import pieces.Pieces;

public class WhitePawn extends Pieces {
    private void moveWhitePawn(){

    }

    @Override
    public boolean isValidMove(int pieceRow, int pieceCol, int targetRow, int targetCol, String pieceType){
        return true;
    }
}
