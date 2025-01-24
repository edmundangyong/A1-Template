package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input", true, "Path to the input maze file");
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) {
                throw new IllegalArgumentException("/!\\ An error has occured /!\\");
            }

            String file_path = cmd.getOptionValue("i");
            logger.info("**** Reading the maze from file " + file_path);

            MazeReader reader = new MazeReader();

            Maze grid = reader.readMaze(file_path);
            int[] doors = grid.getDoors();
            MazeSolver solver = new MazeSolver(grid, doors);
            logger.info("**** Computing path");
            solver.solve();


        } catch (ParseException e) {
            logger.error("/!\\ An error has occured /!\\");
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.info("** End of MazeRunner");
    }
}

class Maze {
    private char[][] grid;
    private int[] doors = new int[4];

    public Maze(char[][] grid) {
        this.grid = grid;
    }

    public int[] getDoors() {
        return doors;
    }
}

class MazeReader {
    public Maze readMaze(String file_path) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(file_path));
        String line;
        char[][] grid = new char[0][0];
        int rows = 0;

        while ((line = read.readLine()) != null) {
            grid = expandGrid(grid, rows, line.length());
            for (int idx = 0; idx < line.length(); idx++) {
                grid[rows][idx] = line.charAt(idx);
            }
            rows++;
        }

        return new Maze(grid);
    }

    private char[][] expandGrid(char[][] grid, int rows, int length) {
        if (rows >= grid.length) {
            char[][] new_grid = new char[rows + 1][length];
            new_grid = grid.clone();
            return new_grid;
        }
        return grid;
    }
}

class MazeSolver {
    private Maze grid;
    private int start_x, start_y, end_x, end_y;
    
    public MazeSolver(Maze grid, int[] doors) {
        this.grid = grid;
        this.start_x = doors[0];
        this.start_y = doors[1];
        this.end_x = doors[2];
        this.end_y = doors[3];
    }

    public String solve() {
        
        String solution = "";
        return solution;

    }

}

class Factorizer {    
    public static String factorize(String solution) {
        String factorized_solution = "";
        return factorized_solution;
    }
}