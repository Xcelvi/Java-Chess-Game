package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteQueen extends Pieces implements Vision {
    public WhiteQueen(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        if (colDiff == rowDiff)  return diagonalMoveWhite(targetCol, targetRow);
        return horizontalVerticalMoveWhite(targetCol, targetRow);
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
                String square = board[tempRowLocation][tempColLocation].getClass().getSimpleName();
                pieceVision.add(square);

                if (!square.contains("-")) break;

                tempColLocation += d[0];
                tempRowLocation += d[1];
            }
            pieceVision.add("|");
        }
        directions = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] d : directions) {
            int tempColLocation = targetCol;
            int tempRowLocation = targetRow;
            tempColLocation += d[0];
            tempRowLocation += d[1];
            while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
                String square = board[tempRowLocation][tempColLocation].getClass().getSimpleName();
                pieceVision.add(square);

                if (!square.contains("-")) break;

                tempColLocation += d[0];
                tempRowLocation += d[1];
            }
            pieceVision.add("|");
        }

        return pieceVision;
    }
}
