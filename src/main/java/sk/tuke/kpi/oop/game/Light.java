package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {

    private boolean electricity;
    private boolean isRunning;
    private boolean energy;
    Animation lightOnAnimation = new Animation("sprites/light_on.png");
    Animation lightOffAnimation = new Animation("sprites/light_off.png");

    public Light() {
        isRunning = false;
        electricity = false;
        energy = false;
        updateAnimation();
    }

    //public void setElectricityFlow(boolean electricity) {
    //    this.electricity = electricity;
    //    updateAnimation();
    //}

    private void updateAnimation() {
        if (energy == true && isRunning == true) {
            setAnimation(lightOnAnimation);
        }
        else {
            setAnimation(lightOffAnimation);
        }
    }

    public void toggle() {
        if (isRunning == false) {
            isRunning = true;
        }
        else {
            isRunning = false;
        }
        updateAnimation();
    }


    @Override
    public void turnOn() {
        if (isRunning == false) {
            isRunning = true;
        }
        updateAnimation();

    }

    @Override
    public void turnOff() {
        if (isRunning == true) {
            isRunning = false;
        }
        updateAnimation();

    }

    @Override
    public boolean isOn() {
       return isRunning;
    }

    @Override
    public void setPowered(boolean energy) {
        this.energy = energy;
        updateAnimation();
    }
}
