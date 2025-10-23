package pieces.black;

import pieces.Pieces;

public class BlackQueen extends Pieces {
    public BlackQueen(int col, int row, String[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        if (colDiff == rowDiff)  return diagonalMoveBlack(targetCol, targetRow);
        return horizontalVerticalMoveBlack(targetCol, targetRow);
    }
}
