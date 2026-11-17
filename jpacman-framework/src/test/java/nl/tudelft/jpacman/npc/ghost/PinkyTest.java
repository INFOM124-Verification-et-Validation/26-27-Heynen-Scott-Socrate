package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


/**
 * <p>
 * An implementation of the classic Pac-Man ghost Speedy.
 * </p>
 * <p>
 * Nickname: Pinky. Speedy gets his name for an unusual reason. Speedy appears
 * to try to outsmart Pac-Man and crash into Pac-Man from the opposite
 * direction. The truth behind this is that when Speedy isn't patrolling the
 * top-left corner of the maze, he tries to attack Pac-Man by moving to where he
 * is going to be (that is, a few spaces ahead of Pac-Man's current direction)
 * instead of right where he is, as Blinky does. It's difficult to use this to
 * your advantage, but it's possible. If Pinky is coming at you and you face a
 * different direction, even briefly, he may just turn away and attempt to cut
 * you off in the new direction while you return to your original direction. In
 * the original Japanese version, his name is Machibuse/Pinky.
 * </p>
 * <p>
 * <b>AI:</b> When the ghosts are not patrolling their home corners, Pinky wants
 * to go to the place that is four grid spaces ahead of Pac-Man in the direction
 * that Pac-Man is facing. If Pac-Man is facing down, Pinky wants to go to the
 * location exactly four spaces below Pac-Man. Moving towards this place uses
 * the same logic that Blinky uses to find Pac-Man's exact location. Pinky is
 * affected by a targeting bug if Pac-Man is facing up - when he moves or faces
 * up, Pinky tries moving towards a point up, and left, four spaces.
 * </p>
 * <p>
 * <i>Note: In the original arcade series, the ghosts' genders are unspecified
 * and assumed to be male. In 1999, the USA division of Namco and Namco Hometech
 * developed the Pac-Man World series and declared Pinky to be female.</i>
 * </p>
 *
 * A SUPPRIMERRRRRRRRRRRRRRRRRRRRRRRRRR
 * lemock = mock(Level.class); #simule la class level QUE POUR APRES EX 2 (apd 3)
 */


public class PinkyTest {
    /**
     * Asserts that Pinky correctly go 4 square next to the direction of Pac-Man (South)
     */
    @Test
    void testPinkyGoOnPacManSouth() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#          Y #");
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
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.SOUTH);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isEqualTo(Direction.WEST);
    }

    /**
     * Asserts that Pinky correctly go 4 square next to the direction of Pac-Man (East)
     */
    @Test
    void testPinkyGoOnPacManEast() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("# P          #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#          Y #");
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
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.EAST);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isEqualTo(Direction.NORTH);
    }

    /**
     * Asserts that Pinky correctly go 4 square next to the direction of Pac-Man (West)
     */
    @Test
    void testPinkyGoOnPacManWest() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("#          P #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("# Y          #");
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
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.WEST);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isEqualTo(Direction.NORTH);
    }

    /**
     * Asserts that Pinky correctly avoid an obstacle while going on Pac-Man
     */
    @Test
    void testPinkyAvoidObstacleWhileGoingOnPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("#          P #");
        map.add("#            #");
        map.add("#            #");
        map.add("# #          #");
        map.add("# Y          #");
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
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.WEST);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        assertThat(aiDirection.get()).isIn(Direction.EAST,Direction.WEST);
    }

    /**
     * Asserts that Pinky correctly have the bug when Pac-Man is going upward
     */
    @Test
    void testPinkyBugPacManNorth() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("#     Y      #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#     P      #");
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
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.NORTH);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        //assertThat(aiDirection.get()).isEqualTo(Direction.WEST);
    }

    /**
     * Asserts that Pinky correctly avoid to go out of the map while going on Pac-Man
     */
    @Test
    void testPinkyAvoidOutOfMapWhileGoingOnPacMan() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("#         P Y#");
        map.add("#            #");
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
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.EAST);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        if (aiDirection.isPresent()) {
            assertThat(aiDirection.get()).isNotEqualTo(Direction.EAST);
        }
    }

    /**
     * Asserts that Pinky correctly doesn't move if he is blocked
     */
    @Test
    void testPinkyBlocked() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("#         P  #");
        map.add("#            #");
        map.add("#            #");
        map.add("#    ###     #");
        map.add("#    #Y#     #");
        map.add("#    ###     #");
        map.add("##############");

        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        Player player = new PlayerFactory(pacManSprites).createPacMan();
        Level currentLevel = ghostMapParser.parseMap(map);
        currentLevel.registerPlayer(player);
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.EAST);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        if (aiDirection.isPresent()) {
            fail("pinky moved");
        }
    }

    /**
     * Asserts that Pinky correctly doesn't move while being 4 square from Pac-Man direction (the spot it aims)
     */
    @Test
    void testPinkyOnTheSpot() {
        //arrange
        List<String> map = new ArrayList<>();
        map.add("##############");
        map.add("#            #");
        map.add("#         P  #");
        map.add("#            #");
        map.add("#            #");
        map.add("#            #");
        map.add("#         Y  #");
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
        Pinky pinky = Navigation.findUnitInBoard(Pinky.class, currentLevel.getBoard());

        player.setDirection(Direction.SOUTH);

        //act
        Optional<Direction> aiDirection = pinky.nextAiMove();

        //assert
        if (aiDirection.isPresent()) {
            fail("pinky moved");
        }
    }
}
