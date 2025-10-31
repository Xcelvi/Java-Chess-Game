package board;

import pieces.Pieces;
import pieces.Vision;
import pieces.black.*;
import pieces.white.*;

import java.util.ArrayList;

public class BoardControl extends ChessBoard {

    public BoardControl() {
        super();
    }
    Pieces[][] board = getBoard();
    public void initializeBoard() {
        // initialize pawns
        board[1][0] = new BlackPawn(0, 1, board, this);
        board[1][1] = new BlackPawn(1, 1, board, this);
        board[1][2] = new BlackPawn(2, 1, board, this);
        board[1][3] = new BlackPawn(3, 1, board, this);
        board[1][4] = new BlackPawn(4, 1, board, this);
        board[1][5] = new BlackPawn(5, 1, board, this);
        board[1][6] = new BlackPawn(6, 1, board, this);
        board[1][7] = new BlackPawn(7, 1, board, this);
        board[6][0] = new WhitePawn(0, 6, board, this);
        board[6][1] = new WhitePawn(1, 6, board, this);
        board[6][2] = new WhitePawn(2, 6, board, this);
        board[6][3] = new WhitePawn(3, 6, board, this);
        board[6][4] = new WhitePawn(4, 6, board, this);
        board[6][5] = new WhitePawn(5, 6, board, this);
        board[6][6] = new WhitePawn(6, 6, board, this);
        board[6][7] = new WhitePawn(7, 6, board, this);

        board[0][0] = new BlackRook(0, 0, board);
        board[0][7] = new BlackRook(7, 0, board);
        board[7][0] = new WhiteRook(0, 7, board);
        board[7][7] = new WhiteRook(7, 7, board);
        //initialize knights
        board[0][1] = new BlackKnight(1,0,board);
        board[0][6] = new BlackKnight(6,0,board);
        board[7][1] = new WhiteKnight(1,7,board);
        board[7][6] = new WhiteKnight(6,7,board);
        //initialize bishop
        board[0][2] = new BlackBishop(2,0,board);
        board[0][5] =  new BlackBishop(5,0,board);
        board[7][2] = new WhiteBishop(2,7,board);
        board[7][5] =  new WhiteBishop(5,7,board);
        //initialize queen
        board[0][3] = new BlackQueen(3,0,board);
        board[7][3] = new WhiteQueen(3,7,board);
        // initialize king
        board[0][4] = new BlackKing(4,0,board, this);
        board[7][4] = new WhiteKing(4,7,board, this);
        generateAllLegalMoves(this);
        printBoard();
    }

    public void setBoardVision(){
        Pieces[][] board = getBoard();
        for (Pieces[] pieces : board) {
            for (int col = 0; col < board.length; col++) {
                if (pieces[col] != null) {
                    Pieces piece = pieces[col];
                    if (piece instanceof Vision) {
                        //this is correct
                        ((Vision) piece).getPieceFullVision();
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
                    ArrayList<String> pieceVision = board[i][j].getPieceFullVision();
                    if (board[i][j].getClass().getSimpleName().contains("Black")) {
                        for (String vision : pieceVision) {
                            if (vision != null && vision.contains("WhiteKing")) {
                                System.out.println("BlackKing in check by " + board[i][j].getClass().getSimpleName() + board[i][j].getCol() + board[i][j].getRow());
                                System.out.println(board[i][j].getPieceVision());
                                return true;
                            }
                        }
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
                    ArrayList<String> pieceVision = board[i][j].getPieceFullVision();
                    if (board[i][j].getClass().getSimpleName().contains("White")) {
                        for (String vision : pieceVision) {
                            if  (vision !=null && vision.contains("BlackKing")) {
                                System.out.println("BlackKing in check by " + board[i][j].getClass().getSimpleName() + board[i][j].getCol() + board[i][j].getRow());
                                System.out.println(board[i][j].getPieceVision());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public void printBoard() {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getClass().getSimpleName() + " ");
                }else if(board[i][j] == null){
                    System.out.print("---------- ");
                }
            }
            System.out.println();
        }
    }
    public boolean isWhiteInCheck(Pieces[][] board, int originalCol, int originalRow, int targetCol, int targetRow) {
        Pieces[][] boardCopy = board.clone();
        for (int i = 0; i < board.length; i++) {
            boardCopy[i] = board[i].clone(); // clones the row array
        }
        BoardControl boardControl = new BoardControl();
        boardCopy[originalRow][originalCol] = null;
        boardCopy[targetRow][targetCol] = new WhiteKing(targetCol, targetRow, boardCopy, boardControl);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardCopy[i][j] != null && boardCopy[i][j].getClass().getSimpleName().contains("Black")) {
                    ArrayList<String> pieceVision = boardCopy[i][j].getPieceFullVision();
                    if (pieceVision.contains("WhiteKing")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
