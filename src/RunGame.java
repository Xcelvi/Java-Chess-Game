import board.ChessBoard;
import pieces.Pieces;
import pieces.white.WhitePawn;

import java.util.Scanner;

public class RunGame {
    public static void main(String[] args) {

        //Initializes and prints the chess board, and begins the game
        ChessBoard board = new ChessBoard();
        board.initializeBoard();

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Please enter the coordinates of the piece you would like to move and the move location:");
            String move = sc.nextLine();
            if (move.equals("end")) {
                break;
            }

            int pieceRow = rowInput(move.charAt(1) + "");
            int pieceColumn = colInput(move.charAt(0) + "");

            int moveRow = rowInput(move.charAt(4) + "");
            int movenColumn = colInput(move.charAt(3) + "");
            board.movePiece(pieceColumn, pieceRow, movenColumn, moveRow);
        } while (true);
    }

    // Below is taking user inputs and converting it into usable row / columns in my 2d array
    private static int colInput(String colInput) {
        int colValue = -1;
        switch (colInput) {
            case "A" : colValue = 0; break;
            case "B" : colValue = 1; break;
            case "C" : colValue = 2; break;
            case "D" : colValue = 3; break;
            case "E" : colValue = 4; break;
            case "F" : colValue = 5; break;
            case "G" : colValue = 6; break;
            case "H" : colValue = 7; break;
            default:
                System.out.println("Invalid input");
                break;
        }
        return colValue;
    }
    private static int rowInput(String rowInput) {
        int rowValue  = Integer.parseInt(rowInput);
        switch (rowValue) {
            case 8 : rowValue = 0; break;
            case 7 : rowValue = 1; break;
            case 6 : rowValue = 2; break;
            case 5 : rowValue = 3; break;
            case 4 :
                break;
            case 3 : rowValue = 5; break;
            case 2 : rowValue = 6; break;
            case 1 : rowValue = 7; break;
            default:
                System.out.println("Invalid input");
                break;
        }
        return rowValue;
    }


}
