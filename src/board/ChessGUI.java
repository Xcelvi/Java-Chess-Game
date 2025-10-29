package board;

import pieces.Pieces;

import javax.swing.*;
import java.awt.*;

public class ChessGUI {
    private final BoardControl boardControl;
    private final JPanel chessBoardPanel;
    private final JButton[][] squares = new JButton[8][8];

    public ChessGUI(BoardControl boardControl) {
        this.boardControl = boardControl;
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

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
                square.setBackground(whiteSquare ? Color.WHITE : Color.BLUE);
                squares[row][col] = square;

                final int r = row;
                final int c = col;
                square.addActionListener(e -> handleClick(r, c));

                chessBoardPanel.add(square);
                whiteSquare = !whiteSquare;
            }
        }
    }

    public static void main(String[] args) {
        new ChessGUI(new BoardControl());
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
    int selectedRow = -1;
    int selectedCol = -1;
    private void handleClick(int row, int col) {
        Pieces[][] board = boardControl.getBoard();
        if (selectedRow == -1 && selectedCol == -1) {
            if(board[row][col] != null) {
                selectedRow = row;
                selectedCol = col;
            }
            return;
        }


        Pieces movingPiece = board[selectedRow][selectedCol];
        boardControl.movePiece(selectedCol, selectedRow, col, row, boardControl);
        if (board[row][col] == movingPiece) {
            boardControl.setBoardVision();
            updateBoard();
        }

        selectedRow = -1;
        selectedCol = -1;
        }
}
