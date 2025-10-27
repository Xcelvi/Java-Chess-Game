package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;


public class WhiteBishop extends Pieces implements Vision {
    public WhiteBishop(int col, int row, String[][] board) {
        super(col, row, board);
    }

    //Fix this
    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return diagonalMoveWhite(targetCol, targetRow);
    }

    @Override
    public ArrayList<String> getPieceVision(int targetCol, int targetRow) {
        return null;
    }

    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        return null;
    }
}
