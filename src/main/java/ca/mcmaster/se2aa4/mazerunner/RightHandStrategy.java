package ca.mcmaster.se2aa4.mazerunner;

public class RightHandStrategy implements MazeSolverStrategy {
    Player player1 = new Player();
    Maze grid = new Maze();

    public RightHandStrategy(Maze grid) {
        this.grid = grid;
        this.player1.start(grid.getEntry());
    }

    public String findPath() {
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

}