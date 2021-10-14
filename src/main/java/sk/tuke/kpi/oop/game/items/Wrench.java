package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.BreakableTool;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool<DefectiveLight> implements Collectible {

    Animation wrenchAnimation = new Animation("sprites/wrench.png", 16, 16, 0.1f, Animation.PlayMode.LOOP);

    public Wrench() {
        super(2);
        setAnimation(wrenchAnimation);
    }

    @Override
    public void useWith(DefectiveLight actor) {
        super.useWith(actor);
        actor.repair();
    }
}
