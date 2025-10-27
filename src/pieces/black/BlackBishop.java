package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackBishop extends Pieces implements Vision {
    public BlackBishop(int col, int row, String[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return diagonalMoveBlack(targetCol, targetRow);
    }

    @Override
    public ArrayList<String> getPieceVision(int col, int row) {
        return null;
    }

    @Override
    public ArrayList<String> getPieceFullVision(int col, int row) {
        return null;
    }

}
