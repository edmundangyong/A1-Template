package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {

    @Test
    public void entryTest() {
        MazeReader reader = new MazeReader("./examples/small.maz.txt");

        assertTrue(reader.getMaze().getEntry()[0] == 8 && reader.getMaze().getEntry()[1] == 0);
    }

    @Test
    public void exitTest() {
        MazeReader reader = new MazeReader("./examples/tiny.maz.txt");
        
        assertTrue(reader.getMaze().getExit()[0] == 1 && reader.getMaze().getExit()[1] == 6);
    }

    @Test
    public void factorizeTest() {
        String canonical = " FR FFFR   LFLL   R ";
        assertTrue(Factorizer.factorize(canonical).equals("F R 3F R L F 2L R"));
    }

    @Test
    public void directionTest() {
        Direction direction = Direction.NORTH;
        assertTrue(direction.left().equals(Direction.WEST) && direction.right().equals(Direction.EAST));
    }

    @Test
    public void startTest() {
        Player player = new Player();
        MazeReader reader = new MazeReader("./examples/medium.maz.txt");
        player.start(reader.getMaze().getEntry());

        assertTrue(player.getPosition()[0] == 23 && player.getPosition()[1] == 0 && player.getDirection().equals(Direction.EAST));
    }

    @Test
    public void inputTest() {
        Player player = new Player();
        MazeReader reader = new MazeReader("./examples/tiny.maz.txt");
        player.start(reader.getMaze().getEntry());

        player.input('F');
        player.input('F');
        player.input('F');
        player.input('F');
        player.input('L');
        player.input('F');
        player.input('F');
        player.input('R');
        player.input('F');
        player.input('L');

        assertTrue(player.getPosition()[0] == 3 && player.getPosition()[1] == 5 && player.getDirection().equals(Direction.NORTH));
    }

    @Test
    public void correctPathTest() {
        MazeReader reader = new MazeReader("./examples/straight.maz.txt");
        MazeSolver solver = new MazeSolver(reader.getMaze());
        String path = "FFFF";

        assertTrue(solver.checkPath(path).equals("correct path"));
    }

    @Test
    public void collidingPathTest() {
        MazeReader reader = new MazeReader("./examples/straight.maz.txt");
        MazeSolver solver = new MazeSolver(reader.getMaze());
        String path = "FRFFLFLLFRRFRFLFF";

        assertTrue(solver.checkPath(path).equals("correct path"));
    }

    @Test
    public void incorrectPathTest() {
        MazeReader reader = new MazeReader("./examples/straight.maz.txt");
        MazeSolver solver = new MazeSolver(reader.getMaze());
        String path = "FF";

        assertTrue(solver.checkPath(path).equals("incorrect path"));
    }

    @Test
    public void singletonTest() {
        Maze maze1 = Maze.getInstance();
        Maze maze2 = Maze.getInstance();

        assertTrue(maze1 == maze2);
    }
}