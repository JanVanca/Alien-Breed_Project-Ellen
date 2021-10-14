package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {

    boolean energy;

    Animation computerAnimation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP);

    public Computer() {
        energy = false;
        updateAnimation();
    }

    private void updateAnimation() {
        if (energy == false) {
            setAnimation(computerAnimation);
            computerAnimation.pause();
        }
        else {
            computerAnimation.play();
        }
    }

    public int add(int value1, int value2) {
        if (energy == false) {
            return 0;
        }
        else {
            return value1 + value2;
        }

    }

    public float add(float value1, float value2) {
        if (energy == false) {
            return 0;
        }
        else  {
            return value1 + value2;
        }

    }

    public int sub(int value1, int value2) {
        if (energy == false) {
            return 0;
        }
        else {
            return value1 - value2;
        }

    }

    public float sub(float value1, float value2) {
        if (energy == false) {
            return 0;
        }
        else {
            return value1 - value2;
        }

    }


    @Override
    public void setPowered(boolean energy) {
        this.energy = energy;
        updateAnimation();
    }
}
