package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(-1,1),
    SOUTHEAST(1,-1),
    SOUTHWEST(-1,-1),
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

         if (dx == 0 && dy == 1) {
             return 90;
         }

         if (dx == 0 && dy == -1) {
             return 270;
         }

         if (dx == 1 && dy == 0) {
             return 0;
         }

         if (dx == -1 && dy == 0) {
             return 180;
         }

         if (dx == 1 && dy == 1) {
             return 45;
         }

         if (dx == -1 && dy == 1) {
             return 135;
         }

         if (dx == 1 && dy == -1) {
             return 315;
         }

         if (dx == -1 && dy == -1) {
             return 225;
         }

         if (dx == 0 && dy == 0) {
             return 0;
         }

         return 0;
    }

   // public Direction combine(Direction other) {
   //
   //     int dx = this.dx + other.getDx();
   //     int dy = this.dy + other.getDy();
   //     for(Direction direction: Direction.values()){
   //         if(dx == direction.getDx() && dy == direction.getDy())
   //             return direction;
   //     }
   //     return NONE;
   // }
}
