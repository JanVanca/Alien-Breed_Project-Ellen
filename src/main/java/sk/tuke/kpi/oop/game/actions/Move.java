package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move <A extends Movable> implements Action<A>  {

    private Direction direction;
    private float duration;
    private A actor;
    private boolean isDone;

    private float counter;

    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
        isDone = false;
        this.counter = 0.00f;

    }

    public Move(Direction direction) {
        this.direction = direction;
        isDone = false;
        this.counter = 0.00f;
    }

    @Override
    public void setActor(@Nullable A actor) {
        this.actor = actor;
    }

    @Override
    public @Nullable A getActor() {
        return actor;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() == null) {
            return;
        }

        if (isDone == false) {
            if (counter == 0.00f) {
                actor.startedMoving(direction);
            }
            if (counter >= duration) {
                isDone = true;
                stop();
                return;
            }

            if (duration > 0) {

                actor.setPosition(actor.getPosX() + direction.getDx() * actor.getSpeed(), actor.getPosY() + direction.getDy() * actor.getSpeed());
                counter = counter + deltaTime;
                if ((getActor().getScene()).getMap().intersectsWithWall(actor)) {
                    actor.setPosition(actor.getPosX() - direction.getDx() * actor.getSpeed(), actor.getPosY() - direction.getDy() * actor.getSpeed());
                    actor.collidedWithWall();
                }

            }

            else {
                stop();
            }
        }

    }

    @Override
    public void reset() {
        isDone = false;
        duration = 0;
    }

    public void stop() {
        isDone = true;
        actor.stoppedMoving();
    }
}
