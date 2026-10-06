package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.ui.PacManUiBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A very simple (and not particularly useful)
 * test class to have a starting point where to put tests.
 *
 * @author Arie van Deursen
 */
class DirectionTest {

    /**
     * Do we get the correct delta when moving north?
     */
    @Test
    void testNorth() {
        //arrange
        Direction direction = Direction.NORTH;

        //assert
        assertEquals(0,direction.getDeltaX());
        assertEquals(-1, direction.getDeltaY());
    }

    @Test
    void testSouth() {
        //arrange
        Direction direction = Direction.SOUTH;

        //assert
        assertEquals(0,direction.getDeltaX());
        assertEquals(1, direction.getDeltaY());
    }

    @Test
    void testEast() {
        //arrange
        Direction direction = Direction.EAST;

        //assert
        assertEquals(1,direction.getDeltaX());
        assertEquals(0, direction.getDeltaY());
    }

    @Test
    void testWest() {
        //arrange
        Direction direction = Direction.WEST;

        //assert
        assertEquals(-1,direction.getDeltaX());
        assertEquals(0, direction.getDeltaY());
    }
}
