package pieces;

import pieces.black.*;
import pieces.white.*;

import java.util.Objects;

public abstract class Pieces {
    private int col;
    private int row;
    protected boolean hasMoved;
    protected Pieces[][] board;

    public Pieces(int col, int row, Pieces[][] board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }

    public abstract boolean isValidMove(int targetCol, int targetRow);

    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveWhite(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff) return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;
        while (colLocation != targetCol && rowLocation != targetRow) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
            System.out.println("stepping");
        }
        Pieces piece = board[rowLocation][colLocation];
        return !piece.getClass().getSimpleName().contains("White");
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setRow(int row) {
        this.row = row;
    }
    //Implements Vertical and horizontal movements for rook/ queen of white
    public boolean horizontalVerticalMoveWhite(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0) {
            colStep = -1;
        } else if (colLocation - targetCol < 0) {
            colStep = 1;
        } else if (rowLocation - targetRow > 0) {
            rowStep = -1;
        } else if (rowLocation - targetRow < 0) {
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        Pieces piece = board[rowLocation][colLocation];
        return !piece.getClass().getSimpleName().contains("White");
    }

    //Implements diagonal movements for bishop / queen of white
    public boolean diagonalMoveBlack(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();

        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff != rowDiff) return false;

        int colStep = (targetCol > colLocation) ? 1 : -1;
        int rowStep = (targetRow > rowLocation) ? 1 : -1;

        colLocation += colStep;
        rowLocation += rowStep;

        while (colLocation != targetCol && rowLocation != targetRow) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        Pieces piece = board[rowLocation][colLocation];
        return !piece.getClass().getSimpleName().contains("Black");
    }

    //Implements Vertical and horizontal movements for rook/ queen of Black
    public boolean horizontalVerticalMoveBlack(int targetCol, int targetRow) {
        int colLocation = getCol();
        int rowLocation = getRow();
        int colDiff = Math.abs(colLocation - targetCol);
        int rowDiff = Math.abs(rowLocation - targetRow);

        if (colDiff > 0 && rowDiff > 0) return false;

        int colStep = 0, rowStep = 0;

        if (colLocation - targetCol > 0) {
            colStep = -1;
        } else if (colLocation - targetCol < 0) {
            colStep = 1;
        } else if (rowLocation - targetRow > 0) {
            rowStep = -1;
        } else if (rowLocation - targetRow < 0) {
            rowStep = 1;
        }
        colLocation += colStep;
        rowLocation += rowStep;
        while ((colLocation == targetCol && rowLocation != targetRow) || (colLocation != targetCol && rowLocation == targetRow)) {
            if (board[rowLocation][colLocation] != null) {
                return false;
            }
            colLocation = colLocation + colStep;
            rowLocation = rowLocation + rowStep;
        }
        Pieces piece = board[rowLocation][colLocation];
        return !piece.getClass().getSimpleName().contains("Black");
    }

    public boolean isKingInCheckWhite(int colLocation, int rowLocation) {
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
                if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("BlackKnight")) {
                    return true;
                }
            }
        }

        // Handle pawn attacks
        int[][] pawnAttacks = {{-1, -1}, {-1, 1}};
        for (int[] move : pawnAttacks) {
            tempRowLocation = rowLocation + move[0];
            tempColLocation = colLocation + move[1];
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if ((board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("BlackPawn")))
                    return true;
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
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if (Objects.equals(board[tempRowLocation][tempColLocation].getClass().getSimpleName(), "BlackKing")) {
                    return true;
                }
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //check if straight up is a rook or a queen
        for (int[] d : directions) {
            tempColLocation = colLocation + d[0];
            tempRowLocation = rowLocation + d[1];
            while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)) {
                if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("White") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("Black")) {
                    if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("BlackRook") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("BlackQueen")) {
                        //you are in check
                        return true;
                    }
                    break;
                }
                tempColLocation += d[0];
                tempRowLocation -= d[1];
            }
            directions = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            for (int[] c : directions) {
                tempColLocation = colLocation + c[0];
                tempRowLocation = rowLocation + c[1];
                while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)) {
                    if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("White") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("Black")) {
                        if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("BlackBishop") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("BlackQueen")) {
                            //you are in check
                            return true;
                        }
                        break;
                    }
                    tempColLocation += c[0];
                    tempRowLocation -= c[1];
                }
            }
        }
        return false;
    }
    public boolean isKingInCheckBlack(int colLocation, int rowLocation) {
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
                if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("WhiteKnight")) {
                    return true;
                }
            }
        }

        // Handle pawn attacks
        int[][] pawnAttacks = {{-1, -1}, {-1, 1}};
        for (int[] move : pawnAttacks) {
            tempRowLocation = rowLocation + move[0];
            tempColLocation = colLocation + move[1];
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if ((board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("WhitePawn")))
                    return true;
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
            if (tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8) {
                if (Objects.equals(board[tempRowLocation][tempColLocation].getClass().getSimpleName(), "WhiteKing")) {
                    return true;
                }
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //check if straight up is a rook or a queen
        for (int[] d : directions) {
            tempColLocation = colLocation + d[0];
            tempRowLocation = rowLocation + d[1];
            while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)) {
                if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("White") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("Black")) {
                    if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("WhiteRook") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("WhiteQueen")) {
                        //you are in check
                        return true;
                    }
                    break;
                }
                tempColLocation += d[0];
                tempRowLocation -= d[1];
            }
            directions = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            for (int[] c : directions) {
                tempColLocation = colLocation + c[0];
                tempRowLocation = rowLocation + c[1];
                while ((tempRowLocation >= 0 && tempRowLocation < 8 && tempColLocation >= 0 && tempColLocation < 8)) {
                    if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("White") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("Black")) {
                        if (board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("WhiteBishop") || board[tempRowLocation][tempColLocation].getClass().getSimpleName().contains("WhiteQueen")) {
                            //you are in check
                            return true;
                        }
                        break;
                    }
                    tempColLocation += c[0];
                    tempRowLocation -= c[1];
                }
            }
        }
        return false;
    }
}