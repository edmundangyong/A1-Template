package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "path to input maze file");
        options.addOption("p", true, "maze path to check");
        CommandLineParser parser = new DefaultParser();

        String file_path = "";
        String maze_path = "";

        try {
            CommandLine cmd = parser.parse(options, args);
            if (!cmd.hasOption("i")) {
                logger.error("/!\\ An error has occured /!\\");
            }
            file_path = cmd.getOptionValue("i");

            if (cmd.hasOption("p")) {
                maze_path = cmd.getOptionValue("p");
            }
            
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        MazeReader reader = new MazeReader(file_path);
        MazeSolver solver = new MazeSolver(reader.getMaze());

        logger.trace("**** Computing path");

        if (maze_path != "") {
            solver.check(maze_path);
        } else {
            System.out.println(solver.solveRightHand());
        }

        logger.info("** End of MazeRunner");
    }
}

class Player {
    private static final Logger logger = LogManager.getLogger();
    private int[] position = new int[2];
    private char direction;

    public void start(int[] position) {
        this.position = position;
        if (position[1] == 0) {
            this.direction = 'E';
        } else {
            this.direction = 'W';
        }
    }

    void input(char c) {
        switch (c) {
            case 'F':
                switch (this.direction) {
                    case 'N':
                        this.position[0] -= 1;
                        break;
                    case 'E':
                        this.position[1] += 1;
                        break;
                    case 'S':
                        this.position[0] += 1;
                        break;
                    case 'W':
                        this.position[1] -= 1;
                        break;
                }
                break;
            case 'R':
                switch (this.direction) {
                    case 'N':
                        this.direction = 'E';
                        break;
                    case 'E':
                        this.direction = 'S';
                        break;
                    case 'S':
                        this.direction = 'W';
                        break;
                    case 'W':
                        this.direction = 'N';
                        break;
                }
                break;
            case 'L':
                switch (this.direction) {
                    case 'N':
                        this.direction = 'W';
                        break;
                    case 'E':
                        this.direction = 'N';
                        break;
                    case 'S':
                        this.direction = 'E';
                        break;
                    case 'W':
                        this.direction = 'S';
                        break;
                }
                break;
        }
    }

    int[] getPosition() {
        return this.position;
    }

    char getDirection() {
        return this.direction;
    }

}

class MazeReader {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze1 = new Maze();
    
    public MazeReader(String file_path) {
        
        try {

            logger.trace("**** Reading the maze from file " + file_path);
            
            BufferedReader loader = new BufferedReader(new FileReader(file_path));
            String line;
            int rows = 0;
            int columns = 0;
            while ((line = loader.readLine()) != null) {
                if (rows == 0) {
                    columns = line.length();
                }
                rows += 1;
            }
            loader.close();

            int[][] grid = new int[rows][columns];

            BufferedReader reader = new BufferedReader(new FileReader(file_path));
            int i = 0;
            int[] entry = new int[2];
            int[] exit = new int[2];
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (idx == 0 && line.charAt(idx) == ' ') {
                        entry[0] = i;
                        entry[1] = idx;
                        // System.out.print("ENTR ");
                        grid[i][idx] = 0;
                    } else if (idx == (columns - 1) && line.charAt(idx) == ' ') {
                        exit[0] = i;
                        exit[1] = idx;
                        // System.out.print("EXIT ");
                        grid[i][idx] = 0;
                    } else if (line.charAt(idx) == '#') {
                        grid[i][idx] = 1;
                        // System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        grid[i][idx] = 0;
                        // System.out.print("PASS ");
                    }
                }
                // System.out.print(System.lineSeparator());
                i++;
            }
            reader.close();
            maze1.update(rows, columns, grid, entry, exit);

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }

    public Maze getMaze() {
        return maze1;
    }
}

class Maze {
    private int rows = 0;
    private int columns = 0;
    private int[][] grid;
    private int[] entry = new int[2];
    private int[] exit = new int[2];

    public void update(int rows, int columns, int[][] grid, int[] entry, int[] exit) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new int[rows][columns];
        this.grid = grid;
        this.entry = entry;
        this.exit = exit;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int[][] getMaze() {
        return this.grid;
    }

    public int[] getEntry() {
        return this.entry;
    }

    public int[] getExit() {
        return this.exit;
    }

    public int check(int row, int column) {
        return this.grid[row][column];
    }
}

class MazeSolver {
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
                case 'N':
                    if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 1 && this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else {
                        player1.input('F');
                        solution += "F";
                    }
                    break;
                case 'E':
                    if (this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 1 && this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] + 1) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else {
                        player1.input('F');
                        solution += "F";
                    }
                    break;
                case 'S':
                    if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 1 && this.grid.check(this.player1.getPosition()[0] + 1, this.player1.getPosition()[1]) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else {
                        player1.input('F');
                        solution += "F";
                    }
                    break;
                case 'W':
                    if (this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 0) {
                        player1.input('R');
                        solution += "R";
                        player1.input('F');
                        solution += "F";
                    } else if (this.grid.check(this.player1.getPosition()[0] - 1, this.player1.getPosition()[1]) == 1 && this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1] - 1) == 1) {
                        player1.input('L');
                        solution += "L";
                    } else {
                        player1.input('F');
                        solution += "F";
                    }
                    break;
            }
        // logger.info(this.player1.getPosition()[0] + " " + this.player1.getPosition()[1] + " " + this.player1.getDirection() + " " + this.grid.getExit()[0] + " " + this.grid.getExit()[1] + " " + solution);
        }
        return solution;
    }
    
    public void check(String path) {
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) != ' ') {
                this.player1.input(path.charAt(i));
            }
            if (this.grid.check(this.player1.getPosition()[0], this.player1.getPosition()[1]) == 1) {
                logger.warn("Collided with wall, ignoring last move");
                this.player1.input('R');
                this.player1.input('R');
                this.player1.input('F');
                this.player1.input('R');
                this.player1.input('R');
            }
        }
        if (this.player1.getPosition()[0] == this.grid.getExit()[0] && this.player1.getPosition()[1] == this.grid.getExit()[1]) {
            System.out.println("correct path");
        } else {
            System.out.println("incorrect path");
        }
    }

}