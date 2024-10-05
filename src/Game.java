import java.util.Arrays;

public class Game {

    private final Board board;
    private final Player[] players;
    private final int rounds;

    public Game(int playerCount, int rounds) {
        this.board = new Board();
        this.players = new Player[playerCount];
        this.rounds = rounds;

        // Initialize players starting at beginning
        for (int i = 0; i < playerCount; i++) {
            Player newPlayer = new Player();
            newPlayer.position = board.head;

            players[i] = newPlayer;
        }


    }

    public void start() {
        for (int i = 1; i <= rounds; i++) {

            for (Player player : players) {
                player.roll();
            }

            // set winner

        }

    }
}

