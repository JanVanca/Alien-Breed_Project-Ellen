package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.gamelib.map.SceneMap;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move implements Action<Movable> {

    Movable actor;
    boolean isDone = false;
    Direction direction;
    private float duration;
    private float counter = 0.00f;
    boolean isAtWall;

    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
    }

    public Move (Direction direction) {
        this.direction = direction;

    }

    @Nullable
    @Override
    public Movable getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable Movable actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void execute(float deltaTime) {
        isAtTheWall(actor);

        if (counter == 0.00) {
            actor.startedMoving(direction);
        }

        if (counter >= duration) {
            actor.stoppedMoving();
            isDone = true;
            return;
        }

        if (isAtWall == true) {
            actor.stoppedMoving();
        }




        if (isAtWall == false) {
            float aktualspeed = actor.getSpeed() * deltaTime;


            if (Direction.NORTH == direction) {
                actor.setPosition(actor.getPosX(), actor.getPosY() + (int) Math.ceil(aktualspeed));
            }

            if (Direction.SOUTH == direction) {
                actor.setPosition(actor.getPosX(), actor.getPosY() - (int) Math.ceil(aktualspeed));
            }


            if (Direction.EAST == direction) {
                actor.setPosition(actor.getPosX()+ (int) Math.ceil(aktualspeed), actor.getPosY());
            }

            if (Direction.WEST == direction) {
                actor.setPosition(actor.getPosX()- (int) Math.ceil(aktualspeed), actor.getPosY());
            }

            if (Direction.NORTHEAST == direction) {
                actor.setPosition(actor.getPosX() + (int) Math.ceil(aktualspeed), actor.getPosY() + (int) Math.ceil(aktualspeed));
            }

            if (Direction.NORTHWEST == direction) {
                actor.setPosition(actor.getPosX() - (int) Math.ceil(aktualspeed), actor.getPosY() + (int) Math.ceil(aktualspeed));
            }

            if (Direction.SOUTHEAST == direction) {
                actor.setPosition(actor.getPosX() + (int) Math.ceil(aktualspeed), actor.getPosY() - (int) Math.ceil(aktualspeed));
            }

            if (Direction.SOUTHWEST == direction) {
                actor.setPosition(actor.getPosX() - (int) Math.ceil(aktualspeed), actor.getPosY() - (int) Math.ceil(aktualspeed));
            }

            counter = counter + deltaTime;
            isAtWall = false;

        }




    }

    @Override
    public void reset() {
        isDone = false;
        counter = 0.00f;
    }

    public void stop() {
        isDone = true;
        actor.stoppedMoving();
    }

    public void isAtTheWall(Actor actor) {
        if (actor.getScene().getMap().intersectsWithWall(actor) == true) {
           isAtWall = true;
        }



    }

}
