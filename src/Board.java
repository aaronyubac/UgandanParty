import java.lang.reflect.Type;
import java.util.Random;

// Circular linked list
public class Board {
    Tile head;

    public Board() {
        Random random = new Random();

        // Initialize Events and store in an array
        Beginning beginning = new Beginning();
        Nothing nothing = new Nothing();
        Typer typer = new Typer();
        GuessThatNum guessThatNum = new GuessThatNum();
        WordJumble wordJumble = new WordJumble();

        Event[] events = {beginning, nothing, typer, guessThatNum, wordJumble};

        // create start tile
        this.head = new Tile(beginning);
        Tile current = this.head;

        // create 39 tiles
        for (int i = 0; i < 39; i++) {
            int randEventIndex = random.nextInt(1, events.length);
            Tile newTile = new Tile(events[randEventIndex]);

            current.next = newTile;
            current = current.next;
        }

        // link back to head;
        current.next = this.head;
    }
}
