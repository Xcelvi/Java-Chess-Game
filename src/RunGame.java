import board.ChessBoard;

import java.util.Scanner;

public class RunGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.printBoard();

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Please enter the coordinates of the piece you would like to move and the move location:");
            String move = sc.nextLine();
            if (move.equals("end")) {
                break;
            }

            String pieceRowString = move.charAt(0) + "";
            int pieceRow = rowInput(pieceRowString);
            int pieceColumn = Integer.parseInt(move.charAt(1) + "");
            System.out.println("Piece row " + pieceRow + " Piece column " + pieceColumn + "\n");

            String moveRowString = move.charAt(3) + "";
            int moveRow = rowInput(moveRowString);
            int movenColumn = Integer.parseInt(move.charAt(4) + "");
            System.out.println("Move row " + moveRow + " Move column " + movenColumn);

        } while (true);
    }

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


}
