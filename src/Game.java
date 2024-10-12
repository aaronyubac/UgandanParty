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
            newPlayer.playNo = i+1;
            newPlayer.position = board.head;

            players[i] = newPlayer;
        }
    }

    public void start() throws InterruptedException {

        for (int i = 1; i <= rounds; i++) {

            for (Player player : players) {

                // display scoreboard
                for(Player current : players) {
                    System.out.format("%s%10d\n", "Player " + current.playNo, current.score);
                }


                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Option menu
                System.out.println("-".repeat(150));

                System.out.printf("Round %d/%d\n", i, rounds);
                player.optionMenu();


            }


        }

        Thread.sleep(2000);

        System.out.println("GAME OVER");
        System.out.println("And the winner is..");
        Thread.sleep(3000);

        // set winner
        Player highestScorer = players[0];
        for (int i = 0; i < players.length; i++) {
            if (players[i].score > highestScorer.score) {
                highestScorer = players[i];
            }
        }

        winner = highestScorer;


        System.out.println(winner + "!!!");

    }

}

