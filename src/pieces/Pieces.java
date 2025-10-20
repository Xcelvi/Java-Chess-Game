package pieces;

import board.ChessBoard;

import java.util.ArrayList;

public abstract class Pieces extends ChessBoard {

    public abstract boolean isValidMove(int pieceRow, int pieceCol, int targetRow, int targetCol, String pieceType);
}

