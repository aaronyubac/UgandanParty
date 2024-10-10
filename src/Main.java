import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int playerCount;
        int rounds;

        String icon = """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣤⣶⣶⣶⣶⣶⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⢠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀
                ⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆
                ⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣋⣩⣭⣭⣭⣉⡻⣿⣿⣿⣿⣿⣿
                ⠀⣠⣴⣭⣹⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⢣⣼⣿⣿⠛⠁⠘⠿⠿⢻⣿⣿⣿⣿⣿
                ⠀⠛⠛⠁⣿⣿⡯⣫⣤⣴⣶⣶⣤⣭⣛⡸⣿⣿⣇⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿
                ⠀⠀⢀⣴⣷⠬⣉⣀⣈⣹⣿⣿⣿⣿⣿⣷⣮⣝⣛⣯⣤⣤⣤⣤⣭⣛⠿⣿⣿⣿
                ⠀⠈⠉⣽⣶⣶⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⢛⡛⠛⠿⣿⣿⣷⡌⢻⣿
                ⣀⢰⣿⣦⡝⠛⢷⣮⡛⠻⣿⣿⣿⠿⢛⣫⣵⣶⣿⣿⣿⣿⣿⣿⣿⠿⠛⣣⣾⣿
                ⣿⢸⣿⡿⠀⣿⣶⣝⢿⣿⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⡿⠟⣩⣵⣶⠇⣿⣿⣿⢹
                ⣿⢸⣿⠀⡇⢹⣿⣿⡶⠎⣙⠿⠿⠿⠿⠿⢟⣛⣩⣴⣾⣿⣿⣿⡟⣸⣿⣿⠇⣸
                ⣿⡇⠛⢠⣿⡀⣿⣿⠀⠀⠀⠈⠛⠻⠿⠿⣿⣿⠿⠿⠛⠛⠛⠁⠀⣿⡿⠃⠀⣿
                ⣿⣿⣧⣿⣿⣷⡘⢿⡇⢸⣦⣤⣀⣀⡀⠀⠀⠀⠀⠀⣀⣀⣤⡄⠼⢋⣴⡇⠸⢋
                ⣿⣿⣿⣿⣿⣿⣷⣮⡃⣸⣿⣿⣿⣿⣿⣿⣿⣶⣾⣿⣿⣿⣿⣷⣶⣿⡿⢀⣠⣾
                
                ************************************
                            UGANDAN PARTY
                ************************************
                """;

        System.out.println(icon);

        System.out.println("How many players?: ");

        // Checks if a string is inputted
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid input...");
            scanner.next();
        }

        // if an int store the input
        int inputPlayerCount = scanner.nextInt();

        // prompt for input until it's greater than 0
        while (true) {
            if (inputPlayerCount <= 0) {
                System.out.println("Please enter a valid input...");
                inputPlayerCount = scanner.nextInt();
            } else {
                playerCount = inputPlayerCount;
                break;
            }
        }


        System.out.println("How many rounds?: ");

        // Checks if a string is inputted
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid input...");
            scanner.next();
        }

        // if an int store the input
        int inputRounds = scanner.nextInt();

        // prompt for input until it's greater than 0
        while (true) {
            if (inputRounds <= 0) {
                System.out.println("Please enter a valid input...");
                inputRounds = scanner.nextInt();
            } else {
                rounds = inputRounds;
                break;
            }
        }

        System.out.println();

        Game game = new Game(playerCount, rounds);
        game.start();





    }

}
