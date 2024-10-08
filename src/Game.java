import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Game {

    private final Board board;
    private final Player[] players;
    private final int rounds;
    private Player winner;

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

        Tile tile = new Tile(new GuessNum());
        tile.event.run(players[0]);

    }

    public void start() {

        for (int i = 1; i <= rounds; i++) {

            for (Player player : players) {
                player.roll();
            }

        }

        // set winner
        Player highestScorer = players[0];
        for (int i = 0; i < players.length; i++) {
            if (players[i].score > highestScorer.score) {
                highestScorer = players[i];
            }
        }

        winner = highestScorer;
        System.out.println(winner);

    }

}

