public class Main {
    public static void main(String[] args) {
        String[][] winState0 = {{"E","E","E"},{"S","S","S"},{"C","C","C"}};
        Board board = new Board(winState0);
        board.printBoard();
        System.out.println(board.isWinner());
    }
}