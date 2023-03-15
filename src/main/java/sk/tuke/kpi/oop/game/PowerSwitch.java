package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {

    Switchable switchable;
    Animation switchAnimation = new Animation("sprites/switch.png");

    public PowerSwitch(Switchable switchable) {
        setAnimation(switchAnimation);
        this.switchable = switchable;
    }

    public Switchable getDevice() {
        return switchable;
    }

    public void switchOn() {
        switchable.turnOn();
        getAnimation().setTint(Color.WHITE);
    }

    public void switchOff() {
        switchable.turnOff();
        getAnimation().setTint(Color.GRAY);
    }
}
