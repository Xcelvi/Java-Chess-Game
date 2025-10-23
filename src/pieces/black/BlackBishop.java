package pieces.black;

import pieces.Pieces;

public class BlackBishop extends Pieces {
    public BlackBishop(int col, int row, String[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff)  return false;
        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;
        while (colLocation != targetCol && rowLocation != targetRow ){
            if (!board[rowLocation][colLocation].contains("-")){
                System.out.println("BoardSky" + board[rowLocation][colLocation]);
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        System.out.println("BlackBishop isValidMove after while");
        return !board[rowLocation][colLocation].contains("Black");
    }

}
