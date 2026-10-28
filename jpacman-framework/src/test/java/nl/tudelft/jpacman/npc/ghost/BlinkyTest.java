package nl.tudelft.jpacman.npc.ghost;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



/**
 * <p>
 * An implementation of the classic Pac-Man ghost Shadow.
 * </p>
 * <p>
 * Nickname: Blinky. As his name implies, Shadow is usually a constant shadow on
 * Pac-Man's tail. When he's not patrolling the top-right corner of the maze,
 * Shadow tries to find the quickest route to Pac-Man's position. Despite the
 * fact that Blinky's real name is Speedy, Shadow is actually the fastest of the
 * ghosts because of when there are only a few pellets left, Blinky drastically
 * speeds up, which can make him quite deadly. In the original Japanese version,
 * his name is Oikake/Akabei.
 * </p>
 * <p>
 * <b>AI:</b> When the ghosts are not patrolling in their home corners (Blinky:
 * top-right, Blinky: top-left, Inky: bottom-right, Clyde: bottom-left), Blinky
 * will attempt to shorten the distance between Pac-Man and himself. If he has
 * to choose between shortening the horizontal or vertical distance, he will
 * choose to shorten whichever is greatest. For example, if Pac-Man is four grid
 * spaces to the left, and seven grid spaces above Blinky, he'll try to move up
 * towards Pac-Man before he moves to the left.
 */

public class BlinkyTest {
    //1 Goal, inputs and outputs
    //---------------------------

    //Goal : testing if the NPC do what it should (always chasing Pac-Man with the shortest path)
    //Input domain : the position of ghosts, the map and the position of Pac-Man
    //Output domain : the move the ghost will do (UP,RIGHT,DOWN,LEFT,NOTHING)

    //3 Identify input and output partitions
    //--------------------------------------

    //Input partition :
    // (No) obstacles on the path
    // Blinky can't move (blocked by a ghost/wall),
    // Blinky is at the bound of the map
    // 2 path are the bests equally
    // The best path is (not) a straight line

    //Output partition :
    // Moves as intended
    // Moves out of the map,
    // Don't do the best move

    //4 Identify boundaries :
    //------------------------

    //Same as 3


    /**
     * Asserts that Blinky correctly go on Pac-Man on a straight line
     */
    @Test
    void testBlinkyStraightLine() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("# S          #");
        map.add("#            #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Blinky Blinky = Navigation.findUnitInBoard(Blinky.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = Blinky.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isEqualTo(Direction.NORTH);
    }

    /**
     * Asserts that Blinky correctly go on Pac-Man on a perfect diagonal line
     */
    @Test
    void testBlinkyPerfectDiagonalLine() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#    S       #");
        map.add("#            #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Blinky Blinky = Navigation.findUnitInBoard(Blinky.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = Blinky.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.NORTH,Direction.WEST);
    }

    /**
     * Asserts that Blinky correctly shorten the distance with Pac-Man on a not perfect diagonal line
     */
    @Test
    void testBlinkyShortenDistanceDiagonalLine() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#   S        #");
        map.add("#            #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Blinky Blinky = Navigation.findUnitInBoard(Blinky.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = Blinky.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isEqualTo(Direction.NORTH);
    }


}
