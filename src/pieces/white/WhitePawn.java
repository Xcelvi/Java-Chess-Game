package pieces.white;

import board.BoardControl;
import board.ChessBoard;
import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhitePawn extends Pieces implements Vision {
    public WhitePawn(int col, int row, Pieces[][] board, BoardControl chessBoard) {
        super(col, row, board, chessBoard);
        this.chessBoard = chessBoard;
        this.name = "WhitePawn";
        this.pieceValue = 100;
    }
    private final BoardControl chessBoard;
    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = rowLocation - targetRow;
        if (!(targetCol >=0 && targetRow >= 0 && targetCol < 8 && targetRow < 8)) return false;
        if (rowLocation == 6){
            if (colLocation - targetCol == 0){
                if (rowDiff == 2){
                    return board[rowLocation - 1][colLocation] == null && board[targetRow][targetCol] == null;
                }
            }
        }
        if (rowDiff > 1){
            return false;
        }
        if ((colDiff== 1) && (rowDiff == 1)) {
            if (board[targetRow][targetCol] != null &&
                    board[targetRow][targetCol].getName().contains("Black")) {
                return true;
            }
            if (targetRow == 2 && rowLocation == 3) {
                ArrayList<String> moveLog = chessBoard.getMoveLog();
                if (moveLog.isEmpty()) return false;
                if (moveLog.get(moveLog.size()-1).equals("BlackPawn" + (targetCol) + "1" + (targetCol) + 3)) {
                    return true;
                }
            }
        }

        return rowLocation - targetRow == 1 &&  colLocation - targetCol == 0 && board[targetRow][targetCol] == null;
    }

    @Override
    public ArrayList<String> getPieceFullVision() {
        ArrayList<String> pieceVision = new ArrayList<>();
        int targetCol = this.getCol();
        int targetRow = this.getRow();
        if (targetRow - 1 >= 0 && targetCol + 1 < 8) {
            if (board[targetRow - 1][targetCol + 1] == null) {
                pieceVision.add("Null" + (targetCol +1) + (targetRow - 1));
            } else if (board[targetRow - 1][targetCol + 1] != null) {
                pieceVision.add(board[targetRow - 1][targetCol + 1].getClass().getSimpleName()+ (targetCol +1) + (targetRow - 1));
            }
        }
        if (targetRow -1 >= 0 && targetCol -1 >= 0) {
            if (board[targetRow - 1][targetCol - 1] == null) {
                pieceVision.add("Null" + (targetCol -1) + (targetRow - 1));
            } else if (board[targetRow - 1][targetCol - 1] != null) {
                pieceVision.add(board[targetRow - 1][targetCol - 1].getClass().getSimpleName() + (targetCol -1) + (targetRow - 1));
            }
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
}
