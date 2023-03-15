package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Energy extends AbstractActor implements Usable<Alive> {
    private Animation energyAnimation = new Animation("sprites/energy.png");

    public Energy() {
        setAnimation(energyAnimation);
    }


    @Override
    public void useWith(Alive ripley) {
        if (ripley.getHealth().getActualHealth() < 100) {
            ripley.getHealth().restore();
            getScene().removeActor(this);
        }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
