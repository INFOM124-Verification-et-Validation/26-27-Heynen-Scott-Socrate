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
    // if it correctly react when getting strange case like the ghost being out of the map ... (sadly impossible to test)
    //Input domain : the position of ghosts, the map and the position of Pac-Man
    //Output domain : the move the ghost will do (UP,RIGHT,DOWN,LEFT,NOTHING)

    //2 Explore the program (if needed)
    //---------------------------------

    //Nothing to show

    //3 Identify input and output partitions
    //--------------------------------------

    //Input partition :
    // Clyde < 8 squares, = 8 squares, > 8 squares from PacMan
    // Clyde can't move (blocked by a ghost/wall),
    // Clyde is at the bound of the map

    //Output partition :
    // Moves as intended
    // Moves out of the map,
    // move toward Pac-Man while being within 8 square of its position, move away of Pac-Man while being more than 8 square of its position

    //4 Identify boundaries :
    //------------------------

    // The distance between Clyde and PacMan, < 8, = 8, > 8
    // Obstacles or not in the path
    // At the boundary of the map or not
    // Blocked or not


    /**
     * Asserts that Clyde correctly go on Pac-Man because he is at more than 8 square from him
     */
    @Test
    void testClydeGoOnPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#          C #");
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
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.NORTH, Direction.WEST);
    }

    /**
     * Asserts that Clyde correctly run away from Pac-Man because he is at less than 8 square from him
     */
    @Test
    void testClydeGoAwayFromPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P C        #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
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
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.NORTH,Direction.EAST,Direction.SOUTH);
    }

    /**
     * Asserts that Clyde correctly run away from Pac-Man because he is next to him
     */
    @Test
    void testClydeNextToPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# PC         #");
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
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.EAST,Direction.SOUTH,Direction.NORTH);
    }

    /**
     * Asserts that Clyde correctly run away from Pac-Man because he is at exactly 7 square from him
     */
    @Test
    void testClydeExactly7SquaresFromPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("# C          #");
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
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.EAST,Direction.SOUTH,Direction.WEST);
    }

    /**
     * Asserts that Clyde correctly run away from Pac-Man because he is at exactly 8 square from him
     */
    @Test
    void testClydeExactly8SquaresFromPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("# C          #");
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
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.EAST,Direction.SOUTH,Direction.WEST);
    }

    /**
     * Asserts that Clyde correctly attack Pac-Man because he is at exactly 9 square from him
     */
    @Test
    void testClydeExactly9SquaresFromPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("# C          #");
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
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isEqualTo(Direction.NORTH);
    }

    /**
     * Asserts that Clyde correctly avoid a wall of obstacles rather than going straight forward while attacking Pac-Man
     */
    @Test
    void testClydeAvoidObstaclesWhileAttacking() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("######       #");
        map.add("#    #       #");
        map.add("# C          #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isEqualTo(Direction.EAST);
    }

    /**
     * Asserts that Clyde correctly avoid a wall of obstacles rather than going straight forward while going away from Pac-Man
     */
    @Test
    void testClydeAvoidObstaclesWhileGoingAway() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("# P          #");
        map.add("#            #");
        map.add("# C          #");
        map.add("######       #");
        map.add("#    #       #");
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
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.EAST,Direction.WEST);
    }

    /**
     * Asserts that Clyde correctly stay still rather than moving (because he is blocked)
     */
    @Test
    void testClydeStayStillWhileGoingAway() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("# P          #");
        map.add("#            #");
        map.add("##           #");
        map.add("#C#          #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection).isEmpty();
    }

    /**
     * Asserts that Clyde correctly stay still rather than moving (because he is blocked)
     */
    @Test
    void testClydeStayStillWhileAttacking() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("##           #");
        map.add("#C#          #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection).isEmpty();
    }

    /**
     * Asserts that Clyde correctly stay still rather than moving (because he is blocked)
     */
    @Test
    void testClydeBoundOfMapLessThan8Squares() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#C           #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, currentLevel.getBoard());

        //act
        Optional<Direction> aiDirection = clyde.nextAiMove();

        //assert
        assertThat(aiDirection).isEmpty();
    }
}
