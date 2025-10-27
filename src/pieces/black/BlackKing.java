package pieces.black;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;


public class BlackKing extends Pieces implements Vision {
    public BlackKing(int col, int row, Pieces[][] board) {
        super(col, row, board);
    }
    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (isKingInCheckBlack(targetCol, targetRow)) {
            return false;
        }

        if (colDiff == 1 && rowDiff ==1 && diagonalMoveBlack(targetCol, targetRow)){
            this.hasMoved = true;
            return true;
        } if (colDiff == 1 && rowDiff == 0 && horizontalVerticalMoveBlack(targetCol, targetRow)){
            this.hasMoved = true;
            return true;
        } else if  (colDiff == 0 && rowDiff == 1 && horizontalVerticalMoveBlack(targetCol, targetRow)){
            hasMoved = true;
            return true;
        }
        //if the kings hasn't moved
        if (hasMoved){
            //if they are castling queen side
            if (targetRow == 0 && targetCol == 1){
                //if the rook queen side has not moved
                if (board[0][0] != null && board[0][0].getHasMoved()){
                    //check if squares are open, if not return false.
                    for (int i = 1; i < 4; i++){
                        if (board[0][i] != null){
                            return false;
                        }
                    }
                    board[0][2] = board[0][0];
                    board[0][0] = null;
                    return true;
                }
                //if they are castling king side
            } else if (targetRow == 0 && targetCol == 6){
                //If the king side rook has not moved
                if (board[0][7] != null && board[0][7].getHasMoved()){
                    //check if squares are open, if not return false.
                    if (board[0][5] != null &&  board[0][6] != null){
                        return false;
                    }
                        board[0][5] = board[0][7];
                        board[0][7] = null;
                        return true;
                    }
                }
            }
        return false;
        }


    @Override
    public ArrayList<String> getPieceFullVision(int col, int row) {
        return null;
    }
}
