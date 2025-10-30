package board;

import pieces.Pieces;
import java.util.ArrayList;

public class AI {
    private BoardControl board;

    public AI(BoardControl board) {
        this.board = board;
    }

    // Basic evaluation by material
    public int evaluateBoard() {
        int score = 0;
        Pieces[][] boardArray = board.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Pieces piece = boardArray[row][col];
                if (piece == null) continue;

                switch (piece.getClass().getSimpleName()) {
                    case "WhitePawn": score += 10; break;
                    case "WhiteKnight": score += 30; break;
                    case "WhiteBishop": score += 33; break;
                    case "WhiteRook": score += 50; break;
                    case "WhiteQueen": score += 90; break;
                    case "WhiteKing": score += 900; break;
                    case "BlackPawn": score -= 10; break;
                    case "BlackKnight": score -= 30; break;
                    case "BlackBishop": score -= 33; break;
                    case "BlackRook": score -= 50; break;
                    case "BlackQueen": score -= 90; break;
                    case "BlackKing": score -= 900; break;
                }
            }
        }
        return score;
    }
    //Find best move based on eval function, set if you are white, and the depth you want the ai to go
    public Move findBestMove(boolean isWhite, int depth) {
        //Generate the full list of legal moves
        ArrayList<Move> legalMoves = board.generateAllLegalMoves(board);
        int bestScore = isWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        if (isWhite && legalMoves.isEmpty()) {
            System.out.println("Game Over");
            bestScore = Integer.MAX_VALUE;
        } else if(!isWhite && legalMoves.isEmpty()) {
            System.out.println("Game Over");
            bestScore = Integer.MIN_VALUE;
        }
        Move bestMove = null;
        //Loop through legal moves
        for (Move move : legalMoves) {
            // make move and store caputred piece
            Pieces captured = board.makeMove(move);
            int score;
            if (depth > 1) {
                // Use minimax to generate the score of the position after that move
                AI nextAI = new AI(board);
                score = nextAI.minimax(!isWhite, depth - 1);
            } else {
                //Reached base case for depth, return the score.
                score = evaluateBoard();
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
    private int minimax(boolean isWhite, int depth) {
        //generate legal moves, initialize score to low/high
        if (depth == 0) return evaluateBoard();
        ArrayList<Move> legalMoves = board.generateAllLegalMoves(board);
        int bestScore = isWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        //begin looping through those legal moves
        for (Move move : legalMoves) {
            Pieces captured = board.makeMove(move);
            int score = minimax(!isWhite, depth - 1);
            board.undoMove(move, captured);
            if (isWhite) bestScore = Math.max(bestScore, score);
            else bestScore = Math.min(bestScore, score);
        }
        return bestScore;
    }
}
