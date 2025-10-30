package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackKnight extends Pieces implements Vision {
    public BlackKnight(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();
        if (!(targetCol >=0 && targetRow >= 0 && targetCol < 8 && targetRow < 8)) return false;
        if (board[targetRow][targetCol] != null) {
            if (board[targetRow][targetCol].getClass().getSimpleName().contains("Black")) {
                return false;
            }
        }
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        if (colDiff == 2 && rowDiff == 1){
            return true;
        }else return colDiff == 1 && rowDiff == 2;
    }


    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        ArrayList<String> pieceVision = new ArrayList<>();
        int[][] knightMoves = {
                {-2, -1}, {-2, +1},
                {-1, +2}, {+1, +2},
                {+2, +1}, {+2, -1},
                {-1, -2}, {+1, -2}
        };

        for (int[] move : knightMoves) {
            int tempColLocation = targetCol;
            int tempRowLocation = targetRow;
            tempColLocation += move[0];
            tempRowLocation += move[1];
            // Check board bounds (0–7 if 8×8 board)
            if (tempColLocation >= 0 && tempRowLocation >= 0 && tempColLocation < 8 && tempRowLocation < 8) {
                if (board[tempRowLocation][tempColLocation] == null){
                    pieceVision.add("null");
                }else {
                    pieceVision.add(board[tempRowLocation][tempColLocation].getClass().getSimpleName());
                }
            }
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
}
