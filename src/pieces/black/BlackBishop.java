package pieces.black;

import pieces.Pieces;

public class BlackBishop extends Pieces {
    @Override
    public boolean isValidMove(int pieceRow, int pieceCol, int targetRow, int targetCol, String pieceType){
        return true;
    }

}
