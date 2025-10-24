package pieces.white;

import pieces.Pieces;

public class WhiteKing extends Pieces {
    boolean hasMoved = false;
    public WhiteKing(int col, int row, String[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff == 1 && rowDiff ==1 && diagonalMoveWhite(targetCol, targetRow)){
            hasMoved = true;
            return true;
        } if (colDiff == 1 && rowDiff == 0 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            hasMoved = true;
            return true;
        } else if  (colDiff == 0 && rowDiff == 1 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            hasMoved = true;
            return true;
        }
        return false;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }
}
