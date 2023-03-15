package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor> {

    Animation cardAnimation = new Animation("sprites/key.png");

    public AccessCard() {
        setAnimation(cardAnimation);
    }


    @Override
    public void useWith(LockedDoor actor) {
        actor.useLock();
    }

    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }
}
