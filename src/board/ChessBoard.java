package board;

import pieces.Pieces;

import java.util.Arrays;

public class ChessBoard {
    private String[][] board = new  String[8][8];


    //loops over to initialize entire chess board
    public void initializeBoard() {
        // initialize pawns
        for (int i = 0; i < 8; i++){
            board[1][i] = "BlackPawn";
            board[6][i] = "WhitePawn";
        }
        //initialize rooks
        board[0][0] = board[0][7] = "R";
        board[7][0] = board[7][7] = "R";
        //initialize knights
        board[0][1] = board[0][6] = "K";
        board[7][1] = board[7][6] = "K";
        //initialize bishop
        board[0][2] = board[0][5] = "B";
        board[7][2] = board[7][5] = "B";
        //initialize queen
        board[0][3] = "Q";
        board[7][3] = "Q";
        // initialize king
        board[0][4] = "K";
        board[7][4] = "K";
        // fill empty squares

        for (int i = 2; i < 6; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = "-";
            }
        }
        printBoard();
    }
    //takes input and updates the chess board
    public void movePiece(int colPiece, int rowPiece, int colLocation, int rowLocation) {
        String pieceName = board[rowPiece][colPiece];
        Pieces piece = Pieces.getPiece(pieceName, colPiece, rowPiece);
        if (piece.isValidMove(colLocation, rowLocation)) {
            System.out.println();
            System.out.println();
            board[rowLocation][colLocation] = board[rowPiece][colPiece];
            board[rowPiece][colPiece] = "-";
        } else{
            System.out.println("Invalid move, please try again");
        }
        printBoard();
    }

    protected void printBoard() {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + Arrays.toString(board) +
                '}';
    }
}
