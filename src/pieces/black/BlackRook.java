package pieces.black;

import pieces.Pieces;

public class BlackRook extends Pieces {

    public BlackRook(int col, int row,  String[][] board) {
        super(col, row, board);
    }



    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0){
            colStep = -1;
        } else if (colLocation - targetCol < 0){
            colStep = 1;
        } else if (rowLocation - targetRow > 0){
            rowStep = -1;
        } else if (rowLocation - targetRow < 0){
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;

        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)){
            if (!board[rowLocation][colLocation].contains("-")){
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("Black");
    }
}
