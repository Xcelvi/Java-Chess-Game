package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackPawn extends Pieces implements Vision {
    public BlackPawn(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (rowLocation == 1){
            if (colLocation - targetCol == 0){
                if (rowLocation - targetRow == -1 || rowLocation - targetRow == -2){
                    return board[rowLocation + 1][colLocation] == null && board[targetRow][targetCol] == null;
                }
            }
        }
        if ((colDiff== 1) && (rowDiff == 1)){
            return board[targetRow][targetCol] != null && board[targetRow][targetCol].getClass().getSimpleName().contains("White");
        }
        return false;
    }


    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        ArrayList<String> pieceVision = new ArrayList<>();

        if (targetRow + 1 < 8 && targetCol + 1 < 8) {
            if (board[targetRow + 1][targetCol + 1] == null) {
                pieceVision.add("Null");
            } else if (board[targetRow + 1][targetCol + 1] != null) {
                pieceVision.add(board[targetRow - 1][targetCol + 1].getClass().getSimpleName());
            }
        }
        if (targetRow + 1 < 8 && targetCol -1 > 0) {
            if (board[targetRow + 1][targetCol - 1] == null) {
                pieceVision.add("Null");
            } else if (board[targetRow + 1][targetCol - 1] != null) {
                pieceVision.add(board[targetRow - 1][targetCol + 1].getClass().getSimpleName());
            }
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
}
