package pieces.white;

import pieces.Pieces;

public class WhiteQueen extends Pieces {
    public WhiteQueen(int col, int row, String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        System.out.println("In Queen");
        if (colDiff == rowDiff)  return diagnolMoveWhite(targetCol, targetRow);
        System.out.println("In Queen rook");
        return horizontalVerticalMoveWhite(targetCol, targetRow);
    }
}
