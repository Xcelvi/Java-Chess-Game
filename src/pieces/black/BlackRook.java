package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackRook extends Pieces implements Vision {
    public ArrayList<String> pieceVision = new ArrayList<>();
    public BlackRook(int col, int row, Pieces[][] board) {
        super(col, row, board);
        this.name = "BlackRook";
        this.pieceValue = 500;
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        if (horizontalVerticalMoveBlack(targetCol, targetRow)) {
            hasMoved = true;
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getPieceFullVision() {
        ArrayList<String> pieceVision = new ArrayList<>();
        int targetCol = this.getCol();
        int targetRow = this.getRow();
        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
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
