package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    Player player1 = new Player();
    Maze grid = new Maze();

    public MazeSolver(Maze grid) {
        this.grid = grid;
        this.player1.start(grid.getEntry());
    }

    public String solveRightHand() {
        String solution = "";
        while (this.player1.getPosition()[0] != this.grid.getExit()[0] || this.player1.getPosition()[1] != this.grid.getExit()[1]) {
            switch (this.player1.getDirection()) {
                case NORTH:
                    if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 1 && this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 0) {
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 1 && this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    }
                    break;
                case EAST:
                    if (this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 1 && this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 0) {
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 1 && this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else if (this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    }
                    break;
                case SOUTH:
                    if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 1 && this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 0) {
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 1 && this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    }
                    break;
                case WEST:
                    if (this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 1 && this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 0) {
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 1 && this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else if (this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    }
                    break;
            }
        // logger.info(this.player1.getPosition()[0] + " " + this.player1.getPosition()[1] + " " + this.player1.getDirection() + " " + this.grid.getExit()[0] + " " + this.grid.getExit()[1] + " " + solution);
        }
        return solution;
    }
    
    public String checkPath(String path) {
        String result = "";
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) != ' ') {
                this.player1.input(path.charAt(i));
            }
            if (this.player1.getPosition()[1] < 0 || this.player1.getPosition()[1] > this.grid.getExit()[1]) {
                logger.error("/!\\ Out of bounds /!\\");
            } else if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1]) == 1) {
                logger.warn("Collided with wall, ignoring last move");
                this.player1.input('R');
                this.player1.input('R');
                this.player1.input('F');
                this.player1.input('R');
                this.player1.input('R');
            }
        }
        if (this.player1.getPosition()[0] == this.grid.getExit()[0] && this.player1.getPosition()[1] == this.grid.getExit()[1]) {
            result = "correct path";
        } else {
            result = "incorrect path";
        }
        return result;
    }

}