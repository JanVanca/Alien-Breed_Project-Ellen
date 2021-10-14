package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.BreakableTool;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible {

    // private int durability;
    private Animation fireExtinguisherAnimation = new Animation("sprites/extinguisher.png");


    public FireExtinguisher() {
        super(1);
        setAnimation(fireExtinguisherAnimation);

    }

    @Override
    public void useWith(Reactor actor) {
        super.useWith(actor);
        actor.repair();
    }

    // METODY UZ SU DEFINOVANE V TRIEDE BREAKTABLE TOOL,

    //public int getDurability() {
    //    return durability;
    //}

    //public void useWith() {
    //    durability = durability - 1;
    //    if (durability == 0) {
    //        Scene fireExtinguisherScene = getScene();
    //        fireExtinguisherScene.removeActor(this);
    //   }
    //}


}
