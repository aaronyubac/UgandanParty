import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Player {
    int playNo;
    Tile position;
    int score;

    public void roll() throws InterruptedException {

        System.out.println("rolling...");
        Thread.sleep(2000);
        Random rand = new Random();

        // possibilities when rolling two dice is [2,12]
        int min = 2;
        int max = 12;

        int rolled = rand.nextInt((max-min) + 1) + min;
        System.out.println("You rolled a " + rolled);
        Thread.sleep(1000);
        System.out.printf("Player %d is on the move\n", this.playNo);

        // traverse tiles "rolled" times
        for (int i = 0; i < rolled; i++) {
            position = position.next;

            // if player goes through beginning give points
            if (position.event instanceof Beginning) {
                position.event.run(this);
            }
        }

        // "player moving"
        Thread.sleep(3000);
        System.out.println();

        // Perform check because otherwise Beginning.Run() will execute twice if
        // final position is on Beginning
        if (!(position.event instanceof Beginning)) {
            position.event.run(this);
        }

        System.out.println();
        System.out.println();
        Thread.sleep(3000);


    }

    public void optionMenu() {

        String menu = """
                *********************************************************************************
                                                    OPTIONS
                1 - Roll
                *********************************************************************************
                """;


        System.out.printf("Player %d\n", playNo);
        System.out.println(menu);
        Scanner scanner = new Scanner(System.in);
        try {
            int input = scanner.nextInt();


            switch (input) {
                case 1:
                    this.roll();
                    break;
                default:
                    optionMenu();
            }
        } catch (InputMismatchException | InterruptedException e) {
            System.out.println("Enter a valid input");
            optionMenu();
        }
    }
}
