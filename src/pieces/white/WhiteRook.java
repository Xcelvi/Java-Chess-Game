package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteRook extends Pieces implements Vision {
    public WhiteRook(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {

        if (horizontalVerticalMoveWhite(targetCol, targetRow)) {
            hasMoved = true;
            setCol(targetCol);
            setRow(targetRow);
            return true;
        }
        return false;
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
                String square;
                if (board[tempRowLocation][tempColLocation] == null){
                    square = "null";
                }else {
                    square = board[tempRowLocation][tempColLocation].getClass().getSimpleName();
                }
                pieceVision.add(square);

                if (board[tempRowLocation][tempColLocation] != null) break;

                tempColLocation += d[0];
                tempRowLocation += d[1];
            }
            pieceVision.add("|");
        }
        System.out.println("Returned piece vision");
        return pieceVision;
    }
}
