package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteKnight extends Pieces implements Vision {
    public WhiteKnight(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();
        if (board[targetRow][targetCol].getClass().getSimpleName().contains("White")){
            return false;
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
            while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
                String square = board[tempRowLocation][tempColLocation].getClass().getSimpleName();
                pieceVision.add(square);
                tempColLocation += move[0];
                tempRowLocation += move[1];
            }
        }
        return pieceVision;
    }
}
