package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackBishop extends Pieces implements Vision {
    public BlackBishop(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return diagonalMoveBlack(targetCol, targetRow);
    }

    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        ArrayList<String> pieceVision = new ArrayList<>();
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
                pieceVision.add(square);

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
