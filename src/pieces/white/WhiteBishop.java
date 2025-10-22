package pieces.white;

import board.ChessBoard;
import pieces.Pieces;

import java.util.Arrays;

public class WhiteBishop extends Pieces {
    public WhiteBishop(int col, int row, String[][] board) {
        super(col, row, board);
    }

    //Fix this

    @Override
    public boolean isValidMove(int targetRow, int targetCol){
        int colLocation = getCol();
        int rowLocation = getRow();
        System.out.print("How the board lookin" + Arrays.deepToString(board));
        String[][] tempBoard = board.clone();
        String[][] tempBoard2 = board.clone();
        boolean left = false, right = false, up = false, down = false;

        if (rowLocation - targetRow > 0) {
            up = true;
        } else if (rowLocation - targetRow < 0) {
            down = true;
        } else{
            return false;
        }
        tempBoard2[rowLocation][colLocation] = "----------";
        tempBoard2[targetRow][targetCol] = "WhiteBishop";
        if(colLocation - targetCol > 0) {
            right = true;
        } else if (colLocation - targetCol < 0) {
            left = true;
        }else {
            return false;
        }
        System.out.println("Did rowloc - target and stuff");
        if (left && down){
            while (tempBoard != tempBoard2) {
                System.out.println("got to movement step left down");
                if (tempBoard[rowLocation - 1][colLocation + 1].contains("-")) {
                    tempBoard[rowLocation - 1][colLocation + 1] = "WhiteBishop";
                    tempBoard[rowLocation][colLocation] = "----------";
                } else {
                    return false;
                }
            }
        } else if (left && up){
            while (tempBoard != tempBoard2) {
                System.out.println("got to movement step left up");
                System.out.println("Temp board" + tempBoard[rowLocation - 1][colLocation -1]);
                if (tempBoard[rowLocation - 1][colLocation - 1].contains("-")) {
                    tempBoard[rowLocation - 1][colLocation - 1] = "WhiteBishop";
                    tempBoard[rowLocation][colLocation] = "----------";
                } else {
                    return false;
                }
            }

        } else if (right && down){
            while (tempBoard != tempBoard2) {
                System.out.println("got to movement step right down");
                if (tempBoard[rowLocation + 1][colLocation + 1].contains("-")) {
                    System.out.println("got to movement step");
                    tempBoard[rowLocation + 1][colLocation + 1] = "WhiteBishop";
                    tempBoard[rowLocation][colLocation] = "----------";
                } else {
                    return false;
                }
            }

        } else if (right && up){
            while (tempBoard != tempBoard2) {
                System.out.println("got to movement step right up");
                if (tempBoard[rowLocation + 1][colLocation - 1].contains("-")) {
                    System.out.println("got to movement step");
                    tempBoard[rowLocation + 1][colLocation - 1] = "WhiteBishop";
                    tempBoard[rowLocation][colLocation] = "----------";
                } else {
                    return false;
                }
            }

        } else {
            return false;
        }
        return true;
    }
}
