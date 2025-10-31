package board;

import pieces.Pieces;

import javax.swing.*;
import java.awt.*;

public class ChessGUI {
    private final BoardControl boardControl;
    private final JPanel chessBoardPanel;
    private final JButton[][] squares = new JButton[8][8];
    private final AI ai;

    public ChessGUI(BoardControl boardControl, AI ai) {
        this.boardControl = boardControl;
        this.ai = ai;
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        chessBoardPanel = new JPanel(new GridLayout(8, 8));
        initializeBoard();

        frame.add(chessBoardPanel);
        frame.setVisible(true);
        updateBoard();
    }

    public void initializeBoard() {
        boolean whiteSquare = true;
        for (int row = 0; row < 8; row++) {
            whiteSquare = !whiteSquare; // alternate colors each row
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setOpaque(true);
                square.setBorderPainted(false);
                square.setBackground(whiteSquare ? Color.WHITE : Color.PINK);
                squares[row][col] = square;

                final int r = row;
                final int c = col;
                square.addActionListener(e -> handleClick(r, c));

                chessBoardPanel.add(square);
                whiteSquare = !whiteSquare;
            }
        }
    }

    public void updateBoard() {
        Pieces[][] board = boardControl.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = squares[row][col];
                if (board[row][col] != null) {
                    square.setText(getPieceSymbol(board[row][col]));
                    square.setFont(new Font("Arial", Font.BOLD, 32));
                } else {
                    square.setText("");
                }
            }
        }
        chessBoardPanel.revalidate();
        chessBoardPanel.repaint();
    }

    private String getPieceSymbol(Pieces piece) {
        String name = piece.getClass().getSimpleName();
        return switch (name) {
            case "WhiteKing" -> "♔";
            case "WhiteQueen" -> "♕";
            case "WhiteRook" -> "♖";
            case "WhiteBishop" -> "♗";
            case "WhiteKnight" -> "♘";
            case "WhitePawn" -> "♙";
            case "BlackKing" -> "♚";
            case "BlackQueen" -> "♛";
            case "BlackRook" -> "♜";
            case "BlackBishop" -> "♝";
            case "BlackKnight" -> "♞";
            case "BlackPawn" -> "♟";
            default -> "Unknown";
        };
    }


    private int selectedRow = -1;
    private int selectedCol = -1;
    private void handleClick(int row, int col) {
        Pieces[][] board = boardControl.getBoard();

        if (selectedRow == -1 && selectedCol == -1) {
            if (board[row][col] != null) {
                selectedRow = row;
                selectedCol = col;
            }
            return;
        }

        if (boardControl.isMoveLegal(selectedCol, selectedRow, col, row, boardControl)) {
            boardControl.movePiece(selectedCol, selectedRow, col, row, boardControl);
            boardControl.setBoardVision();
            boardControl.generateAllLegalMoves(boardControl);
            updateBoard();
            selectedRow = -1;
            selectedCol = -1;

//             Run AI move on a separate thread
            new Thread(() -> {
                Move aiMove = ai.findBestMove(false, 3);
                if (aiMove != null) {
                    SwingUtilities.invokeLater(() -> {
                        boardControl.makeMove(aiMove);
                        boardControl.setBoardVision();
                        updateBoard();
                    });
                }
            }).start();
        }
        selectedRow = -1;
        selectedCol = -1;
    }

}
