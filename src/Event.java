import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
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

        Scanner scanner = new Scanner(System.in);
        // request random quote
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("https://api.api-ninjas.com/v1/quotes")).
                    headers("X-Api-Key", System.getenv("API_NINJAS_KEY"))
                    .timeout(Duration.of(5, SECONDS))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String quote = parseQuote(response.body());

            // create timer
            // configure timer

            System.out.println("Type the quote before time runs out!!!");

            System.out.println(3);
            Thread.sleep(1000);
            System.out.println(2);
            Thread.sleep(1000);
            System.out.println(1);
            Thread.sleep(1000);

            System.out.println(quote);
            String input = scanner.nextLine();

            if (input.equals(quote)) {
                System.out.println("yahoo");
            } else {
                System.out.println("you suck");
            }

            // or submits when timer is over

            // compare submission with str
            // if 80% correct 1 point
            // if 100% correct 3 points


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

