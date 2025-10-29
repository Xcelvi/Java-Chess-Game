package pieces.white;

import board.BoardControl;
import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteKing extends Pieces implements Vision {
    public WhiteKing(int col, int row, Pieces[][] board,  BoardControl chessBoard) {
        super(col, row, board);
        this.chessBoard = chessBoard;
    }
    BoardControl chessBoard;
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
        if (!hasMoved) {
            //if they are castling queen side
            if (targetRow == 7 && targetCol == 2){
                //if the rook queen side has not moved
                if (board[7][0] != null && !board[7][0].getHasMoved()){
                    //check if squares are open, if not return false.
                    for (int i = 3; i > 1; i--){
                        if (board[7][i] != null){
                            return false;
                        }
                        if (chessBoard.isWhiteInCheck(board, colLocation, rowLocation, i, targetRow)){
                            return false;
                        }
                    }
                    board[7][3] = board[7][0];
                    board[7][0] = null;
                    board[7][3].setCol(3);
                    board[7][3].setRow(7);
                    this.hasMoved = true;
                    return true;
                }
                //if they are castling king side
            } else if (targetRow == 7 && targetCol == 6){
                //If the king side rook has not moved
                if (board[7][7] != null && !board[7][7].getHasMoved()){
                    //check if squares are open, if not return false.
                        if (board[7][5] != null || board[7][6] != null){
                            return false;
                        }
                        if (chessBoard.isWhiteInCheck(board, colLocation, rowLocation, 5, 7) || chessBoard.isWhiteInCheck(board, colLocation, rowLocation, 6, 7)) {
                            return false;
                        }
                    board[7][5] = board[7][7];
                    board[7][7] = null;
                    board[7][5].setCol(5);
                    board[7][5].setRow(7);
                    this.hasMoved = true;
                    return true;
                    }
            }
        }
        return false;
    }


    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        ArrayList<String> pieceVision = new ArrayList<>();
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
                pieceVision.add(square);

            }
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
}
