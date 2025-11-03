package pieces.black;

import board.BoardControl;
import board.ChessBoard;
import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackPawn extends Pieces implements Vision {
    public BlackPawn(int col, int row, Pieces[][] board, BoardControl chessBoard) {
        super(col, row, board);
        this.chessBoard = chessBoard;
    }
    int pieceValue = 100;
    private final ChessBoard chessBoard;
    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = rowLocation - targetRow;
        if (!(targetCol >=0 && targetRow >= 0 && targetCol < 8 && targetRow < 8)) return false;
        if (rowLocation == 1){
            if (colLocation - targetCol == 0){
                if (rowDiff == -2){
                    return board[rowLocation + 1][colLocation] == null && board[targetRow][targetCol] == null;
                }
            }
        }
        if (rowDiff < -1){
            return false;
        }
        if ((colDiff== 1) && (rowDiff == -1)) {
            if (board[targetRow][targetCol] != null &&
                    board[targetRow][targetCol].getClass().getSimpleName().contains("White")) {
                return true;
            }
            if (targetRow == 5 && rowLocation == 4) {
                ArrayList<String> moveLog = chessBoard.getMoveLog();
                if (moveLog.isEmpty()) return false;
                if (moveLog.get(moveLog.size()-1).equals("WhitePawn" + (targetCol) + "6" + (targetCol) + 4)) {
                    board[targetRow - 1][targetCol] = null;
                    return true;
                }
            }
        }

        return rowLocation - targetRow == -1 &&  colLocation - targetCol == 0 && board[targetRow][targetCol] == null;
    }


    @Override
    public ArrayList<String> getPieceFullVision() {
        ArrayList<String> pieceVision = new ArrayList<>();
        int targetCol = this.getCol();
        int targetRow = this.getRow();
        if (targetRow + 1 < 8 && targetCol + 1 < 8) {
            if (board[targetRow + 1][targetCol + 1] == null) {
                pieceVision.add("Null" + (targetCol +1) + (targetRow + 1));
            } else if (board[targetRow + 1][targetCol + 1] != null) {
                pieceVision.add(board[targetRow + 1][targetCol + 1].getClass().getSimpleName() + (targetCol +1) + (targetRow + 1));
            }
        }
        if (targetRow + 1 < 8 && targetCol -1 >= 0) {
            if (board[targetRow + 1][targetCol - 1] == null) {
                pieceVision.add("Null" + (targetCol - 1) + (targetRow + 1));
            } else if (board[targetRow + 1][targetCol - 1] != null) {
                pieceVision.add(board[targetRow + 1][targetCol - 1].getClass().getSimpleName() + (targetCol -1) + (targetRow + 1));
            }
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
    @Override
    public int getPieceValue() {
        return pieceValue;
    }
}
