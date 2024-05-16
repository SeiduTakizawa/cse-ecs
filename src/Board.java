public class Board {

    LETTERS [][] board;
    public Board(LETTERS[][] board){
        board[1][0] = LETTERS.S;
        this.board = board;
    }
    public LETTERS[][] getBoard() {
        return board;
    }
    public void printBoard(){
        for (int i =0; i<2; i++) {
            for(int j = 0; j<2;j++ ) {
                System.out.println(board[i][j]);;
            }
        }
    }
    public boolean isWinner(){
        //check horizontally
        LETTERS[] winState1 = "C"a
        LETTERS [] result;
        for (int i =0; i<2; i++) {
            for(int j = 0; j<2;j++ ) {
                if(board[i][j] == )
            }
        }
        if(result.equals())
    }

}