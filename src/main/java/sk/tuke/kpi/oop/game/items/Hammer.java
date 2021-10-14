package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.BreakableTool;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> implements Collectible {

    private Animation hammerAnimation = new Animation("sprites/hammer.png");
    // private int durability;

    public Hammer() {
        super(1);
        setAnimation(hammerAnimation);
    }

    @Override
    public void useWith(Reactor actor) {
        super.useWith(actor);
        actor.repair();
    }

    // METODY UZ SU DEFINOVANE V TRIEDE BREAKTABLE TOOLS

    //public int getDurability() {
    //    return durability;
    //}

    //public void useWith() {
    //    this.durability = durability - 1;

    //      if (durability == 0) {
    //      Scene reload = getScene();
    //        reload.removeActor(this);
    //    }
    //}


}
