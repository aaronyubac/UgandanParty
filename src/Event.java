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
                    System.out.println("yahoo");
                    player.score+=3;
                } else {
                    System.out.println("Looks like you made a mistake\n\n");
                }
            } else {
                System.out.println("\nYou're too slow!!!'");
            }

            Thread.sleep(5000);




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

