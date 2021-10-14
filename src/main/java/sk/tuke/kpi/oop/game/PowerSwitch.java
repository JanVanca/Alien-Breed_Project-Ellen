package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {

    private Animation controllerAnimation = new Animation("sprites/switch.png");
    //private Reactor reactor;
    Switchable device;
    private boolean isRunning = false;

    PowerSwitch(Switchable device) {
        setAnimation(controllerAnimation);
        getDevice(device);
    }


    //public void toggle() {
    //    isRunning = reactor.isOn();
    //    if(isRunning == false) {
    //        reactor.turnOn();
    //    }
    //    else {
    //        reactor.turnOff();
    //    }
    //}


   public void getDevice(Switchable device) {
        this.device = device;
    }

    public void switchOn() {
        device.turnOn();
        getAnimation().setTint(Color.WHITE);

    }

    public void switchOff() {
        device.turnOff();
        getAnimation().setTint(Color.GRAY);
    }

}
