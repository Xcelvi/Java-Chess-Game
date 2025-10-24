package pieces.black;

import pieces.Pieces;

public class BlackKnight extends Pieces {
    public BlackKnight(int col, int row, String[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();
        System.out.println("In black knight");
        if (board[targetRow][targetCol].contains("Black")){
            return false;
        }
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        System.out.println("In black knight");
        if (colDiff == 2 && rowDiff == 1){
            return true;
        }else return colDiff == 1 && rowDiff == 2;
    }

}
