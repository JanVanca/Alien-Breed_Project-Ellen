package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer{

    private Animation lightOnAnimation = new Animation("sprites/light_on.png");
    private Animation lightOffAnimation = new Animation("sprites/light_off.png");
    private boolean isOn;
    private boolean isPowered;

    public Light() {
        this.isOn = false;
        this.isPowered = false;
        setAnimation(lightOffAnimation);
    }

    public void toggle() {
        if (isOn == false) {
            isOn = true;
            updateAnimation();
        } else {
            isOn = false;
            updateAnimation();
        }
    }

    private void updateAnimation() {
        if (isOn == true && isPowered == true) {
            setAnimation(lightOnAnimation);
        } else {
            setAnimation(lightOffAnimation);
        }
    }

    @Override
    public void turnOn() {
        if (isOn == false) {
            isOn = true;
            updateAnimation();
        }
    }

    @Override
    public void turnOff() {
        if (isOn == true) {
            isOn = false;
            updateAnimation();
        }
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void setPowered(boolean powered) {
        this.isPowered = powered;
        updateAnimation();

    }
}
