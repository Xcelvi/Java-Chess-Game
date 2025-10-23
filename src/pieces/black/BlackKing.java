package pieces.black;

import pieces.Pieces;

public class BlackKing extends Pieces {
    public BlackKing(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff == 1 && rowDiff ==1){
            return diagonalMoveBlack(targetCol, targetRow);
        } if (colDiff == 1 && rowDiff == 0){
            return horizontalVerticalMoveBlack(targetCol, targetRow);
        } else if  (colDiff == 0 && rowDiff == 1){
            return horizontalVerticalMoveBlack(targetCol, targetRow);
        }
        return false;
    }

}
