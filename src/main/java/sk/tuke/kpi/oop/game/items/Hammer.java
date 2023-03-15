package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> implements Collectible {

    private Animation hammerAnimation = new Animation("sprites/hammer.png");

    public Hammer() {
        super(1);
        setAnimation(hammerAnimation);
    }


    @Override
    public void useWith(Repairable actor) {
        super.useWith(actor);
        actor.repair();
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}

