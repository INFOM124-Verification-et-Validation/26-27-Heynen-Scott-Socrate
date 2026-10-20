package nl.tudelft.jpacman.npc.ghost;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;



/**
 * {@inheritDoc}
 *
 * <p>
 * Clyde has two basic AIs, one for when he's far from Pac-Man, and one for
 * when he is near to Pac-Man.
 * When Clyde is far away from Pac-Man (beyond eight grid spaces),
 * Clyde behaves very much like Blinky, trying to move to Pac-Man's exact
 * location. However, when Clyde gets within eight grid spaces of Pac-Man,
 * he automatically changes his behavior and runs away
 * </p>
 */

public class ClydeTest {
    //1 Goal, inputs and outputs
    //---------------------------

    //Goal : testing if the NPC do what it should (changing of state when within 8 grid spaces of Pac-Man,
    // if it correctly react when getting strange case like the ghost being out of the map ...)
    //Input domain : the position of ghosts, the map and the position of Pac-Man
    //Output domain : the move the ghost will do (UP,RIGHT,DOWN,LEFT,NOTHING)

    //3 Identify input and output partitions
    //--------------------------------------

    //Input partition : < 8 squares, = 8 squares, > 8 squares
    // Pac-Man/Clyde out of the map, in the map.
    // Clyde can't move (blocked by a ghost/wall), bound of the map

    //Output partition :
    // 



}
