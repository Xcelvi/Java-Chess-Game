package pieces.black;

import board.ChessBoard;
import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BlackPawn extends Pieces implements Vision {
    public BlackPawn(int col, int row, Pieces[][] board, ChessBoard chessBoard) {
        super(col, row, board);
        this.chessBoard = chessBoard;
    }
    private final ChessBoard chessBoard;
    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);
        if (rowLocation == 1){
            if (colLocation - targetCol == 0){
                if (rowLocation - targetRow == -2){
                    return board[rowLocation + 1][colLocation] == null && board[targetRow][targetCol] == null;
                }
            }
        }
        if ((colDiff== 1) && (rowDiff == 1)) {
            if (board[targetRow][targetCol] != null &&
                    board[targetRow][targetCol].getClass().getSimpleName().contains("Black")) {
                return true;
            }
            if (targetRow == 5) {
                System.out.println("BlackPawn" + (targetCol) + "6" + (targetCol) + 5);
                ArrayList<String> moveLog = chessBoard.getMoveLog();
                if (moveLog.get(moveLog.size()-1).equals("WhitePawn" + (targetCol) + "6" + (targetCol) + 4)) {
                    board[targetRow - 1][targetCol] = null;
                    return true;
                }
            }
        }

        return rowLocation - targetRow == -1 &&  colLocation - targetCol == 0 && board[targetRow][targetCol] == null;
    }


    @Override
    public ArrayList<String> getPieceFullVision(int targetCol, int targetRow) {
        ArrayList<String> pieceVision = new ArrayList<>();

        if (targetRow + 1 < 8 && targetCol + 1 < 8) {
            if (board[targetRow + 1][targetCol + 1] == null) {
                pieceVision.add("Null");
            } else if (board[targetRow + 1][targetCol + 1] != null) {
                pieceVision.add(board[targetRow + 1][targetCol + 1].getClass().getSimpleName());
            }
        }
        if (targetRow + 1 < 8 && targetCol -1 > 0) {
            if (board[targetRow + 1][targetCol - 1] == null) {
                pieceVision.add("Null");
            } else if (board[targetRow + 1][targetCol - 1] != null) {
                pieceVision.add(board[targetRow + 1][targetCol - 1].getClass().getSimpleName());
            }
        }
        setPieceVision(pieceVision);
        return pieceVision;
    }
}
