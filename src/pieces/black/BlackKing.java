package pieces.black;

import pieces.HavePiecesMoved;
import pieces.Pieces;



public class BlackKing extends Pieces {
    public BlackKing(int col, int row, String[][] board) {
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
            HavePiecesMoved.BLACKKING.setHasMoved(true);
            return true;
        } if (colDiff == 1 && rowDiff == 0 && horizontalVerticalMoveBlack(targetCol, targetRow)){
            HavePiecesMoved.BLACKKING.setHasMoved(true);
            return true;
        } else if  (colDiff == 0 && rowDiff == 1 && horizontalVerticalMoveBlack(targetCol, targetRow)){
            HavePiecesMoved.BLACKKING.setHasMoved(true);
            return true;
        }
        //if the kings hasn't moved
        if (HavePiecesMoved.BLACKKING.getHasMoved()){
            //if they are castling queen side
            if (targetRow == 0 && targetCol == 1){
                //if the rook queen side has not moved
                if (HavePiecesMoved.BLACKROOKL.getHasMoved()){
                    //check if squares are open, if not return false.
                    for (int i = 1; i < 4; i++){
                        if (!board[0][i].contains("-")){
                            return false;
                        }
                    }
                    board[0][2] = board[0][0];
                    board[0][0] = "----------";
                    return true;
                }
                //if they are castling king side
            } else if (targetRow == 0 && targetCol == 6){
                //If the king side rook has not moved
                if (HavePiecesMoved.BLACKROOKR.getHasMoved()){
                    //check if squares are open, if not return false.
                    if (!board[0][5].contains("-") &&  !board[0][6].contains("-")){
                        return false;
                    }
                        board[0][5] = board[0][7];
                        board[0][7] = "----------";
                        return true;
                    }
                }
            }
        return false;
        }
    }
