public class Board {
    private static final int size = 3;
    private static final String[] WINNING_COMBINATIONS = {"ESC", "CSE"};
    private char[][] board = new char[size][size];

    public Board() {
        // Initialize the board with empty spaces and place 'S' at position [1,0]
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        board[1][0] = 'S';
    }
    public int getSize(){
        return size;
    }
    public char[][] getBoard() {
        return board;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinning() {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder col = new StringBuilder();
            for (int j = 0; j < size; j++) {
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
        for (int i = 0; i < size; i++) {
            diagonal1.append(board[i][i]);
            diagonal2.append(board[i][size - 1 - i]);
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

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
                if (j < size - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i < size - 1) {
                System.out.println("---------");
            }
        }
        System.out.println("=========");
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && board[row][col] == ' ';
    }

    public void makeMove(int row, int col, char choice) {
        board[row][col] = choice;
    }

    public void setBoard(char[][] board) {
        this.board = board.clone();
    }
}
