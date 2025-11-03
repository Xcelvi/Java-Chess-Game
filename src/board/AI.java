package board;

import pieces.Pieces;
import java.util.ArrayList;
import java.util.Comparator;

public class AI {
    private BoardControl board;

    public AI(BoardControl board) {
        this.board = board;
    }

    int[][] whitePawnHeatMap = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {30, 30, 30, 30, 30, 30, 30, 30},
            {10, 10, 20, 25, 25, 20, 10, 10},
            {0, 0, 10, 20, 20, 10, 0, 0},
            {0, 0, 0, 10, 10, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    int[][] blackPawnHeatMap = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 10, 10, 0, 0, 0},
            {0, 0, 10, 20, 20, 10, 0, 0},
            {10, 10, 20, 25, 25, 20, 10, 10},
            {30, 30, 30, 30, 30, 30, 30, 30},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    int[][] whiteKnightPST = {

            {29, 30, 30, 30, 30, 30, 30, 29,},
            {30, 30, 30, 30, 30, 30, 30, 30,},
            {25, 30, 32, 32, 32, 32, 30, 25,},
            {30, 30, 32, 32, 32, 32, 30, 30,},
            {30, 30, 32, 32, 32, 32, 30, 30,},
            {30, 30, 32, 32, 32, 32, 30, 30,},
            {30, 30, 30, 30, 30, 30, 30, 30,},
            {29, 31, 30, 30, 30, 30, 31, 29}
};
    int[][]blackKnightPST = {

            {29, 31, 30, 30, 30, 30, 31, 29},
            {30, 30, 30, 30, 30, 30, 30, 30,},
            {30, 30, 32, 32, 32, 32, 30, 30,},
            {30, 30, 32, 32, 32, 32, 30, 30,},
            {30, 30, 32, 32, 32, 32, 30, 30,},
            {25, 30, 32, 32, 32, 32, 30, 25,},
            {30, 30, 30, 30, 30, 30, 30, 30,},
            {29, 30, 30, 30, 30, 30, 30, 29,},
    };
    int[][] whiteRookPST = {
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {52, 52, 52, 52, 52, 52, 52, 52,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 51, 51, 50, 50, 50,},
    };
    int[][] blackRookPST = {
            {50, 50, 50, 51, 51, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {52, 52, 52, 52, 52, 52, 52, 52,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
    };
    int[][] whiteBishopPST = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10,   0,   0,   0,   0,   0,   0, -10},
            {-10,   0,   5,  10,  10,   5,   0, -10},
            {-10,   5,   5,  10,  10,   5,   5, -10},
            {-10,   0,  10,  10,  10,  10,   0, -10},
            {-10,  10,  10,  10,  10,  10,  10, -10},
            {-10,   5,   0,   0,   0,   0,   5, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}
    };
    int[][] blackBishopPST = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10,   5,   0,   0,   0,   0,   5, -10},
            {-10,  10,  10,  10,  10,  10, 10, -10},
            {-10,   0,  10,  10,  10,  10,  0, -10},
            {-10,   5,   5,   10,  10,   5,   5, -10},
            {-10,   0,   5,  10,   10,   5,   0, -10},
            {-10,   0,   0,   0,   0,   0,   0, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}
    };
    int[][] blackQueenPST = {
            {-20, -10, -10,  -5,  -5, -10, -10, -20},
            {-10,   0,   0,   0,   0,   0,   0, -10},
            {-10,   0,   5,   5,   5,   5,   0, -10},
            { -5,   0,   5,   5,   5,   5,   0,  -5},
            {  0,   0,   5,   5,   5,   5,   0,  -5},
            {-10,   5,   5,   5,   5,   5,   0, -10},
            {-10,   0,   0,   0,   0,   0,   0, -10},
            {-20, -10, -10,  -5,  -5, -10, -10, -20}
    };
    int[][] whiteQueenPST = {
            {-20, -10, -10,  -5,  -5, -10, -10, -20},
            {-10,   0,   0,   0,   0,   0,   0, -10},
            {-10,   0,   5,   5,   5,   5,   0, -10},
            { -5,   0,   5,   5,   5,   5,   0,  -5},
            {  0,   0,   5,   5,   5,   5,   0,  -5},
            {-10,   5,   5,   5,   5,   5,   0, -10},
            {-10,   0,   5,   0,  0,   0,   0, -10},
            {-20, -10, -10,  -5,  -5, -10, -10, -20}
    };

    // Basic evaluation by material
    public double evaluateBoard(ArrayList<Move> legalMoves, boolean isWhite) {
        double score = 0;
        int pieceCount = 0;
        Pieces[][] boardArray = board.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Pieces piece = boardArray[row][col];
                if (piece == null) continue;
                pieceCount++;
                String pieceName = piece.getClass().getSimpleName();
                if (pieceName.contains("White")) {
                    score += piece.getPieceValue();
                    if (pieceName.contains("Pawn")){
                        score += whitePawnHeatMap[row][col];
                    }
                    if (pieceName.contains("Knight")){
                        score += whiteKnightPST[row][col];
                    }
                    if (pieceName.contains("Rook")){
                        score += whiteRookPST[row][col];
                    }
                    if (pieceName.contains("Bishop")){
                        score += whiteBishopPST[row][col];
                    }
                    if (pieceName.contains("Queen")){
                        score += whiteQueenPST[row][col];
                    }
                    if (pieceCount < 8){
                        if (pieceName.contains("King")) {
                            if (row >= 2 && row <= 6 &&
                            col >= 2 && col <= 6) {
                                score += 10;
                            }
                        }
                    }
                } else if (pieceName.contains("Black")) {
                    score -= piece.getPieceValue();
                    if (pieceName.contains("Pawn")){
                        score -= blackPawnHeatMap[row][col];
                    }
                    if (pieceName.contains("Knight")){
                        score -= blackKnightPST[row][col];
                    }
                    if (pieceName.contains("Rook")){
                        score -= blackRookPST[row][col];
                    }
                    if (pieceName.contains("Bishop")){
                        score -= blackBishopPST[row][col];
                    }
                    if (pieceName.contains("Queen")){
                        score -= blackQueenPST[row][col];
                    }
                    if  (pieceCount < 8){
                        if (pieceName.contains("King")) {
                            if (row >= 2 && row <= 6 &&
                                    col >= 2 && col <= 6) {
                                score -= 10;
                            }
                        }
                    }
                }
            }
        }
        return score;
    }
    //Find best move based on eval function, set if you are white, and the depth you want the ai to go
    public Move findBestMove(boolean isWhite, int depth) {
        //Generate the full list of legal moves
        double bestScore = isWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;
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
            double score;
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
    private double minimax(boolean isWhite, int depth, double alpha, double beta) {
        //generate legal moves, initialize score to low/high
        ArrayList<Move> legalMoves = board.generateAllLegalMoves(board);
        legalMoves = orderMoves(legalMoves, isWhite);
        if (depth == 0) return evaluateBoard(legalMoves, isWhite);
        double bestScore = isWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;
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
            double score = minimax(!isWhite, depth - 1, alpha, beta);
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

    public ArrayList<Move> orderMoves(ArrayList<Move> unorderedMoves, boolean isWhite){
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
            if (isWhite) {
                if (board.isBlackInCheck()) moveScoreGuess += 50;
                if (board.isWhiteInCheck()) moveScoreGuess -= 80;
            } else {
                if (board.isWhiteInCheck()) moveScoreGuess += 50;
                if (board.isBlackInCheck()) moveScoreGuess -= 80;
            }
            if (move.toCol >= 3 && move.toCol <= 5 && move.toRow >= 3 && move.toRow <= 5) moveScoreGuess += 100;

            board.undoMove(move, tempCaptured);
            move.setHeuristicValue(moveScoreGuess);
        }
        unorderedMoves.sort(Comparator.comparingInt(Move::getHeuristicValue).reversed());
        return unorderedMoves;
    }
}
