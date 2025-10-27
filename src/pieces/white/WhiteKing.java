package pieces.white;

import pieces.HavePiecesMoved;
import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteKing extends Pieces implements Vision {
    public WhiteKing(int col, int row, String[][] board) {
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
            HavePiecesMoved.WHITEKING.setHasMoved(true);
            return true;
        } if (colDiff == 1 && rowDiff == 0 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            HavePiecesMoved.WHITEKING.setHasMoved(true);
            return true;
        } else if  (colDiff == 0 && rowDiff == 1 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            HavePiecesMoved.WHITEKING.setHasMoved(true);
            return true;
        }
        if (HavePiecesMoved.WHITEKING.getHasMoved()){
            //if they are castling queen side
            if (targetRow == 7 && targetCol == 1){
                //if the rook queen side has not moved
                if (HavePiecesMoved.WHITEROOKL.getHasMoved()){
                    //check if squares are open, if not return false.
                    for (int i = 1; i < 4; i++){
                        if (!board[7][i].contains("-")){
                            return false;
                        }
                    }
                    board[7][2] = board[7][0];
                    board[7][0] = "----------";
                    return true;
                }
                //if they are castling king side
            } else if (targetRow == 7 && targetCol == 6){
                //If the king side rook has not moved
                if (HavePiecesMoved.WHITEROOKR.getHasMoved()){
                    //check if squares are open, if not return false.
                    if (!board[7][5].contains("-") &&  !board[7][6].contains("-")){
                        return false;
                    }
                    board[7][5] = board[7][7];
                    board[7][7] = "----------";
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getPieceVision(int col, int row) {
        return null;
    }

    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        return null;
    }
}
