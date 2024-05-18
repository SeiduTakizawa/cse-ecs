import java.util.Scanner;

public class Player {
    private Scanner scanner = new Scanner(System.in);

    public void playerMove(Board board) {
        int row, col;
        char choice;

        while (true) {
            System.out.println("Player 1, enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (board.isValidMove(row, col)) {
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

        board.makeMove(row, col, choice);
    }

    public void computerMove(Board board) {
        int[] bestMove = findBestMove(board);
        char moveChar;
        if (bestMove[2] == 0) {
            moveChar = 'E';
        } else if (bestMove[2] == 1) {
            moveChar = 'S';
        } else {
            moveChar = 'C';
        }
        board.makeMove(bestMove[0], bestMove[1], moveChar);
    }


    private int[] findBestMove(Board board) {
        int bestVal = Integer.MIN_VALUE; // Takes the lowest possible value in java instead of infinite
        int[] bestMove = {-1, -1, -1};
        char[][] testBoard = board.getBoard();

        for (int i = 0; i < 3; i++) { // Nested for loops to iterate over every cell of the board
            for (int j = 0; j < 3; j++) {
                if (testBoard[i][j] == ' ') { //check if empty to move
                    for (int k = 0; k < 3; k++) {
                        char choice;
                        if (k == 0) {  //Assigns the correct symbol based on the value of k
                            choice = 'E';
                        } else if (k == 1) {
                            choice = 'S';
                        } else {
                            choice = 'C';
                        }
                        testBoard[i][j] = choice; // Simulates a potential move
                        int moveVal = minimax(testBoard, 0, false); // Call the minmax algorithm
                        testBoard[i][j] = ' '; // Undo the move
                        if (moveVal > bestVal) { // If the move is optimal update bestMove
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

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        Board tempBoard = new Board();
        tempBoard.setBoard(board);

        if (tempBoard.isWinning()) {
            if (isMaximizing) {
                return -1;
            } else {
                return 1;
            }
        }
        if (tempBoard.isFull()) {
            return 0;
        }

        if (isMaximizing) { //Computer is the max Player
            int best = Integer.MIN_VALUE; // Takes the lowest possible value in java instead of infinite
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') { // Nested for loops to try every possible move and to check their validity
                        for (char choice : new char[] {'E', 'S', 'C'}) { // recursively calling minmax
                            board[i][j] = choice;
                            int newScore = minimax(board, depth + 1, false); // Calculate the score of the new move
                            if (newScore > best) {
                                best = newScore; // Update if the new score is better
                            }
                            board[i][j] = ' ';
                        }
                    }
                }
            }
            return best;
        } else { //Human is the min Player
            int best = Integer.MAX_VALUE; // Takes the highest possible value in java instead of infinite
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        for (char choice : new char[] {'E', 'S', 'C'}) {
                            board[i][j] = choice;
                            int newScore = minimax(board, depth + 1, true); // Calculate the score of the new move
                            if (newScore < best) {
                                best = newScore; // Update if the new score is better
                            }

                            board[i][j] = ' ';
                        }
                    }
                }
            }
            return best;
        }
    }
}
