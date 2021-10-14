package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {

    Animation doorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f);



    public Door() {
        setAnimation(doorAnimation);
        doorAnimation.stop();

    }
    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void useWith(Actor actor) {

    }
}
