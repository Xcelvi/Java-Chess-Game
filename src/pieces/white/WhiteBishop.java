package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;


public class WhiteBishop extends Pieces implements Vision {
    public WhiteBishop(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }
    int pieceValue = 330;
    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return diagonalMoveWhite(targetCol, targetRow);
    }

    @Override
    public ArrayList<String> getPieceFullVision() {
        ArrayList<String> pieceVision = new ArrayList<>();
        int targetCol = this.getCol();
        int targetRow = this.getRow();
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] d : directions) {
            int tempColLocation = targetCol;
            int tempRowLocation = targetRow;
            tempColLocation += d[0];
            tempRowLocation += d[1];
            while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
                String square;
                if (board[tempRowLocation][tempColLocation] == null){
                    square = "null";
                }else {
                    square = board[tempRowLocation][tempColLocation].getClass().getSimpleName();
                }
                pieceVision.add(square+ tempColLocation + tempRowLocation);

                if (board[tempRowLocation][tempColLocation] != null) break;

                tempColLocation += d[0];
                tempRowLocation += d[1];
            }
            pieceVision.add("|");
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
}
