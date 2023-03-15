package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible {

    private Animation fireExtinguisherAnimation = new Animation("sprites/extinguisher.png");

    public FireExtinguisher() {
        super(1);
        setAnimation(fireExtinguisherAnimation);
    }

    @Override
    public void useWith(Reactor actor) {
        super.useWith(actor);
        actor.extinguish();
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}
