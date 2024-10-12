import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void linksBackToBeginning() {

        Board board = new Board();

        Tile current = board.head;

        // traverse the board until back to beginning
        for (int i = 0; i < 40; i++) {
            current = current.next;
        }

        Class expected = Beginning.class;
        Class output = current.event.getClass();

        assertEquals(expected, output);
    }

}