import java.util.Scanner;

public class TicTacToeGame {
    private static final String[] WINNING_COMBINATIONS = {"ESC", "CSE"};
    private static final int SIZE = 3;
    private char[][] board = new char[SIZE][SIZE];
    private Scanner scanner = new Scanner(System.in);

    public TicTacToeGame() {
        // Initialize the board with empty spaces and place 'S' at position [1,0]
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
        board[1][0] = 'S';
    }

    public void playGame() {
        boolean isGameWon = false;
        int moves = 0;

        while (moves < SIZE * SIZE && !isGameWon) {
            printBoard();
            if (moves % 2 == 0) {
                playerMove();
            } else {
                computerMove();
            }
            isGameWon = isWinningBoard(board);
            if (isGameWon || isBoardFull()) {
                break;
            }
            moves++;
        }

        printBoard();
        if (isGameWon) {
            System.out.println("Player " + (moves % 2 == 0 ? '2' : '1') + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void playerMove() {
        int row, col;
        char choice;

        while (true) {
            System.out.println("Player 1, enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ') {
                break;
            } else {
                System.out.println("This move is not valid.");
            }
        }

        while (true) {
            System.out.println("Player 1, choose 'E', 'S', or 'C': ");
            choice = scanner.next().charAt(0);
            if (choice == 'E' || choice == 'S' || choice == 'C') {
                break;
            } else {
                System.out.println("Invalid choice. Choose 'E', 'S', or 'C'.");
            }
        }

        board[row][col] = choice;
    }

    private void computerMove() {
        int[] bestMove = findBestMove();
        board[bestMove[0]][bestMove[1]] = bestMove[2] == 0 ? 'E' : bestMove[2] == 1 ? 'S' : 'C';
    }

    private int[] findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1, -1};

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    for (int k = 0; k < 3; k++) {
                        char choice = k == 0 ? 'E' : k == 1 ? 'S' : 'C';
                        board[i][j] = choice;
                        int moveVal = minimax(0, false);
                        board[i][j] = ' ';
                        if (moveVal > bestVal) {
                            bestMove[0] = i;
                            bestMove[1] = j;
                            bestMove[2] = k;
                            bestVal = moveVal;
                        }
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(int depth, boolean isMaximizing) {
        if (isWinningBoard(board)) {
            return isMaximizing ? -1 : 1;
        }
        if (isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == ' ') {
                        for (char choice : new char[] {'E', 'S', 'C'}) {
                            board[i][j] = choice;
                            best = Math.max(best, minimax(depth + 1, false));
                            board[i][j] = ' ';
                        }
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == ' ') {
                        for (char choice : new char[] {'E', 'S', 'C'}) {
                            board[i][j] = choice;
                            best = Math.min(best, minimax(depth + 1, true));
                            board[i][j] = ' ';
                        }
                    }
                }
            }
            return best;
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinningBoard(char[][] board) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder col = new StringBuilder();
            for (int j = 0; j < SIZE; j++) {
                row.append(board[i][j]);
                col.append(board[j][i]);
            }
            if (isWinningCombination(row.toString()) || isWinningCombination(col.toString())) {
                return true;
            }
        }

        // Check diagonals
        StringBuilder diagonal1 = new StringBuilder();
        StringBuilder diagonal2 = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            diagonal1.append(board[i][i]);
            diagonal2.append(board[i][SIZE - 1 - i]);
        }
        return isWinningCombination(diagonal1.toString()) || isWinningCombination(diagonal2.toString());
    }

    private boolean isWinningCombination(String line) {
        for (String combo : WINNING_COMBINATIONS) {
            if (line.contains(combo)) {
                return true;
            }
        }
        return false;
    }

    private void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
                if (j < SIZE - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("---------");
            }

        }
        System.out.println("=========");
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
