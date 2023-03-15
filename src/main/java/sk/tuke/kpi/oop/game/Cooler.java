package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable{

    private Animation coolerAnimation = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP);
    private Reactor reactor;
    private boolean isOn;

    public Cooler(Reactor reactor) {
        setAnimation(coolerAnimation);
        updateAnimation();
        this.reactor = reactor;
        this.isOn = false;
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

    private void updateAnimation() {
        if (isOn == true) {
            coolerAnimation.play();
        } else {
            coolerAnimation.pause();
        }
    }

    public void coolReactor() {
        if (isOn == true) {
            reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }

    public Reactor getReactor() {
        return reactor;
    }
}
