import java.util.Random;

public class Player {
    Tile position;
    int score;

    public void roll() {
        Random rand = new Random();

        // possibilities when rolling two dice is [2,12]
        int min = 2;
        int max = 12;

        int rolled = rand.nextInt((max-min) + 1) + min;
        System.out.println(rolled);

        // traverse tiles "rolled" times
        for (int i = 0; i < rolled; i++) {
            position = position.next;

            // if player goes through beginning give points
            if (position.event instanceof Beginning) {
                position.event.run(this);
            }
        }

        // Perform check because otherwise Beginning.Run() will execute twice if
        // final position is on Beginning
        if (!(position.event instanceof Beginning)) {
            position.event.run(this);
        }



    }
}
