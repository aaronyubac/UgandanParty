import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;

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

        String gameName = "TYPER";

        System.out.println("-".repeat(100));
        System.out.println(" ".repeat(50 - gameName.length()) + gameName);
        System.out.println("-".repeat(100));


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
            Thread.sleep(2000);

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

            // give points if can't fetch from api
        }


    }

    public static String parseQuote(String responseBody) {

        JSONArray quotes = new JSONArray(responseBody);
        JSONObject quote = quotes.getJSONObject(0);


        return quote.getString("quote");



    }
}

class GuessThatNum implements Event {

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);


    @Override
    public void run(Player player) {

        String gameName = "GUESS THAT NUMBER";

        System.out.println("-".repeat(100));
        System.out.println(" ".repeat(50 - gameName.length()) + gameName);
        System.out.println("-".repeat(100));

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
                System.out.format("It was %d\n\n", target);
            }

        } catch (InputMismatchException e) {
            System.out.println("For trying to be funny, no knuckles for you");
        }



    }
}

class WordJumble implements Event {

    ArrayList<String> wordCache;


    public WordJumble() {
        wordCache = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("anagrams.txt");
            BufferedReader reader = new BufferedReader(fileReader);

            String word;
            while ((word = reader.readLine()) != null) {

                wordCache.add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(Player player) {

        String gameName = "WORD JUMBLE";

        System.out.println("-".repeat(100));
        System.out.println(" ".repeat(50 - gameName.length()) + gameName);
        System.out.println("-".repeat(100));


        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int timeLimit = 10;

        String givenWord = wordCache.get(random.nextInt(0, wordCache.size()));

        // Make a Hashmap for the given word to keep track of character frequency
        HashMap<Character, Integer> wordMap = new HashMap<>();

        for (int i = 0; i < givenWord.length(); i++) {
            char current = givenWord.charAt(i);

            wordMap.put(current, wordMap.getOrDefault(current, 0) + 1);
        }

        try {
            System.out.println("An anagram is a word or phrase that's formed by rearranging the letters of another word or phrase");
            Thread.sleep(4000);

            System.out.println("Example: gum and mug");
            Thread.sleep(3000);
            System.out.printf("Find an anagram for \"%s\"\n", givenWord);
            Thread.sleep(1000);
            System.out.printf("You have %d seconds\n", timeLimit);


            // timeout if past time limit
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < timeLimit * 1000
                    && !in.ready()) {
            }

            if (in.ready()) {

                String input = in.readLine();
                // check if valid anagram
                if (input.length() != givenWord.length()) {
                    // not an anagram
                    System.out.println("\n(͡ ° ͜ʖ ͡ °)");
                    System.out.println("\"Nice try\"\n");
                    return;
                }

                // Make a Hashmap for the input to keep track of character frequency

                HashMap<Character, Integer> inputMap = new HashMap<>();
                for (int i = 0; i < input.length(); i++) {
                    char current = input.charAt(i);

                    inputMap.put(current, inputMap.getOrDefault(current, 0) + 1);

                }

                if (inputMap.equals(wordMap)) {
                    // pass
                    System.out.println("Guau");
                    player.score += 3;
                } else {
                    // not an anagram
                    System.out.println("\n(͡ ° ͜ʖ ͡ °)");
                    System.out.println("\"Nice try\"\n");
                }


            } else {

                System.out.println("\nYou didn't even try");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}