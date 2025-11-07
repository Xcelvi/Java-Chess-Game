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
    private ArrayList<Pieces> allPieces = new ArrayList<>();
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
        for (Pieces[] pieces : board) {
            for (Pieces piece : pieces) {
                if (piece != null) {
                    allPieces.add(piece);
                }
            }
        }
    }

    public void setBoardVision(){
        for  (Pieces piece : allPieces) {
                    ((Vision) piece).getPieceFullVision();
        }
    }

    public boolean isWhiteInCheck(){
            for  (Pieces piece : allPieces) {
                ArrayList<String> pieceVision = piece.getPieceFullVision();
                if (piece.getName().contains("Black")) {
                    for (String vision : pieceVision) {
                        if (vision != null && vision.contains("WhiteKing")) {
                            return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isBlackInCheck() {
        for (Pieces piece : allPieces) {
            ArrayList<String> pieceVision = piece.getPieceFullVision();
            if (piece.getName().contains("White")) {
                for (String vision : pieceVision) {
                    if (vision != null && vision.contains("BlackKing")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isWhiteInCheck(ArrayList<Pieces> pieces){
        for  (Pieces piece : pieces) {
            ArrayList<String> pieceVision = piece.getPieceFullVision();
            if (piece.getName().contains("Black")) {
                for (String vision : pieceVision) {
                    if (vision != null && vision.contains("WhiteKing")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isBlackInCheck(ArrayList<Pieces> pieces){
        for  (Pieces piece : pieces) {
            ArrayList<String> pieceVision = piece.getPieceFullVision();
            if (piece.getName().contains("White")) {
                for (String vision : pieceVision) {
                    if  (vision !=null && vision.contains("BlackKing")) {
                        return true;
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
                    System.out.print(board[i][j].getName() + " ");
                }else if(board[i][j] == null){
                    System.out.print("---------- ");
                }
            }
            System.out.println();
        }
    }
    public ArrayList<Pieces> getPieces(){
        return allPieces;
    }
}
