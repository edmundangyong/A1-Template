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

        logger.trace("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}

class Player {
    
    private int[] position = new int[2];
    private char direction;

    public Player(int[] position) {
        this.position = position;
        if (position[1] == 0) {
            this.direction = 'E';
        } else {
            this.direction = 'W';
        }
    }

    void input(char c) {
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
    private int rows = 0;
    private int columns = 0;
    private int[][] maze;
    private int[] entry = new int[2];
    private int[] exit = new int[2];
    
    public MazeReader(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "path to input maze file");
        CommandLineParser parser = new DefaultParser();
        
        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) {
                logger.error("/!\\ An error has occured /!\\");
            }

            String file_path = cmd.getOptionValue("i");

            logger.trace("**** Reading the maze from file " + file_path);
            
            BufferedReader loader = new BufferedReader(new FileReader(file_path));
            String line;
            while ((line = loader.readLine()) != null) {
                if (this.rows == 0) {
                    this.columns = line.length();
                }
                this.rows += 1;
            }
            loader.close();

            this.maze = new int[rows][columns];

            BufferedReader reader = new BufferedReader(new FileReader(file_path));
            int i = 0;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (idx == 0 && line.charAt(idx) == ' ') {
                        this.entry[0] = i;
                        this.entry[1] = idx;
                        // System.out.print("ENTR ");
                        maze[i][idx] = 0;
                    } else if (idx == (columns - 1) && line.charAt(idx) == ' ') {
                        this.entry[0] = i;
                        this.entry[1] = idx;
                        // System.out.print("EXIT ");
                        maze[i][idx] = 0;
                    } else if (line.charAt(idx) == '#') {
                        // System.out.print("WALL ");
                        maze[i][idx] = 1;
                    } else if (line.charAt(idx) == ' ') {
                        // System.out.print("PASS ");
                        maze[i][idx] = 0;
                    }
                }
                i++;
            }
            reader.close();

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }

    public int[] getEntry() {
        return this.entry;
    }
    
    public int[] getExit() {
        return this.exit;
    }

}