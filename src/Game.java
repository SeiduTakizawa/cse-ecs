public class Game {
    private Board board = new Board();
    private Player player = new Player();

    public void playGame() {
        boolean winState = false;
        int moves = 0;

        while (moves < 9) {
            board.print();
            if (moves % 2 == 0) {
                player.playerMove(board);
            } else {
                player.computerMove(board);
            }
            winState = board.isWinning();
            if (winState || board.isFull()) {
                break;
            }
            moves++;
        }

        board.print();
        if (winState) {
            String winner;
            if (moves % 2 == 0) {
                winner = "Player";
            } else {
                winner = "Computer";
            }
            System.out.println(winner + " wins!");

        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
