package board;

import pieces.Pieces;
import java.util.ArrayList;
import java.util.Comparator;

public class AI {
    private BoardControl board;

    public AI(BoardControl board) {
        this.board = board;
    }

    // Basic evaluation by material
    public int evaluateBoard(ArrayList<Move> legalMoves, boolean isWhite) {
        int score = 0;
        Pieces[][] boardArray = board.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Pieces piece = boardArray[row][col];
                if (piece == null) continue;
                score += piece.getPieceValue();
            }
        }
        return score;
    }
    //Find best move based on eval function, set if you are white, and the depth you want the ai to go
    public Move findBestMove(boolean isWhite, int depth) {
        //Generate the full list of legal moves
        int bestScore = isWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        ArrayList<Move> legalMoves = board.generateAllLegalMoves(board);
        if (isWhite && legalMoves.isEmpty()) {
            System.out.println("Game Over");
            bestScore = Integer.MAX_VALUE;
        } else if(!isWhite && legalMoves.isEmpty()) {
            bestScore = Integer.MIN_VALUE;
            System.out.println("Game Over");
        }
        Move bestMove = null;
        //Loop through legal moves
        for (Move move : legalMoves) {
            // make move and store caputred piece
            Pieces captured = board.makeMove(move);
            int score;
            if (depth > 1) {
                // Use minimax to generate the score of the position after that move
                score = this.minimax(!isWhite, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            } else {
                //Reached base case for depth, return the score.
                score = evaluateBoard(legalMoves, isWhite);
            }

            // undo moves
            board.undoMove(move, captured);

            if (isWhite && score > bestScore) {
                bestScore = score;
                bestMove = move;
            } else if (!isWhite && score < bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    // Minimax helper
    int counter = 0;
    private int minimax(boolean isWhite, int depth, int alpha, int beta) {
        //generate legal moves, initialize score to low/high
        ArrayList<Move> legalMoves = board.generateAllLegalMoves(board);
        legalMoves = orderMoves(legalMoves);
        if (depth == 0) return evaluateBoard(legalMoves, isWhite);
        int bestScore = isWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        //begin looping through those legal moves
        if (legalMoves.isEmpty()) {
            if (isWhite) {
                if (board.isWhiteInCheck()) return Integer.MIN_VALUE - depth;
            } else {
                if (board.isBlackInCheck()) return Integer.MAX_VALUE;
            }
            return 0;
        }
        for (Move move : legalMoves) {
            Pieces captured = board.makeMove(move);
            int score = minimax(!isWhite, depth - 1, alpha, beta);
            board.undoMove(move, captured);
            //alpha beta pruning. If the beta move is worse than the previous worst, it snips the branch off
            counter++;
            System.out.println("Count of searchers: " + counter );
            if (isWhite) {
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, bestScore);
            } else {
                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, bestScore);
            }
            if (alpha >= beta) break;
        }

        //Return worst / best eval for each position, works for both players
        return bestScore;
    }

    public ArrayList<Move> orderMoves(ArrayList<Move> unorderedMoves){
        for (Move move : unorderedMoves) {
            int moveScoreGuess = 0;
            Pieces movePieceType = move.movedPiece;
            Pieces capturedPieceType = move.capturedPiece;

            if(capturedPieceType != null){
                moveScoreGuess = 10 * capturedPieceType.getPieceValue() - movePieceType.getPieceValue();
            }
            if (movePieceType.getClass().getSimpleName().contains("Pawn") &&
                    (move.toRow == 0 || move.toRow == 7)) {
                    moveScoreGuess += 900;
            }
            Pieces tempCaptured = board.makeMove(move);
            if (board.isWhiteInCheck() || board.isBlackInCheck()) {
                moveScoreGuess += 50;
            }
            board.undoMove(move, tempCaptured);
            move.setHeuristicValue(moveScoreGuess);
        }
        unorderedMoves.sort(Comparator.comparingInt(Move::getHeuristicValue).reversed());
        return unorderedMoves;
    }
}
