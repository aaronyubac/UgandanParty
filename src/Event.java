import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import static java.time.temporal.ChronoUnit.SECONDS;

public interface Event {

    void run(Player player);
}

class Beginning implements Event {

    public void run(Player player) {
        // give points
        System.out.println("player reached beginning");
        player.score += 3;
    }
}

class Nothing implements Event {

    public void run(Player player) {
        // do nothing
        System.out.println("nothing happened");
    }
}

class Typer implements Event {

    public void run (Player player) {


        try {
            // request random quote
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("https://api.api-ninjas.com/v1/quotes")).
                    headers("X-Api-Key", System.getenv("API_NINJAS_KEY"))
                    .timeout(Duration.of(5, SECONDS))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String quote = parseQuote(response.body());

            System.out.println("Type the quote before time runs out!!!");

            System.out.println(3);
            Thread.sleep(1000);
            System.out.println(2);
            Thread.sleep(1000);
            System.out.println(1);
            Thread.sleep(1000);

            System.out.println(quote);


            // timeout if past time limit
            int timeLimit = quote.length()/5;

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < timeLimit * 1000L
                    && !in.ready()) {
            }

            if (in.ready()) {
                if (in.readLine().equals(quote)) {
                    System.out.println("Yahoo +3 for you");
                    player.score+=3;
                } else {
                    System.out.println("Looks like you made a mistake\n\n");
                }
            } else {
                System.out.println("\nYou're too slow!!!'");
            }




        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String parseQuote(String responseBody) {

        JSONArray quotes = new JSONArray(responseBody);
        JSONObject quote = quotes.getJSONObject(0);


        return quote.getString("quote");



    }
}

class GuessNum implements Event {

    @Override
    public void run(Player player) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int min = 0;
        int max = 5;
        int target = random.nextInt(min, max+1);

        System.out.format("Guess a number between %d and %d\n", min, max);
        System.out.println("If you guess right, I will free that many Ugandan Knuckles");
        try {

            int input = scanner.nextInt();

            if (input == target) {
                System.out.println("A promise is a promise");
                player.score += target;
            } else {
                System.out.println("You didn't save them :(");
                System.out.format("It was %d", target);
            }

        } catch (InputMismatchException e) {
            System.out.println("For trying to be funny, no knuckles for you");
        }



    }
}