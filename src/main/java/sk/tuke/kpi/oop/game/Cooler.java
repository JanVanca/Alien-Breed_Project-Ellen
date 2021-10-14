package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable{

    private Animation coolerAnimation = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP);
    private boolean isRunning = false;
    Reactor reactor;


    public Cooler(Reactor reactor) {
        setAnimation(coolerAnimation);
        turnOff();
        this.reactor = reactor;

    }

    @Override
    public void turnOn() {
        isRunning = true;
        coolerAnimation.play();

    }

    @Override
    public void turnOff() {
        isRunning = false;
        coolerAnimation.pause();


    }

    @Override
    public boolean isOn() {
        return isRunning;
    }



    public void coolReactor() {
        if (isOn()) {
            reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        //new Invoke<>(this::coolReactor).scheduleFor(this);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
