public class Main {
    public static void main(String[] args) {
        String[][] winState0 = {{"E","S","C"},
                                {"S","S","C"},
                                {"C","C","S"}};
        Board board = new Board(winState0);
        board.printBoard();
        System.out.println(board.isWinningBoard());
    }
}