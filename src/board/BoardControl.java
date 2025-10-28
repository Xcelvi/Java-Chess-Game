package board;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class BoardControl extends ChessBoard {


    public BoardControl() {
        super();
    }

    public void setBoardVision(){
        Pieces[][] board = getBoard();

        for (int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                if (board[row][col] != null) {
                    Pieces piece = board[row][col];
                    if (piece instanceof Vision) {
                        //this is correct
                        ((Vision) piece).getPieceFullVision(col, row);
                    }
                }
            }
        }
    }
    public boolean isWhiteInCheck(){
        Pieces[][] board = getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    ArrayList<String> pieceVision = board[i][j].getPieceFullVision(j, i);
                    if (board[i][j].getClass().getSimpleName().contains("Black") && pieceVision.contains("WhiteKing")) {
                        System.out.println(board[i][j] +" " + pieceVision);
                        System.out.println("WhiteKing check");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isBlackInCheck(){
        Pieces[][] board = getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    ArrayList<String> pieceVision = board[i][j].getPieceFullVision(j, i);
                    if (board[i][j].getClass().getSimpleName().contains("White") && pieceVision.contains("BlackKing")) {
                        System.out.println(board[i][j] +" " + pieceVision);
                        System.out.println("WhiteKing check");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
