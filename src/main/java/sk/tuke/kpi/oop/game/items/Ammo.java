package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

public class Ammo extends AbstractActor implements Usable<Armed> {

    private Animation ammoAnimation = new Animation("sprites/ammo.png");

    public Ammo() {
        setAnimation(ammoAnimation);
    }

    @Override
    public void useWith(Armed actor) {
        if (actor.getFirearm().getAmmo() < actor.getFirearm().getMaxAmmo()) {
            actor.getFirearm().reload(50);
            getScene().removeActor(this);
        }
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
