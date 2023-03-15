package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NONE(0, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public float getAngle() {
        if (dy == 1 && dx == 0) {
            return 0;
        } else if (dy == -1 && dx == 0) {
            return 180;
        } else if (dx == 1 && dy == 0) {
            return 270;
        } else if (dx == -1 && dy == 0) {
            return 90;
        } else if (dx == 1 && dy == 1) {
            return 315;
        } else if (dx == -1 && dy == 1) {
            return 45;
        } else if (dx == 1 && dy == -1) {
            return 225;
        } else if (dx == -1 && dy == -1) {
            return 135;
        } else if (dx == 0 && dy == 0) {
            return 0;
        }
        return 0;
    }

    public Direction combine(Direction other) {
        int dx = this.dx + other.getDx();
        int dy = this.dy + other.getDy();
        for (Direction direction : Direction.values()) {
            if (dx == direction.getDx() && dy == direction.getDy())
                return direction;
        }
        return NONE;
    }

    public static Direction fromAngle(float angle) {
        if (angle == 0) {
            return Direction.NORTH;
        }
        if (angle == 90) {
            return Direction.WEST;
        }
        if (angle == 180) {
            return Direction.SOUTH;
        }
        if (angle == 270) {
            return Direction.EAST;
        }
        if (angle == 315) {
            return Direction.NORTHEAST;
        }
        if (angle == 45) {
            return Direction.NORTHWEST;
        }
        if (angle == 225) {
            return Direction.SOUTHEAST;
        }
        if (angle == 135) {
            return Direction.SOUTHWEST;
        }
        return Direction.NONE;
    }

}
