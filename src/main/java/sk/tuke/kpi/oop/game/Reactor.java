package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.items.Mjolnir;
import sk.tuke.kpi.oop.game.items.Repairable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {

    private int temperature;
    private int damage;
    private boolean running;
    private boolean broken;
    private boolean eletricity;
    private boolean wasExtinguishedReactor;
    private Set<EnergyConsumer> devices;
    private Animation normalAnimation  = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG );
    private Animation overHeatAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation destroyedAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.ONCE);
    private Animation turnOffAnimation = new Animation("sprites/reactor.png");
    private Animation reactorExtinguishedAnimation = new Animation("sprites/reactor_extinguished.png");

    public Reactor() {
        temperature = 0;
        damage = 0;
        running = false;
        broken = false;
        wasExtinguishedReactor = false;
        updateAnimation();
        devices = new HashSet<>();

    }

    public int getDamage() {
        return damage;
    }

    public int getTemperature() {
        return temperature;
    }

    public void increaseTemperature(int increment) {
        if (increment < 0) {
            return;
        }
        else if (isOn() == false) {
            return;
        }
        else if (isBrokenReactor() == true) {
            return;
        }
        else {
            if (damage < 33) {
                temperature = temperature + increment;
            }
            else if (damage >= 33 && damage <= 66) {
                temperature = temperature + (int) Math.ceil(increment* 1.5);
            }
            else {
                temperature = temperature + increment * 2;
            }

            damage = (100*(temperature - 2000)) / (6000 - 2000);

            if (temperature <= 2000) {
                damage = 0;
            }

            if (temperature >= 6000) {
                damage = 100;
            }

            updateAnimation();
        }



    }

    private void updateAnimation() {
        if (isOn() == false) {
            setAnimation(turnOffAnimation);
            return;

        }

        else if(wasExtinguishedReactor == true && damage >= 100) {
            setAnimation(reactorExtinguishedAnimation);
        }

        else if(isBrokenReactor() == true && wasExtinguishedReactor != true) {
            setAnimation(destroyedAnimation);

        }

        else {
            setAnimation(normalAnimation);
            if (temperature >= 4000) {
                setAnimation(overHeatAnimation);
            }

            if (temperature < 4000) {
                setAnimation(normalAnimation);
            }

            if (temperature >= 6000) {
                setAnimation(destroyedAnimation);
            }
        }




    }

    public void decreaseTemperature(int decrement) {

        if (decrement < 0) {
            return;
        }

        else if (isOn() == false) {
            return;
        }
        else if ( wasExtinguishedReactor == true) {
            temperature = temperature - decrement;
        }


        else if (isBrokenReactor() == true && wasExtinguishedReactor != true) {
            return;
        }

        else {
            if (temperature <=0) {
                temperature = 0;
            }
            else if (damage < 50) {
                temperature = temperature - decrement;
            }
            else if (damage > 50 && damage < 100) {
                temperature = temperature - decrement / 2;
            }
            else {
                return;
            }

            updateAnimation();
        }

    }


    @Override
    public boolean repair() {
            if (damage > 0 && damage < 100) {
                damage = damage - 50;
                if (damage <= 0) {
                    damage = 0;
                }
                updateAnimation();
                decreaseTemperature(3000);
                return true;

            }
            else {
                return false;
            }
    }


    public void repair(Mjolnir mjolnir) {
        if (mjolnir != null) {
            if (damage > 0 && damage < 100) {
                damage = damage - 50;
                if (damage <= 0) {
                    damage = 0;
                }
                mjolnir.use();
                updateAnimation();
                decreaseTemperature(3000);
            }
        }
    }

    public void turnOn() {
        running = true;
        updateAnimation();
        if (damage >= 100) {
            broken = true;
            turnOff();
        }

        Iterator<EnergyConsumer> iterator = devices.iterator();
        while (iterator.hasNext()){
            EnergyConsumer device = iterator.next();
            device.setPowered(true);
        }

    }

    public void turnOff() {
        running = false;
        if (isBrokenReactor() == true) {
            return;
        }
        updateAnimation();

        Iterator<EnergyConsumer> iterator = devices.iterator();
        while (iterator.hasNext()){
            EnergyConsumer device = iterator.next();
            device.setPowered(false);
        }

        //to iste len for
        //for (EnergyConsumer device : devices){
        //    device.setPowered(false);
        //}


    }

    public boolean isOn() {
        if (running == true) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isBrokenReactor() {
        if (damage >= 100) {
            broken = true;
            return true;
        }
        else {
            return false;
        }
    }


    public void addDevice(EnergyConsumer energyConsumer) {
        //this.energyConsumer = energyConsumer;
        this.devices.add(energyConsumer);
        isRunningEletricity(energyConsumer);



    }

    public void removeDevice(EnergyConsumer energyConsumer) {
        this.devices.remove(energyConsumer);
        energyConsumer.setPowered(false);


    }


    private void isRunningEletricity(EnergyConsumer energyConsumer) {
        if (energyConsumer != null) {
            if (isOn() == true) {
                eletricity = true;
                energyConsumer.setPowered(eletricity);
            }else {
                eletricity = false;
                energyConsumer.setPowered(eletricity);
            }
        }
    }

    public boolean extinguish() {
        decreaseTemperature(4000);
        updateAnimation();
        return wasExtinguishedReactor = true;



    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }



}
