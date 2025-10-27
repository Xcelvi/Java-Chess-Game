package board;

import pieces.Pieces;
import pieces.Vision;

public class BoardControl extends ChessBoard {


    public BoardControl() {
        super();
    }

    public void setBoardVision(){
        String[][] board = getBoard();

        for (int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                if (!board[row][col].contains("-")) {
                    Pieces piece = Pieces.getPiece(board[row][col], col, row, board);
                    if (piece instanceof Vision) {
                        ((Vision) piece).getPieceFullVision(col, row);
                    }
                }
            }
        }
    }
}
