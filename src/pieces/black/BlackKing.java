package pieces.black;

import pieces.Pieces;

public class BlackKing extends Pieces {
    boolean hasMoved= false;
    public BlackKing(int col, int row) {
        super(col, row);
    }
    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff == 1 && rowDiff ==1 && diagonalMoveBlack(targetCol, targetRow)){
            hasMoved = true;
            return true;
        } if (colDiff == 1 && rowDiff == 0 && horizontalVerticalMoveBlack(targetCol, targetRow)){
            hasMoved = true;
            return true;
        } else if  (colDiff == 0 && rowDiff == 1 && horizontalVerticalMoveBlack(targetCol, targetRow)){
            hasMoved = true;
            return true;
        }
        return false;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }
}
