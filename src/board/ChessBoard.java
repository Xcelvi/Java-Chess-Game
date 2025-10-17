package board;

public class ChessBoard {
    private String[][] board = new  String[8][8];

    public ChessBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        // initialize pawns
        for (int i = 0; i < 8; i++){
            board[i][1] = "P";
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
    }
}
