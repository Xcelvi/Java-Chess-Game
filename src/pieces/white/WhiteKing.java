package pieces.white;

import pieces.Pieces;

public class WhiteKing extends Pieces {
    public WhiteKing(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 1 || rowDiff > 1) {
            return false;
        }
        System.out.println("In white king");
        return horizontalVerticalMoveWhite(targetCol, targetRow) || diagonalMoveWhite(targetCol, targetRow);
    }
}
