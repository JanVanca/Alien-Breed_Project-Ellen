package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer{

    private Animation computerAnimation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private boolean isPowered;

    public Computer() {
        setAnimation(computerAnimation);
        this.isPowered = false;
        updateAnimation();
    }

    public int add(int a, int b) {
        if (isPowered == true) {
            return a + b;
        }
        else {
            return 0;
        }
    }

    public float add(float a, float b) {
        if (isPowered == true) {
            return a + b;
        }
        else {
            return 0;
        }
    }

    public int sub(int a, int b) {
        if (isPowered == true) {
            return a - b;
        }
        else {
            return 0;
        }
    }

    public float sub(float a, float b) {
        if (isPowered == true) {
            return a - b;
        }
        else {
            return 0;
        }
    }

    @Override
    public void setPowered(boolean powered) {
        this.isPowered = powered;
        updateAnimation();
    }

    private void updateAnimation() {
        if (isPowered == true) {
            computerAnimation.play();
        }
        else {
            computerAnimation.pause();
        }
    }
}
