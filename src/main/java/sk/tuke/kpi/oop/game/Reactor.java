package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.items.Mjolnir;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable{

    private int temperature;
    private int damage;
    private boolean running;
    private Animation normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation overheatedAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.ONCE);

    private Animation turnedOffAnimation = new Animation("sprites/reactor.png");

    private Light light;
    private Set<EnergyConsumer> devices;

    public Reactor() {
        this.temperature = 0;
        this.damage = 0;
        this.running = false;
        setAnimation(turnedOffAnimation);
        this.devices = new HashSet<>();
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    public void increaseTemperature(int increment) {
        if (increment <= 0) {
            return;
        } else if (damage >= 100) {
            return;
        } else if (running == false) {
            return;
        } else {
            if (damage < 33) {
                temperature = temperature + increment;
            } else if (damage >= 33 && damage <= 66) {
                temperature = temperature + (int) Math.ceil(increment * 1.5);
            } else {
                temperature = temperature + (increment * 2);
            }

            if (temperature > 2000 && temperature <= 6000) {

                if (damage > (100 * (temperature - 2000) / (6000 - 2000))) {

                }else {
                    damage = (100 * (temperature - 2000) / (6000 - 2000));
                }
            }

            if (temperature >= 6000) {
                damage = 100;
            }
            updateAnimation();
        }
    }

    public void decreaseTemperature(int decrement) {
        if (decrement <= 0) {
            return;
        } else if (running == false) {
            return;
        } else if (temperature < 0) {
            return;
        } else if (damage >= 50) {
            temperature = temperature - decrement / 2;
        } else {
            temperature = temperature - decrement;
        }
        updateAnimation();
    }

    private void updateAnimation() {
        if (isOn() == false) {
            return;
        }
        if (damage >= 100) {
            turnOff();
            setAnimation(brokenAnimation);
        } else if (temperature >= 4000) {
            //overheatedAnimation = new Animation("sprites/reactor_hot.png", 80, 80, (2.5f / damage), Animation.PlayMode.LOOP_PINGPONG);
            overheatedAnimation.setFrameDuration(2.5f / damage);
            setAnimation(overheatedAnimation);
        } else {
            setAnimation(normalAnimation);
        }
    }

    @Override
    public boolean repair() {
        if ( this.damage > 0 && this.damage < 100) {
            if (temperature > ((damage - 50) * 40) + 2000) {
                temperature = ((damage - 50) * 40) + 2000;
            }
            this.damage = damage - 50;
            if (this.damage < 0) {
                this.damage = 0;
            }
            updateAnimation();
            return true;
        }
        return false;
    }

    public void repair(Mjolnir mjolnir) {
        if (mjolnir == null) {
            return;
        }
        if (mjolnir.getDurability() > 0 && this.damage > 0 && this.damage < 100) {
            mjolnir.use();
            temperature = ((damage - 50) * 40) + 2000;
            this.damage = damage - 50;
            if (this.damage < 0) {
                this.damage = 0;
            }
            updateAnimation();
        }
    }

    @Override
    public void turnOn() {
        if (running == false) {
            running = true;

            if (light != null) {
                light.setPowered(isOn());
            }

            updateAnimation();
        }
    }

    @Override
    public void turnOff() {
        if (running == true) {
            running = false;

            if (light != null) {
                light.setPowered(isOn());
            }

            setAnimation(turnedOffAnimation);
        }
    }

    @Override
    public boolean isOn() {
        return running;
    }

    public void addDevice(EnergyConsumer energyConsumer) {
        if (energyConsumer == null) {
            return;
        }
        devices.add(energyConsumer);
        energyConsumer.setPowered(isOn());
    }

    public void removeDevice(EnergyConsumer energyConsumer) {
        if (energyConsumer != null) {
            devices.remove(energyConsumer);
            energyConsumer.setPowered(false);
        }
    }

    public boolean extinguish() {
        if ( this.temperature > 0 && this.damage < 100) {
            temperature = temperature - 4000;
            if (temperature < 0) {
                temperature = 0;
            }
            updateAnimation();
            return true;
        }
        return false;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }
}
