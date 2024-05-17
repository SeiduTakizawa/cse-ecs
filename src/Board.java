public class Board {

    String [][] board;

    public Board(String[][] board){
        board[1][0] = "S";
        this.board = board;
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isWinningBoard() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder col = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                row.append(board[i][j]);
                col.append(board[j][i]);
            }
            if (isWinner(row.toString()) || isWinner(col.toString())) {
                return true;
            }
        }
        // Check diagonals
        StringBuilder diagonal1 = new StringBuilder();
        StringBuilder diagonal2 = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            diagonal1.append(board[i][i]);
            diagonal2.append(board[i][2 - i]);
        }
        return isWinner(diagonal1.toString()) || isWinner(diagonal2.toString());
    }

    private boolean isWinner(String line) {
        String[] win = {"ESC", "CSE"};
        for (String combo : win) {
            if (line.contains(combo)) {
                return true;
            }
        }
        return false;
    }

    public String[][] getBoard() {
        return board;
    }

}