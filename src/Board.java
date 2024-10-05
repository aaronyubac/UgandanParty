// Circular linked list
public class Board {
    Tile head;

    public Board() {
        // create start tile
        this.head = new Tile(new Beginning());
        Tile current = this.head;

        // create 39 tiles
        for (int i = 0; i < 39; i++) {
            Tile newTile = new Tile(new Nothing());

            current.next = newTile;
            current = current.next;
        }

        // link back to head;
        current.next = this.head;
    }
}
