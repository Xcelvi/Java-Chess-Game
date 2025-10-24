package pieces.white;

import pieces.Pieces;

public class WhiteKnight extends Pieces {
    public WhiteKnight(int col, int row, String[][] board) {
        super(col, row, board);
    }
    /*
    0 [0, k, 2, 3, 4, 5, 6, 7]
    1 [0, 1, 2, 3, 4, 5, 6, 7]
    2 [0, 1, 2, 3, 4, 5, 6, 7]
    3 [0, 1, 2, 3, 4, 5, 6, 7]
    4 [0, 1, 2, 3, 4, 5, 6, 7]
    5 [0, 1, 2, 3, 4, 5, 6, 7]
    6 [0, 1, 2, 3, 4, 5, 6, 7]
    7 [0, 1, 2, 3, 4, 5, 6, 7]


     */
    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();
        if (board[targetRow][targetCol].contains("White")){
            return false;
        }
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff == 2 && rowDiff == 1){
            return true;
        }else return colDiff == 1 && rowDiff == 2;
    }
}
