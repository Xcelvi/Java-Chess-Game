package board;

import pieces.Pieces;
import pieces.black.*;
import pieces.white.*;

import java.util.ArrayList;


public class ChessBoard {
    //loops over to initialize entire chess board
    Pieces[][] board = new Pieces[8][8];
    int turn = 1;
    //takes input and updates the chess board
    ArrayList<String> moveLog = new ArrayList<>();
    public void movePiece(int colPiece, int rowPiece, int colLocation, int rowLocation, BoardControl boardControl) {
        if (!isMoveLegal(colPiece, rowPiece, colLocation, rowLocation, boardControl)) {
            return;
        }
        if (board[rowPiece][colPiece] != null) {
            Pieces piece = board[rowPiece][colPiece];
            boolean isWhiteKing = piece instanceof WhiteKing;
            boolean isBlackKing = piece instanceof BlackKing;
            if (piece instanceof WhitePawn && rowLocation == 0){
                piece = promotePawn(piece);
            } else if (piece instanceof BlackPawn && rowLocation == 7){
                piece = promotePawn(piece);
            }
            if (isBlackKing && Math.abs(colLocation - piece.getCol()) == 2) {
                // King-side
                if (colLocation == 6) {
                    Pieces rook = board[0][7];
                    board[0][5] = rook;
                    board[0][7] = null;
                    rook.setCol(5);
                    rook.setRow(0);
                    rook.setHasMoved(true);
                }
                // Queen-side
                else if (colLocation == 2) {
                    Pieces rook = board[0][0];
                    board[0][3] = rook;
                    board[0][0] = null;
                    rook.setCol(3);
                    rook.setRow(0);
                    rook.setHasMoved(true);
                }
            piece.setHasMoved(true);
            }

            if (isWhiteKing && Math.abs(colLocation - piece.getCol()) == 2) {
                // King-side
                if (colLocation == 6) {
                    Pieces rook = board[7][7];
                    board[7][5] = rook;
                    board[7][7] = null;
                    rook.setCol(5);
                    rook.setRow(7);
                    rook.setHasMoved(true);
                }
                // Queen-side
                else if (colLocation == 2) {
                    Pieces rook = board[7][0];
                    board[7][3] = rook;
                    board[7][0] = null;
                    rook.setCol(3);
                    rook.setRow(7);
                    rook.setHasMoved(true);
                }
                piece.setHasMoved(true);

            }

            turn++;
            board[rowLocation][colLocation] = piece;
            board[rowPiece][colPiece] = null;
            board[rowLocation][colLocation].setCol(colLocation);
            board[rowLocation][colLocation].setRow(rowLocation);

            //log the moves
            moveLog.add(board[rowLocation][colLocation].getClass().getSimpleName() + colPiece + rowPiece + colLocation + rowLocation);
            setMoveLog(moveLog);
        }
    }
    public boolean isMoveLegal(int colPiece, int rowPiece, int colLocation, int rowLocation, BoardControl boardControl) {
        Pieces piece = board[rowPiece][colPiece];
        int white = 1;
        int black = 0;
        if (piece == null) {
            System.out.println("Invalid move");
            return false;
        }
        if (turn % 2 == black && piece.getClass().getSimpleName().contains("White")) {
            System.out.println("Invalid move, blacks turn");
            return false;
        } else if (turn % 2 == white && piece.getClass().getSimpleName().contains("Black")) {
            System.out.println("Invalid move, whites turn");
            return false;
        }

        if (piece.isValidMove(colLocation, rowLocation)) {
            Pieces temp = board[rowLocation][colLocation];
            board[rowLocation][colLocation] = board[rowPiece][colPiece];
            board[rowPiece][colPiece] = null;

            if (boardControl.isWhiteInCheck() && turn % 2 == white) {
                board[rowPiece][colPiece] = board[rowLocation][colLocation];
                board[rowLocation][colLocation] = temp;
                return false;
            } else if (boardControl.isBlackInCheck() && turn % 2 == black) {
                board[rowPiece][colPiece] = board[rowLocation][colLocation];
                board[rowLocation][colLocation] = temp;
                return false;
            }
            board[rowPiece][colPiece] = board[rowLocation][colLocation];
            board[rowLocation][colLocation] = temp;

            return true;
        }
        return false;
    }

    public ArrayList<Move> generateAllLegalMoves(BoardControl boardControl) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        boolean isWhiteTurn = turn % 2 == 1;
        boolean isBlackTurn = turn % 2 == 0;
        for  (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Pieces piece = board[row][col];
                if (piece == null) continue;

                if (isWhiteTurn && piece.getClass().getSimpleName().contains("Black")) continue;
                if (isBlackTurn && piece.getClass().getSimpleName().contains("White")) continue;

                boolean isRook = piece.getClass().getSimpleName().contains("Rook");
                boolean isQueen = piece.getClass().getSimpleName().contains("Queen");
                boolean isKing = piece.getClass().getSimpleName().contains("King");
                boolean isPawn = piece.getClass().getSimpleName().contains("Pawn");
                boolean isBishop = piece.getClass().getSimpleName().contains("Bishop");
                boolean isKnight = piece.getClass().getSimpleName().contains("Knight");

                if (isRook){
                    ArrayList<int[]> rookMoves = legalHorizontalVerticalMoves(col, row);
                        for(int[] rookMove : rookMoves){
                            int targetCol = rookMove[0];
                            int targetRow = rookMove[1];
                            if (isMoveLegal(col, row, targetCol, targetRow, boardControl)) legalMoves.add(new Move(col, row, targetCol, targetRow, board[row][col], board[targetRow][targetCol]));
                        }
                } else if (isBishop){
                    ArrayList<int[]> bishopMoves = legalDiagonalMoves(col, row);
                        for(int[] bishopMove : bishopMoves){
                            int targetCol = bishopMove[0];
                            int targetRow = bishopMove[1];
                            if (isMoveLegal(col, row, targetCol, targetRow, boardControl)) legalMoves.add(new Move(col, row, targetCol, targetRow, board[row][col], board[targetRow][targetCol]));
                        }
                } else if  (isQueen){
                    ArrayList<int[]> queenMoves = legalMovesQueen(col, row);
                        for(int[] queenMove : queenMoves){
                            int targetCol = queenMove[0];
                            int targetRow = queenMove[1];
                            if (isMoveLegal(col, row, targetCol, targetRow, boardControl)) legalMoves.add(new Move(col, row, targetCol, targetRow, board[row][col], board[targetRow][targetCol]));
                        }

                } else if (isKing){
                    int[][] kingMoves = {
                            {-1, -1}, {-1, 0},
                            {-1, 1}, {0, 1},
                            {1, 1}, {1, 0},
                            {1, -1}, {0, -1}
                    };

                    for(int[] kingMove : kingMoves){
                        int targetCol = col + kingMove[0];
                        int targetRow = row + kingMove[1];
                        if (isMoveLegal(col, row, targetCol, targetRow, boardControl)) legalMoves.add(new Move(col, row, targetCol, targetRow, board[row][col], board[targetRow][targetCol]));
                        }
                } else if (isPawn){
                    if (isBlackTurn) {
                        int[][] blackPawnMoves = {
                                {1, 0},
                                {2, 0},
                                {1, -1},
                                {1, 1}
                        };
                        for(int[] blackPawnMove : blackPawnMoves){
                            int targetCol = col + blackPawnMove[0];
                            int targetRow = row + blackPawnMove[1];
                            if (isMoveLegal(col, row, targetCol, targetRow, boardControl)) legalMoves.add(new Move(col, row, targetCol, targetRow, board[row][col], board[targetRow][targetCol]));
                        }
                    } else if (isWhiteTurn) {
                        int[][] whitePawnMoves = {
                                {-1, 0},
                                {-2, 0},
                                {-1, -1},
                                {-1, 1}
                        };
                        for(int[] whitePawnMove : whitePawnMoves){
                            int targetCol = col + whitePawnMove[0];
                            int targetRow = row + whitePawnMove[1];
                            if (isMoveLegal(col, row, targetCol, targetRow, boardControl)) legalMoves.add(new Move(col, row, targetCol, targetRow, board[row][col], board[targetRow][targetCol]));
                        }
                    }
                } else if (isKnight){
                    int[][] knightMoves = {
                            {-2, -1},
                            {-2, 1},
                            {-1, -2},
                            {-1, 2},
                            {1, -2},
                            {1, 2},
                            {2, -1},
                            {2, 1}
                    };
                    for(int[] knightMove : knightMoves){
                        int targetCol = col + knightMove[0];
                        int targetRow = row + knightMove[1];
                        if (isMoveLegal(col, row, targetCol, targetRow, boardControl)) legalMoves.add(new Move(col, row, targetCol, targetRow, board[row][col], board[targetRow][targetCol]));
                    }
                }
            }
        }
        return legalMoves;
    }

    public Pieces[][] getBoard() {
        return board;
    }
    public ArrayList<String> getMoveLog() {
        return moveLog;
    }
    public void setMoveLog(ArrayList<String> moveLog) {
        this.moveLog = moveLog;
    }
    private Pieces promotePawn(Pieces pawn){
        int row = pawn.getRow();
        int col = pawn.getCol();
        if (pawn instanceof BlackPawn) {
            return new BlackQueen(col, row, board);
        } else if (pawn instanceof WhitePawn) {
            return new WhiteQueen(col, row, board);
        }
        return null;
    }
    public Pieces makeMove(Move move) {
        Pieces captured = move.capturedPiece;
        Pieces movedPiece = move.movedPiece;

        board[move.toRow][move.toCol] = movedPiece;
        board[move.fromRow][move.fromCol] = null;


        movedPiece.setRow(move.toRow);
        movedPiece.setCol(move.toCol);
        return captured;
    }

    public void undoMove(Move move, Pieces captured) {
        Pieces movedPiece = move.movedPiece;

        board[move.fromRow][move.fromCol] = movedPiece;
        board[move.toRow][move.toCol] = captured;


        movedPiece.setRow(move.fromRow);
        movedPiece.setCol(move.fromCol);

    }
    public int getTurn() {
        return turn;
    }
    public void increaseTurn() {
        turn++;
    }
    public static ArrayList<int[]> legalHorizontalVerticalMoves(int col, int row){
        ArrayList<int[]> legalMoves = new ArrayList<>();
        for (int r = row-1; r >= 0; r--) {
            legalMoves.add(new int[]{col, r});
        }
        for (int r = row+1; r < 8; r++) {
            legalMoves.add(new int[]{col, r});
        }
        for (int c = col+1; c < 8; c++) {
            legalMoves.add(new int[]{c, row});
        }
        for (int c = col-1; c >= 0; c--) {
            legalMoves.add(new int[]{c, row});
        }
        return legalMoves;
    }
    public static ArrayList<int[]> legalDiagonalMoves(int col, int row){
        ArrayList<int[]> legalMoves = new ArrayList<>();
        // Up-Left (↖)
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            legalMoves.add(new int[]{r, c});
        }

        // Up-Right (↗)
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
            legalMoves.add(new int[]{r, c});
        }

        // Down-Left (↙)
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
            legalMoves.add(new int[]{r, c});
        }

        // Down-Right (↘)
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
            legalMoves.add(new int[]{r, c});
        }
        return legalMoves;
    }
    public static ArrayList<int[]> legalMovesQueen(int col, int row){
        ArrayList<int[]> legalMoves = new ArrayList<>();
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            legalMoves.add(new int[]{r, c});
        }

        // Up-Right (↗)
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
            legalMoves.add(new int[]{r, c});
        }

        // Down-Left (↙)
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
            legalMoves.add(new int[]{r, c});
        }

        // Down-Right (↘)
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
            legalMoves.add(new int[]{r, c});
        }
        for (int r = row-1; r >= 0; r--) {
            legalMoves.add(new int[]{col, r});
        }
        for (int r = row+1; r < 8; r++) {
            legalMoves.add(new int[]{col, r});
        }
        for (int c = col+1; c < 8; c++) {
            legalMoves.add(new int[]{c, row});
        }
        for (int c = col-1; c >= 0; c--) {
            legalMoves.add(new int[]{c, row});
        }
        return legalMoves;
    }
}