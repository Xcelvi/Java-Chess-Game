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
        board[0][0] = "BlackRook";
        board[0][7] = "WhiteRook";
        board[7][0] = "BlackRook";
        board[7][7] = "WhiteRook";
        //initialize knights
        board[0][1] = "BlackKnight";
        board[0][6] = "BlackKnight";
        board[7][1] = "WhiteKnight";
        board[7][6] = "WhiteKnight";
        //initialize bishop
        board[0][2] = board[0][5] = "BlackBishop";
        board[7][2] = board[7][5] = "WhiteBishop";
        //initialize queen
        board[0][3] = "BlackQueen";
        board[7][3] = "WhiteQueen";
        // initialize king
        board[0][4] = "BlackKing";
        board[7][4] = "WhiteKing";
        // fill empty squares

        for (int i = 2; i < 6; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = "----------";
            }
        }
        printBoard();
    }
    //takes input and updates the chess board
    public void movePiece(int colPiece, int rowPiece, int colLocation, int rowLocation) {
        String pieceName = board[rowPiece][colPiece];
        if (pieceName.equals("----------")){
            System.out.println("Invalid move");
            return;
        }
        Pieces piece = Pieces.getPiece(pieceName, colPiece, rowPiece);
        if (piece.isValidMove(colLocation, rowLocation)) {
            System.out.println();
            System.out.println();
            board[rowLocation][colLocation] = board[rowPiece][colPiece];
            board[rowPiece][colPiece] = "----------";
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
