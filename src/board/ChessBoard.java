package board;

import pieces.Pieces;
import pieces.black.*;
import pieces.white.*;

import java.util.ArrayList;


public class ChessBoard {
    private Pieces[][] board = new  Pieces[8][8];
    //loops over to initialize entire chess board
    public void initializeBoard() {
        // initialize pawns
        board[1][0] = new BlackPawn(0, 1, board);
        board[1][1] = new BlackPawn(1, 1, board);
        board[1][2] = new BlackPawn(2, 1, board);
        board[1][3] = new BlackPawn(3, 1, board);
        board[1][4] = new BlackPawn(4, 1, board);
        board[1][5] = new BlackPawn(5, 1, board);
        board[1][6] = new BlackPawn(6, 1, board);
        board[1][7] = new BlackPawn(7, 1, board);
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
        board[0][4] = new BlackKing(4,0,board);
        board[7][4] = new WhiteKing(4,7,board);

        printBoard();
    }
    int turn = 1;
    private int enPassantRow;
    private int enPassantCol;

    //takes input and updates the chess board
    ArrayList<String> moveLog = new ArrayList<>();
    public void movePiece(int colPiece, int rowPiece, int colLocation, int rowLocation, BoardControl boardControl) {
        Pieces piece = board[rowPiece][colPiece];
        int black = 0;
        int white = 1;
        if (piece == null) {
            System.out.println("Invalid move");
            return;
        }
        if (turn % 2 == black && piece.getClass().getSimpleName().contains("White")) {
            System.out.println("Invalid move, blacks turn");
            return;
        } else if (turn % 2 == white && piece.getClass().getSimpleName().contains("Black")){
            System.out.println("Invalid move, whites turn");
            return;
        }
        if (piece.isValidMove(colLocation, rowLocation)) {
            //blacks turn
            System.out.println();
            System.out.println();
            Pieces temp = board[rowLocation][colLocation];
            board[rowLocation][colLocation] = board[rowPiece][colPiece];
            board[rowPiece][colPiece] = null;

            if (boardControl.isWhiteInCheck() && turn % 2 == white) {
                board[rowPiece][colPiece] = board[rowLocation][colLocation];
                board[rowLocation][colLocation] = temp;
                return;
            } else if (boardControl.isBlackInCheck() && turn % 2 == black) {
                board[rowPiece][colPiece] = board[rowLocation][colLocation];
                board[rowLocation][colLocation] = temp;
                return;
            }

            if (piece instanceof WhitePawn) {
                int rowDiff = piece.getRow() - rowLocation;
                if (rowDiff == 2){
                    enPassantRow = rowLocation + 1;
                    enPassantCol = colLocation;
                } else{
                    enPassantRow = -1;
                    enPassantCol = -1;
                }
            } else if (piece instanceof BlackPawn) {
                int rowDiff = piece.getRow() - rowLocation;
                if (rowDiff == -2){
                    enPassantRow = rowLocation - 1;
                    enPassantCol = colLocation;
                } else{
                    enPassantRow = -1;
                    enPassantCol = -1;
                }
            } else{
                enPassantRow = -1;
                enPassantCol = -1;
            }

            board[rowLocation][colLocation].setCol(colLocation);
            board[rowLocation][colLocation].setRow(rowLocation);
            moveLog.add(board[rowLocation][colLocation].getClass().getSimpleName()  + colPiece + rowPiece + colLocation + rowLocation);
            System.out.println("Move Log" + moveLog);
            setMoveLog(moveLog);
            turn += 1;
            System.out.println("turn " + turn);
        } else{
            System.out.println("Invalid move, please try again");
        }
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
    public Pieces[][] getBoard() {
        return board;
    }
    public int getEnPassantRow() {
        return enPassantRow;
    }

    public int getEnPassantCol() {
        return enPassantCol;
    }
    public ArrayList<String> getMoveLog() {
        return moveLog;
    }
    public void setMoveLog(ArrayList<String> moveLog) {
        this.moveLog = moveLog;
    }
}
