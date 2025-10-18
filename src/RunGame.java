import board.ChessBoard;

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

            String pieceRowString = move.charAt(0) + "";
            int pieceRow = rowInput(pieceRowString);
            int pieceColumn = colInput(move.charAt(1) + "");


            String moveRowString = move.charAt(3) + "";
            int moveRow = rowInput(moveRowString);
            int movenColumn = colInput(move.charAt(4) + "");
            System.out.println("PieceRow: " + pieceRow + " PieceColumn: " + pieceColumn + " Move: " + moveRow + " Move: " + movenColumn);
            board.movePiece(pieceColumn, pieceRow, movenColumn, moveRow);
        } while (true);
    }

    // Below is taking user inputs and converting it into usable row / columns in my 2d array
    private static int rowInput(String rowInput) {
        int rowValue = -1;
        switch (rowInput) {
            case "A" : rowValue = 0; break;
            case "B" : rowValue = 1; break;
            case "C" : rowValue = 2; break;
            case "D" : rowValue = 3; break;
            case "E" : rowValue = 4; break;
            case "F" : rowValue = 5; break;
            case "G" : rowValue = 6; break;
            case "H" : rowValue = 7; break;
            default:
                System.out.println("Invalid input");
                break;
        }
        return rowValue;
    }
    private static int colInput(String colInput) {
        int colValue  = Integer.parseInt(colInput);
        switch (colValue) {
            case 8 : colValue = 0; break;
            case 7 : colValue = 1; break;
            case 6 : colValue = 2; break;
            case 5 : colValue = 3; break;
            case 4 :
                break;
            case 3 : colValue = 5; break;
            case 2 : colValue = 6; break;
            case 1 : colValue = 7; break;
            default:
                System.out.println("Invalid input");
                break;
        }
        return colValue;
    }


}
