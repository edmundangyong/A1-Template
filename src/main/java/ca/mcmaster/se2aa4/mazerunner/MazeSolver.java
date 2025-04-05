package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    Player player1 = new Player();
    
    Maze maze = Maze.getInstance();

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.player1.start(maze.getEntry());
    }

    public String findPath() {
        MazeSolverStrategy strategy = new RightHandStrategy(maze);
        return strategy.findPath();
    }
    
    public String checkPath(String path) {
        String result = "";
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) != ' ') {
                this.player1.input(path.charAt(i));
            }
            if (this.player1.getPosition()[1] < 0 || this.player1.getPosition()[1] > this.maze.getExit()[1]) {
                logger.error("/!\\ Out of bounds /!\\");
            } else if (this.maze.check(this.player1.getPosition()[0], this.player1.getPosition()[1]) == 1) {
                logger.warn("Collided with wall, ignoring last move");
                this.player1.input('R');
                this.player1.input('R');
                this.player1.input('F');
                this.player1.input('R');
                this.player1.input('R');
            }
        }
        if (this.player1.getPosition()[0] == this.maze.getExit()[0] && this.player1.getPosition()[1] == this.maze.getExit()[1]) {
            result = "correct path";
        } else {
            result = "incorrect path";
        }
        return result;
    }

}