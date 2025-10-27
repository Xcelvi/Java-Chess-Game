package pieces.white;

import pieces.HavePiecesMoved;
import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteRookR extends Pieces implements Vision {
    public WhiteRookR(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveWhite(targetCol, targetRow)){
            HavePiecesMoved.WHITEROOKR.setHasMoved(true);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getPieceVision(int  targetCol, int targetRow) {
        return null;
    }

    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        ArrayList<String> pieceVision = new ArrayList<>();

        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int[] d : directions) {
            int tempColLocation = targetCol;
            int tempRowLocation = targetRow;
            tempColLocation += d[0];
            tempRowLocation += d[1];
            while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
                String square = board[tempRowLocation][tempColLocation];
                pieceVision.add(square);
                tempColLocation += d[0];
                tempRowLocation += d[1];
            }
            pieceVision.add("|");
        }
        return pieceVision;
    }
}
