package nl.tudelft.jpacman.level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite to confirm that {@link Score} correctly stores and
 * accumulates points.
 */
class ScoreTest {

    /**
     * The score under test.
     */
    private Score score;

    /**
     * Resets the score under test.
     */
    @BeforeEach
    void setUp() {
        score = new Score(0);
    }

    /**
     * Asserts that a freshly created score starts at the given amount of
     * points.
     */
    @Test
    void testInitialPoints() {
        //arrange
        setUp();

        //assert
        assertEquals(0, score.getPoints());
    }

    /**
     * Asserts that adding points increases the total by that amount.
     */
    @Test
    void testAddPoints() {
        //arrange
        setUp();

        //act
        score.addPoints(1);

        //assert
        assert score.getPoints() == 1;
    }

    /**
     * Asserts that adding points twice accumulates both amounts.
     */
    @Test
    void testAddPointsTwice() {

        //arrange
        setUp();

        //act
        score.addPoints(1);
        score.addPoints(1);

        //assert
        assert score.getPoints() == 2;

    }

    /**
     * Asserts that adding negative points decreases the total by that amount.
     */
    @Test
    void testAddNegativePoints() {
        //arrange
        setUp();
        score.addPoints(1);

        //act
        score.addPoints(-1);

        //assert
        assert score.getPoints() == 0;
    }
}
