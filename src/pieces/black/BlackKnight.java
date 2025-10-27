package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackKnight extends Pieces implements Vision {
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

    @Override
    public ArrayList<String> getPieceVision(int col, int row) {
        return null;
    }

    @Override
    public ArrayList<String> getPieceFullVision(int col, int row) {
        return null;
    }
}
