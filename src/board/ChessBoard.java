package board;

import pieces.Pieces;
import pieces.black.*;
import pieces.white.*;


public class ChessBoard {
    private Pieces[][] board = new  Pieces[8][8];
    //loops over to initialize entire chess board
    public void initializeBoard() {
        // initialize pawns

        //initialize rooks
        board[0][0] = new BlackRook(0, 0, board);
        board[0][7] = new BlackRook(0, 7, board);
        board[7][0] = new WhiteRook(7, 0, board);
        board[7][7] = new WhiteRook(7, 7, board);
        //initialize knights
        board[0][1] = new BlackKnight(0,1,board);
        board[0][6] = new BlackKnight(0,6,board);
        board[7][1] = new WhiteKnight(7,1,board);
        board[7][6] = new WhiteKnight(7,6,board);
        //initialize bishop
        board[0][2] = new BlackBishop(0,2,board);
        board[0][5] =  new BlackBishop(0,5,board);
        board[7][2] = new WhiteBishop(7,2,board);
        board[7][5] =  new WhiteBishop(7,5,board);
                //initialize queen
        board[0][3] = new BlackQueen(0,3,board);
        board[7][3] = new WhiteQueen(7,3,board);
        // initialize king
        board[0][4] = new BlackKing(0,4,board);
        board[7][4] = new WhiteKing(7,4,board);

        printBoard();
    }
    //takes input and updates the chess board
    public void movePiece(int colPiece, int rowPiece, int colLocation, int rowLocation, int turn) {
        Pieces piece = board[rowPiece][colPiece];

        boolean whiteInCheck = false;
        boolean blackInCheck = false;
        if (piece == null) {
            System.out.println("Invalid move");
            return;
        }
        if (turn % 2 == 0 && piece.getClass().getSimpleName().contains("White")) {
            System.out.println("Invalid move, blacks turn");
            return;
        } else if (turn % 2 == 0 && piece.getClass().getSimpleName().contains("Black")){
            System.out.println("Invalid move, whites turn");
            return;
        }
        if (piece.isValidMove(colLocation, rowLocation)) {
            System.out.println();
            System.out.println();
            board[rowLocation][colLocation] = board[rowPiece][colPiece];
            board[rowPiece][colPiece] = null;

        } else{
            System.out.println("Invalid move, please try again");
        }
        printBoard();
    }

    protected void printBoard() {
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
}
