package pieces.white;

import pieces.Pieces;
import pieces.Vision;

import java.util.ArrayList;

public class WhiteRook extends Pieces implements Vision {
    public WhiteRook(int col, int row, Pieces[][] board) {
        super(col, row, board);
        this.name = "WhiteRook";
        this.pieceValue = 500;
    }
    ArrayList<String> pieceVision = new ArrayList<>();
    @Override
    public boolean isValidMove(int targetCol, int targetRow) {
        return horizontalVerticalMoveWhite(targetCol, targetRow);
    }
    @Override
    public ArrayList<String> getPieceFullVision() {
        ArrayList<String> pieceVision = new ArrayList<>();
        int col = this.getCol();
        int row = this.getRow();
        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int[] d : directions) {
            int tempColLocation = col;
            int tempRowLocation = row;
            tempColLocation += d[0];
            tempRowLocation += d[1];
            while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
                String square;
                if (board[tempRowLocation][tempColLocation] == null){
                    square = "null";
                }else {
                    square = board[tempRowLocation][tempColLocation].getClass().getSimpleName();
                }
                pieceVision.add(square+ tempColLocation + tempRowLocation);

                if (board[tempRowLocation][tempColLocation] != null) break;

                tempColLocation += d[0];
                tempRowLocation += d[1];
            }
            pieceVision.add("|");
            }
        setPieceVision(new  ArrayList<>());
        setPieceVision(pieceVision);
        return pieceVision;
    }

}
