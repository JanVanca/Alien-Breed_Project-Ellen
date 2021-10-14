package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Mjolnir extends AbstractActor {
    private int durability;
    private Animation mjolnirAnimation = new Animation("sprites/hammer.png");



    public Mjolnir() {
        durability = 4;
        setAnimation(mjolnirAnimation);
    }

    public void use() {
        durability = durability - 1;
        if (durability == 0) {
            Scene mjolnirScene = getScene();
            mjolnirScene.removeActor(this);
        }


    }
}
