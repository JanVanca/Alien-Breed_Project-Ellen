package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo extends AbstractActor implements Usable <Ripley> {

    Animation ammoAnimation = new Animation("sprites/ammo.png", 16, 16, 0.1f, Animation.PlayMode.LOOP);


    public Ammo() {
        setAnimation(ammoAnimation);
    }

    @Override
    public void useWith(Ripley actor) {
        if (actor.getAmmo() >= 500) {
            return;
        }
        else {
            actor.setAmmo(actor.getAmmo() + 50);
            Scene sceneAmmo = getScene();
            sceneAmmo.removeActor(this);
        }




    }

}
