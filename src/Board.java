public class Board {

    String [][] board;
    public Board(String[][] board){
        board[1][0] = "S";
        this.board = board;
    }
    public String[][] getBoard() {
        return board;
    }

    public void printBoard(){
        StringBuilder string_builder = new StringBuilder();
        for (int i =0; i<3; i++) {
            for(int j = 0; j<3;j++ ) {
                string_builder.append(board[i][j]);;
            }
            System.out.println(string_builder);
            string_builder.setLength(0);
        }
    }
    public String getWinner(int k,int i,int j){
        String[][] winState0 = {{"E","E","E"},{"S","S","S"},{"C","C","C"}};
        String[][] winState1 = {{"C","C","C"},{"S","S","S"},{"E","E","E"}};
        String[][] winState2 = {{"E","S","C"},{"E","S","C"},{"E","S","C"}};
        String[][] winState3 = {{"C","S","E"},{"C","S","E"},{"C","S","E"}};
        return switch (k) {
            case 1 -> winState1[i][j];
            case 2 -> winState2[i][j];
            case 3 -> winState3[i][j];
            default -> winState0[i][j];
        };
    }

    public boolean isWinner() {
        //check horizontally
        boolean winner = false;
        StringBuilder string_builder = new StringBuilder();
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(getWinner(k, i, j))) {
                        string_builder.append(board[i][j]);
                    }
                }
                if (string_builder.toString().equals("CSE") || string_builder.toString().equals("ESC")) {
                    winner = true;
                    string_builder.setLength(0);
                }
            }
        }
        return winner;
    }
}