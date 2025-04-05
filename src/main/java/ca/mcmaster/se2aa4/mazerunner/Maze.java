package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private static Maze instance;
    private Maze() {}
    public static Maze getInstance() {
        if (instance == null) {
            instance = new Maze();
        }
        return instance;
    }

    private int[][] grid;
    private int[] entry = new int[2];
    private int[] exit = new int[2];

    public void update(int rows, int columns, int[][] grid, int[] entry, int[] exit) {
        this.grid = new int[rows][columns];
        this.grid = grid;
        this.entry = entry;
        this.exit = exit;
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