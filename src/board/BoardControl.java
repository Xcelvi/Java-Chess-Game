package board;

import pieces.Pieces;
import pieces.Vision;

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
                        ((Vision) piece).getPieceFullVision(col, row);
                    }
                }
            }
        }
    }
}
