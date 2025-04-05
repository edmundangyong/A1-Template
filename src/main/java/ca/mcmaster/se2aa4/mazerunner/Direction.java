package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction right() {
        if (this.equals(NORTH)) return EAST;
        if (this.equals(EAST)) return SOUTH;
        if (this.equals(SOUTH)) return WEST;
        if (this.equals(WEST)) return NORTH;
        return this;
    }

    public Direction left() {
        if (this.equals(NORTH)) return WEST;
        if (this.equals(EAST)) return NORTH;
        if (this.equals(SOUTH)) return EAST;
        if (this.equals(WEST)) return SOUTH;
        return this;
    }
}