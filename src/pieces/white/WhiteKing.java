package pieces.white;

import board.BoardControl;
import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteKing extends Pieces implements Vision {
    public WhiteKing(int col, int row, Pieces[][] board,  BoardControl chessBoard) {
        super(col, row, board);
        this.chessBoard = chessBoard;
        this.name = "WhiteKing";
        this.pieceValue = 9000;
    }
    BoardControl chessBoard;
    boolean hasMoved = false;
    @Override
    public boolean isValidMove(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);


        if (colDiff == 1 && rowDiff ==1 && diagonalMoveWhite(targetCol, targetRow)){
            return true;
        } if (colDiff == 1 && rowDiff == 0 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            return true;
        } else if  (colDiff == 0 && rowDiff == 1 && horizontalVerticalMoveWhite(targetCol, targetRow)){
            return true;
        }
        //check if it is rooks in corner
        boolean queenSideRook = board[7][0] instanceof WhiteRook;
        boolean kingSideRook = board[7][7] instanceof WhiteRook;
        if (!hasMoved) {
            //if they are castling queen side
            if (targetRow == 7 && targetCol == 2){
                //if the rook queen side has not moved
                if (board[7][0] != null && !board[7][0].getHasMoved() && queenSideRook){
                    //check if squares are open, if not return false.
                    for (int i = 3; i > 1; i--){
                        if (board[7][i] != null){
                            return false;
                        }
                        if (chessBoard.isWhiteInCheck()){
                            return false;
                        }
                    }
                    return true;
                }
                //if they are castling king side
            } else if (targetRow == 7 && targetCol == 6){
                //If the king side rook has not moved
                if (board[7][7] != null && !board[7][7].getHasMoved() && kingSideRook){
                    //check if squares are open, if not return false.
                        if (board[7][5] != null || board[7][6] != null){
                            return false;
                        }
                    return !chessBoard.isWhiteInCheck() && !chessBoard.isWhiteInCheck();
                }
            }
        }
        return false;
    }


    @Override
    public ArrayList<String> getPieceFullVision() {
        ArrayList<String> pieceVision = new ArrayList<>();
        int targetCol = this.getCol();
        int targetRow = this.getRow();
        int[][] aroundKingMoves = {
                {-1, -1}, {-1, 0},
                {-1, 1}, {0, 1},
                {1, 1}, {1, 0},
                {1, -1}, {0, -1}
        };

        for (int[] move : aroundKingMoves) {
            int tempColLocation = targetCol;
            int tempRowLocation = targetRow;
            tempColLocation += move[0];
            tempRowLocation += move[1];

            // Check board bounds (0–7 if 8×8 board)
            if ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
                String square;
                if (board[tempRowLocation][tempColLocation] == null){
                    square = "null";
                }else {
                    square = board[tempRowLocation][tempColLocation].getClass().getSimpleName();
                }
                pieceVision.add(square + tempColLocation + tempRowLocation);

            }
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
}
