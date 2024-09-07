package ttn.developer.tictactoe;

public class IA {
    public int minimax(int[] board, int depth, boolean isMaximizing) {
        int score = evaluate(board);
        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (!isMovesLeft(board)) return 0;

        int best;
        if (isMaximizing) {
            best = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) {
                    board[i] = 1;
                    best = Math.max(best, minimax(board, depth + 1, false));
                    board[i] = 0;
                }
            }
        } else {
            best = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) {
                    board[i] = -1;
                    best = Math.min(best, minimax(board, depth + 1, true));
                    board[i] = 0;
                }
            }
        }
        return best;
    }

    public int findBestMove(int[] board) {
        int bestVal = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                board[i] = 1;
                int moveVal = minimax(board, 0, true); // Change to TRUE.
                board[i] = 0;
                if (moveVal > bestVal) {
                    bestMove = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }

    public boolean isMovesLeft(int[] board) {
        for (int j : board) {
            if (j == 0) return true;
        }
        return false;
    }

    public int evaluate(int[] board) {
        // Evaluar filas
        for (int row = 0; row < 3; row++) {
            if (board[row * 3] == board[row * 3 + 1] && board[row * 3 + 1] == board[row * 3 + 2]) {
                if (board[row * 3] == 1) return 10;
                else if (board[row * 3] == -1) return -10;
            }
        }
        // Evaluar columnas
        for (int col = 0; col < 3; col++) {
            if (board[col] == board[col + 3] && board[col + 3] == board[col + 6]) {
                if (board[col] == 1) return 10;
                else if (board[col] == -1) return -10;
            }
        }
        // Evaluar diagonales
        if (board[0] == board[4] && board[4] == board[8]) {
            if (board[0] == 1) return 10;
            else if (board[0] == -1) return -10;
        }
        if (board[2] == board[4] && board[4] == board[6]) {
            if (board[2] == 1) return 10;
            else if (board[2] == -1) return -10;
        }
        return 0;
    }
}

