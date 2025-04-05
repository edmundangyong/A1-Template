package ca.mcmaster.se2aa4.mazerunner;

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
            
        } catch(ParseException e) {
            logger.error("/!\\ Error parsing command line arguments /!\\");
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        MazeReader reader = new MazeReader(file_path);
        MazeSolver solver = new MazeSolver(reader.getMaze());

        if (maze_path != "") {
            logger.trace("**** Checking path");
            System.out.println(solver.checkPath(maze_path));
        } else {
            logger.trace("**** Computing path");
            String solution = solver.findPath();
            System.out.println("\nCanonical form: " + solution);
            System.out.println("\nFactorized form: " + Factorizer.factorize(solution) + "\n");
        }

        logger.info("** End of MazeRunner");
    }
}