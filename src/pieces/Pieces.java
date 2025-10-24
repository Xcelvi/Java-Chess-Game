package pieces;

import board.ChessBoard;
import pieces.black.*;
import pieces.white.*;

import java.util.Objects;

public abstract class Pieces extends ChessBoard {
    private int col;
    private int row;
    protected String[][] board;
    public Pieces(int col, int row, String[][] board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }
    public Pieces(int col, int row) {
        this.col = col;
        this.row = row;
    }

    //Initiates polymorphism for pieces
    public static Pieces getPiece(String pieceName, int col, int row, String[][] board) {
        return switch (pieceName){
            case "WhitePawn" -> new  WhitePawn(col, row, board);
            case "WhiteBishop" -> new WhiteBishop(col, row, board);
            case "WhiteRook" -> new WhiteRook(col, row, board);
            case "WhiteRookL" -> new WhiteRookL(col, row, board);
            case "WhiteRookR" -> new WhiteRookR(col, row, board);
            case "WhiteKing" -> new WhiteKing(col, row, board);
            case "WhiteQueen" -> new WhiteQueen(col, row, board);
            case "WhiteKnight" -> new WhiteKnight(col, row, board);
            case "BlackPawn" -> new BlackPawn(col, row, board);
            case "BlackBishop" -> new BlackBishop(col, row, board);
            case "BlackRook" -> new BlackRook(col, row, board);
            case "BlackRookL" -> new BlackRookL(col, row, board);
            case "BlackRookR" -> new BlackRookR(col, row, board);
            case "BlackKing" -> new BlackKing(col, row, board);
            case "BlackQueen" -> new BlackQueen(col, row, board);
            case "BlackKnight" -> new BlackKnight(col, row, board);
            default -> throw new IllegalStateException("Unexpected value: " + pieceName);
        };
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public abstract boolean isValidMove(int targetCol, int targetRow);
    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveWhite(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff)  return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;

        while (colLocation != targetCol && rowLocation != targetRow ){
            if (!board[rowLocation][colLocation].contains("-")){
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("White");
    }
    //Implements Vertical and horizontal movements for rook/ queen of white
    public boolean horizontalVerticalMoveWhite(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0){
            colStep = -1;
        } else if (colLocation - targetCol < 0){
            colStep = 1;
        } else if (rowLocation - targetRow > 0){
            rowStep = -1;
        } else if (rowLocation - targetRow < 0){
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)){
            if (!board[rowLocation][colLocation].contains("-")){
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("White");
    }
    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveBlack(int targetCol, int targetRow){
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff)  return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;

        while (colLocation != targetCol && rowLocation != targetRow ){
            if (!board[rowLocation][colLocation].contains("-")){
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("Black");
    }
    //Implements Vertical and horizontal movements for rook/ queen of Black
    public boolean horizontalVerticalMoveBlack(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0){
            colStep = -1;
        } else if (colLocation - targetCol < 0){
            colStep = 1;
        } else if (rowLocation - targetRow > 0){
            rowStep = -1;
        } else if (rowLocation - targetRow < 0){
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)){
            if (!board[rowLocation][colLocation].contains("-")){
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        return !board[rowLocation][colLocation].contains("Black");
    }

    public boolean isKingInCheckWhite(int colLocation, int rowLocation){
        //Check if black knight is there
        int tempColLocation;
        int tempRowLocation;
        int[][] knightMoves = {
                {-2, -1}, {-2, +1},
                {-1, +2}, {+1, +2},
                {+2, +1}, {+2, -1},
                {-1, -2}, {+1, -2}
        };

        for (int[] move : knightMoves) {
            tempColLocation = colLocation + move[1];
            tempRowLocation = rowLocation + move[0];

            // Check board bounds (0–7 if 8×8 board)
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if (Objects.equals(board[tempRowLocation][tempColLocation], "BlackKnight")) {
                    return true; // Found one, do what you need
                }
            }
        }

        // Handle pawn attacks
        int[][] pawnAttacks = {{-1, -1}, {-1, 1}};
        for (int[] move : pawnAttacks) {
            tempRowLocation = rowLocation + move[0];
            tempColLocation = colLocation + move[1];
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if ("BlackPawn".equals(board[tempRowLocation][tempColLocation])) return true;
            }
        }


        //Check if moving next to black king
        int[][] aroundKingMoves = {
                {-1, -1}, {-1, 0},
                {-1, 1}, {0, 1},
                {1, 1}, {1, 0},
                {1, -1}, {0, -1}
        };
        for (int[] move : aroundKingMoves) {
            tempRowLocation = rowLocation + move[0];
            tempColLocation = colLocation + move[1];
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8){
                if (Objects.equals(board[tempRowLocation][tempColLocation], "BlackKing")) {
                    return true;
                }
            }
        }
        tempColLocation = colLocation;
        tempRowLocation = rowLocation - 1;
        //check if straight up is a rook or a queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)) {
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                    if (board[tempRowLocation][tempColLocation].equals("BlackRook") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                        //you are in check
                        return true;
                    }
                    break;
                }
            tempColLocation += 0;
            tempRowLocation -= 1;
        }
        tempColLocation = colLocation;
        tempRowLocation = rowLocation + 1;
        //check if straight down is a rook or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("BlackRook") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 0;
            tempRowLocation += 1;
        }
        tempColLocation = colLocation + 1;
        tempRowLocation = rowLocation;
        //check if straight right is rook or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("BlackRook") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 1;
            tempRowLocation += 0;
        }
        tempColLocation = colLocation - 1;
        tempRowLocation = rowLocation;
        //check if straight left is rook or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("BlackRook") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation -= 1;
            tempRowLocation += 0;
        }
        tempColLocation = colLocation + 1;
        tempRowLocation = rowLocation + 1;
        //check if diagonal right down is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("BlackBishop") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 1;
            tempRowLocation += 1;
        }
        tempColLocation = colLocation - 1;
        tempRowLocation = rowLocation + 1;
        //check if left down is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("BlackBishop") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation -= 1;
            tempRowLocation += 1;
        }
        tempColLocation = colLocation - 1;
        tempRowLocation = rowLocation - 1;
        //check if up left is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("BlackBishop") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation -= 1;
            tempRowLocation -= 1;
        }
        tempColLocation = colLocation + 1;
        tempRowLocation = rowLocation - 1;
        //check if up right is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("BlackBishop") || board[tempRowLocation][tempColLocation].equals("BlackQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 1;
            tempRowLocation -= 1;
        }
        return false;
    }
    public boolean isKingInCheckBlack(int colLocation, int rowLocation){
        //Check if black knight is there
        int tempColLocation;
        int tempRowLocation;
        int[][] knightMoves = {
                {-2, -1}, {-2, +1},
                {-1, +2}, {+1, +2},
                {+2, +1}, {+2, -1},
                {-1, -2}, {+1, -2}
        };
        for (int[] move : knightMoves) {
            tempColLocation = colLocation + move[1];
            tempRowLocation = rowLocation + move[0];

            // Check board bounds (0–7 if 8×8 board)
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if (Objects.equals(board[tempRowLocation][tempColLocation], "WhiteKnight")) {
                    return true; // Found one, do what you need
                }
            }
        }

        // Handle pawn attacks
        int[][] pawnAttacks = {{-1, -1}, {-1, 1}};
        for (int[] move : pawnAttacks) {
            tempRowLocation = rowLocation + move[0];
            tempColLocation = colLocation + move[1];
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if ("WhitePawn".equals(board[tempRowLocation][tempColLocation])) return true;
            }
        }

        //Check if moving next to black king
        int[][] aroundKingMoves = {
                {-1, -1}, {-1, 0},
                {-1, 1}, {0, 1},
                {1, 1}, {1, 0},
                {1, -1}, {0, -1}
        };
        for (int[] move : aroundKingMoves) {
            tempRowLocation = rowLocation + move[0];
            tempColLocation = colLocation + move[1];
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8){
                if (Objects.equals(board[tempRowLocation][tempColLocation], "WhiteKing")) {
                    return true;
                }
            }
        }
        tempColLocation = colLocation;
        tempRowLocation = rowLocation - 1;
        //check if straight up is a rook or a queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)) {
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteRook") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 0;
            tempRowLocation -= 1;
        }
        tempColLocation = colLocation;
        tempRowLocation = rowLocation + 1;
        //check if straight down is a rook or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteRook") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 0;
            tempRowLocation += 1;
        }
        tempColLocation = colLocation + 1;
        tempRowLocation = rowLocation;
        //check if straight right is rook or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteRook") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 1;
            tempRowLocation += 0;
        }
        tempColLocation = colLocation - 1;
        tempRowLocation = rowLocation;
        //check if straight left is rook or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteRook") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation -= 1;
            tempRowLocation += 0;
        }
        tempColLocation = colLocation + 1;
        tempRowLocation = rowLocation + 1;
        //check if diagonal right down is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteBishop") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 1;
            tempRowLocation += 1;
        }
        tempColLocation = colLocation - 1;
        tempRowLocation = rowLocation + 1;
        //check if left down is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteBishop") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation -= 1;
            tempRowLocation += 1;
        }
        tempColLocation = colLocation - 1;
        tempRowLocation = rowLocation - 1;
        //check if up left is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteBishop") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation -= 1;
            tempRowLocation -= 1;
        }
        tempColLocation = colLocation + 1;
        tempRowLocation = rowLocation - 1;
        //check if up right is bishop or queen
        while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)){
            if (board[tempRowLocation][tempColLocation].contains("White") || board[tempRowLocation][tempColLocation].contains("Black")){
                if (board[tempRowLocation][tempColLocation].equals("WhiteBishop") || board[tempRowLocation][tempColLocation].equals("WhiteQueen")) {
                    //you are in check
                    return true;
                }
                break;
            }
            tempColLocation += 1;
            tempRowLocation -= 1;
        }
        return false;
    }
}