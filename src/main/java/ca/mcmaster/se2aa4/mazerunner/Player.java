package ca.mcmaster.se2aa4.mazerunner;

public class Player {
    private int[] position = new int[2];
    private Direction direction;

    public void start(int[] position) {
        this.position = position;
        if (position[1] == 0) {
            this.direction = Direction.EAST;
        } else {
            this.direction = Direction.WEST;
        }
    }

    void input(char c) {
        switch (c) {
            case 'F':
                switch (this.direction) {
                    case NORTH:
                        this.position[0] -= 1;
                        break;
                    case EAST:
                        this.position[1] += 1;
                        break;
                    case SOUTH:
                        this.position[0] += 1;
                        break;
                    case WEST:
                        this.position[1] -= 1;
                        break;
                }
                break;
            case 'R':
                this.direction = this.direction.right();
                break;
            case 'L':
                this.direction = this.direction.left();
                break;
        }
    }

    int[] getPosition() {
        return this.position;
    }

    Direction getDirection() {
        return this.direction;
    }

}