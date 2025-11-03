import board.*;

import javax.swing.*;
import java.util.Scanner;

public class RunGame {
    public static void main(String[] args) {

        BoardControl board = new BoardControl();
        board.initializeBoard();

        AI ai = new AI(board);
        AIOld aiOld = new AIOld(board);
        ChessGUI chessGUI = new ChessGUI(board, ai);
        Scanner sc = new Scanner(System.in);

        while (true) {
            int turn = board.getTurn();
            boolean isHumanTurn = turn % 2 == 1;
            boolean isWhite = turn % 2 == 1;

            if (true) {
                System.out.println("Please enter the coordinates of the piece you would like to move and the move location:");
                String move = sc.nextLine();
                if (move.equals("end")) break;

                int pieceRow = rowInput(move.charAt(1) + "");
                int pieceCol = colInput(move.charAt(0) + "");
                int moveRow = rowInput(move.charAt(4) + "");
                int moveCol = colInput(move.charAt(3) + "");

                if (board.isMoveLegal(pieceCol, pieceRow, moveCol, moveRow, board)) {
                    board.movePiece(pieceCol, pieceRow, moveCol, moveRow, board);
                    board.setBoardVision();
                    SwingUtilities.invokeLater(chessGUI::updateBoard);
                    board.printBoard();
                } else {
                    System.out.println("Invalid move");
                }
            } else if (!isHumanTurn) { // AI turn
                System.out.println("AI is thinking...");
                try { Thread.sleep(5); } catch (InterruptedException ignored) {}
                Move aiMove = ai.findBestMove(false, 3);

                if (aiMove != null) {
                    board.makeMove(aiMove);
                    board.setBoardVision();
                    SwingUtilities.invokeLater(chessGUI::updateBoard);
                    board.printBoard();
                    System.out.println("AI move: " + aiMove);
                } else {
                    System.out.println("No AI move found");
                    board.setBoardVision();
                    SwingUtilities.invokeLater(chessGUI::updateBoard);
                    board.printBoard();
                    break;
                }
//            } else {
//                System.out.println("AI is thinking...");
//                try { Thread.sleep(5); } catch (InterruptedException ignored) {}
//                Move aiMove = aiOld.findBestMove(false, 3);
//
//                if (aiMove != null) {
//                    board.makeMove(aiMove);
//                    board.setBoardVision();
//                    SwingUtilities.invokeLater(chessGUI::updateBoard);
//                    board.printBoard();
//                    System.out.println("AI move: " + aiMove);
//                } else {
//                    System.out.println("No AI move found");
//                    board.setBoardVision();
//                    SwingUtilities.invokeLater(chessGUI::updateBoard);
//                    board.printBoard();
//                    break;
//                }
            }
        }
    }

    private static int colInput(String colInput) {
        return switch (colInput.toUpperCase()) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            case "D" -> 3;
            case "E" -> 4;
            case "F" -> 5;
            case "G" -> 6;
            case "H" -> 7;
            default -> -1;
        };
    }

    private static int rowInput(String rowInput) {
        int rowValue = Integer.parseInt(rowInput);
        return switch (rowValue) {
            case 8 -> 0;
            case 7 -> 1;
            case 6 -> 2;
            case 5 -> 3;
            case 4 -> 4;
            case 3 -> 5;
            case 2 -> 6;
            case 1 -> 7;
            default -> -1;
        };
    }
}
