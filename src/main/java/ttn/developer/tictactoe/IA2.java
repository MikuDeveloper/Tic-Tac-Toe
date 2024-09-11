package ttn.developer.tictactoe;

public class IA2 {
    int[][] vs = {
        {1,2,3},
        {4,5,6},
        {7,8,9},
        {1,4,7},
        {2,5,8},
        {3,6,9},
        {1,5,9},
        {3,5,7}
    };

    int [] board;

    public IA2 (int [] board) {
        this.board = board;
    }

    public int best() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = -1;
                int score = minimax(false);
                board[i] = 0;
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    public int minimax(boolean isMaximizing) {
        String result = checkWinner();
        if (result != null) {
            if (result.equals("-1")) {
                return 1;
            } else if (result.equals("1")) {
                return -1;
            } else {
                return 0;
            }
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == 0) {
                    board[i] = -1;
                    int score = minimax(false);
                    board[i] = 0;
                    bestScore = Math.max(score, bestScore);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == 0) {
                    board[i] = 1;
                    int score = minimax(true);
                    board[i] = 0;
                    bestScore = Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

    public String checkWinner() {
        for (int[] v : vs) {
            if (board[v[0] - 1] == board[v[1] - 1] &&
                    board[v[1] - 1] == board[v[2] - 1] &&
                    board[v[0] - 1] != 0) {
                return Integer.toString(board[v[0] - 1]);
            }
        }
        for (int pos : board) {
            if (pos == 0) {
                return null;
            }
        }
        return "Empate";
    }
}
