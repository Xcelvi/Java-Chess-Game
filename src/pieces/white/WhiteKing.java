package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteKing extends Pieces implements Vision {
    public WhiteKing(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }

    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (isKingInCheckWhite(targetCol, targetRow)) {
            return false;
        }

        if (colDiff == 1 && rowDiff ==1 && diagonalMoveWhite(targetCol, targetRow)){
            hasMoved = true;
            return true;
        } if (colDiff == 1 && rowDiff == 0 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            hasMoved = true;
            return true;
        } else if  (colDiff == 0 && rowDiff == 1 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            hasMoved = true;
            return true;
        }
        if (!hasMoved) {
            //if they are castling queen side
            if (targetRow == 7 && targetCol == 1){
                //if the rook queen side has not moved
                if (board[7][0] != null && !board[7][0].getHasMoved()){
                    //check if squares are open, if not return false.
                    for (int i = 1; i < 4; i++){
                        if (board[7][i] != null){
                            return false;
                        }
                    }
                    board[7][2] = board[7][0];
                    board[7][0] = null;
                    return true;
                }
                //if they are castling king side
            } else if (targetRow == 7 && targetCol == 6){
                //If the king side rook has not moved
                if (board[7][7] != null && !board[7][7].getHasMoved()){
                    //check if squares are open, if not return false.
                    if (board[7][5] != null &&  board[7][6] != null){
                        return false;
                    }
                    board[7][5] = board[7][7];
                    board[7][7] = null;
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        return null;
    }

}
