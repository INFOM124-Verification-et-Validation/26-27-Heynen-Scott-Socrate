package nl.tudelft.jpacman.board;


import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        //arrange
        setUp();

        //assert
        assertFalse(unit.hasSquare());
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        // arrange
        setUp();
        Square test_square = new BasicSquare();

        //act
        unit.occupy(test_square);

        //assert
        assertEquals(test_square, unit.getSquare());
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        // arrange
        setUp();
        Square test_square = new BasicSquare();

        //act
        unit.occupy(test_square);
        unit.occupy(test_square);

        //assert
        assertEquals(test_square, unit.getSquare());
    }
}
