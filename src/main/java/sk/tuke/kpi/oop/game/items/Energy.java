package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Energy extends AbstractActor implements Usable<Ripley> {

    Animation EnergyAnimation = new Animation("sprites/energy.png", 16, 16, 0.1f, Animation.PlayMode.LOOP);

    public Energy() {
        setAnimation(EnergyAnimation);
    }

    @Override
    public void useWith(Ripley actor) {
        if (actor.getEnergy() == 100) {
            return;
        } else {
            actor.setEnergy(100);
            Scene sceneEnergy = getScene();
            sceneEnergy.removeActor(this);

        }


    }
}
