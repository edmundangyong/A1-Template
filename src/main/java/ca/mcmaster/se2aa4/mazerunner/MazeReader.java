package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class MazeReader {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze = Maze.getInstance();
    
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
            maze.update(rows, columns, grid, entry, exit);

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }

    public Maze getMaze() {
        return maze;
    }
}